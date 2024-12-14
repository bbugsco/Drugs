package com.github.bbugsco.drugs.block.entity.one_input;

import com.github.bbugsco.drugs.block.entity.ImplementedInventory;
import com.github.bbugsco.drugs.block.entity.RecipeList;
import com.github.bbugsco.drugs.recipe.generic.OneInputRecipe;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class OneInputBlockEntity<T extends OneInputRecipe> extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory, RecipeList<T> {

    private final String displayName;
    private final NonNullList<ItemStack> inventory;

    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    private int progress;
    private int maxProgress;
    private int selectedRecipeIndex;

    protected final SimpleContainerData dataAccess = new SimpleContainerData(3) {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> OneInputBlockEntity.this.progress;
                case 1 -> OneInputBlockEntity.this.maxProgress;
                case 2 -> OneInputBlockEntity.this.selectedRecipeIndex;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> OneInputBlockEntity.this.progress = value;
                case 1 -> OneInputBlockEntity.this.maxProgress = value;
                case 2 -> OneInputBlockEntity.this.selectedRecipeIndex = value;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    };

    public final RecipeManager.CachedCheck<SingleRecipeInput, T> matchGetter;
    private final boolean selectsRecipe;
    private final RecipeType<T> type;
    private List<RecipeHolder<T>> recipes;

    public OneInputBlockEntity(BlockPos pos, BlockState state, String displayName, RecipeType<T> type, BlockEntityType<?> blockEntityType, boolean selectsRecipe) {
        super(blockEntityType, pos, state);
        this.displayName = displayName;
        this.inventory = NonNullList.withSize(5, ItemStack.EMPTY);
        this.selectsRecipe = selectsRecipe;
        this.matchGetter = RecipeManager.createCheck(type);
        this.type = type;
        this.recipes = new ArrayList<>();
    }

    @Override
    public void setLevel(Level level) {
        super.setLevel(level);
        setupRecipeList();
    }

    @Override
    public boolean selectsRecipe() {
        return selectsRecipe;
    }

    public int getMaxByproducts() {
        int max = 0;
        for (RecipeHolder<T> holder : recipes) {
            if (holder.value().getByproducts().size() > max)
                max = holder.value().getByproducts().size();
        }
        return max;
    }

    @Override
    public List<RecipeHolder<T>> getRecipes() {
        return this.recipes;
    }

    public void setupRecipeList() {
        if (this.level != null) {
            this.recipes = this.level.getRecipeManager().getAllRecipesFor(type);
        }
    }

    public int getSelectedRecipeIndex() {
        return this.selectedRecipeIndex;
    }

    public void setSelectedRecipeIndex(int selectedRecipeIndex) {
        this.selectedRecipeIndex = selectedRecipeIndex;
    }

    public ItemStack getRenderStack() {
        if(this.getItem(OUTPUT_SLOT).isEmpty()) {
            return this.getItem(INPUT_SLOT);
        } else {
            return this.getItem(OUTPUT_SLOT);
        }
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayer player) {
        return worldPosition;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.literal(displayName);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        ItemStack itemStack = this.inventory.get(slot);
        boolean itemsEqual = !stack.isEmpty() && ItemStack.isSameItem(itemStack, stack);
        this.inventory.set(slot, stack);
        if (slot == INPUT_SLOT && !itemsEqual) {
            this.maxProgress = getCookTime(this.level, this);
            this.progress = 0;
            this.setChanged();
        }
    }

    public Optional<RecipeHolder<T>> getCurrentRecipe() {
        if (selectsRecipe) {
            List<RecipeHolder<T>> recipes = getRecipes();
            if (recipes.isEmpty()) {
                return Optional.empty();
            } else {
                int index = getSelectedRecipeIndex();
                if (index > -1 && index < recipes.size()) {
                    return Optional.of(recipes.get(index));
                } else return Optional.empty();
            }
        } else {
            return matchGetter.getRecipeFor(new SingleRecipeInput(getItem(INPUT_SLOT)), level);
        }
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if(level.isClientSide) return;
        if (selectsRecipe) {
            tickForWhenOneInputCanHaveMultipleOutputs(level, pos, state);
        } else {
            if (isOutputSlotEmptyOrReceivable()) {
                if (hasRecipe()) {
                    progress++;
                    setChanged(level, pos, state);
                    if (progress >= maxProgress) {
                        craftItem();
                        progress = 0;
                    }
                } else {
                    progress = 0;
                }
            } else {
                progress = 0;
                setChanged(level, pos, state);
            }
        }
    }

    public void tickForWhenOneInputCanHaveMultipleOutputs(Level level, BlockPos pos, BlockState state) {
        if (isOutputSlotEmptyOrReceivable()) {
            if (hasRecipe()) {
                T recipe = getRecipes().get(getSelectedRecipeIndex()).value();
                if (recipe.matches(new SingleRecipeInput(inventory.getFirst()), level)) {
                    if ((inventory.get(OUTPUT_SLOT).getCount() == 0) || inventory.get(OUTPUT_SLOT).getItem() == recipe.getResult().getItem()) {
                        progress++;
                        setChanged(level, pos, state);
                        if (progress >= maxProgress) {
                            craftItem(recipe);
                            progress = 0;
                        }
                    } else {
                        progress = 0;
                    }
                }
            } else {
                progress = 0;
            }
        } else {
            progress = 0;
            setChanged(level, pos, state);
        }
    }

    private void craftItem(T recipe) {
        this.removeItem(INPUT_SLOT, 1);
        ItemStack result = recipe.getResult();
        if (canInsertAmountIntoSlot(result, OUTPUT_SLOT)) {
            this.setItem(OUTPUT_SLOT, new ItemStack(result.getItem(), getItem(OUTPUT_SLOT).getCount() + recipe.getResult().getCount()));
        }
        byproduct(recipe);
    }

    private void craftItem() {
        Optional<RecipeHolder<T>> recipe = getCurrentRecipe();
        this.removeItem(INPUT_SLOT, 1);
        recipe.ifPresent(inputOutputRecipeRecipeEntry -> this.setItem(OUTPUT_SLOT, new ItemStack(
                inputOutputRecipeRecipeEntry.value().getResult().getItem(),
                getItem(OUTPUT_SLOT).getCount() + inputOutputRecipeRecipeEntry.value().getResult().getCount())));
        recipe.ifPresent(recipeHolder -> byproduct(recipeHolder.value()));
    }

    private void byproduct(OneInputRecipe recipe) {
        if (recipe instanceof OneInputRecipe byproductRecipe) {
            List<ItemStack> byproducts = byproductRecipe.getByproducts();
            if (byproducts.isEmpty()) { return; }
            for (int i = 0; i < byproducts.size(); i++) {
                if (getLevel() == null) return;
                if (getLevel().random.nextInt(5) != 0) continue;
                int slot = OUTPUT_SLOT + 1 + i;
                ItemStack toAdd = byproducts.get(i);
                if (!canInsertItemIntoSlot(toAdd.getItem(), slot)) return;
                if (!canInsertAmountIntoSlot(toAdd, slot)) return;
                setItem(slot, new ItemStack(toAdd.getItem(), getItem(slot).getCount() + toAdd.getCount()));
            }
        }
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<T>> recipe = getCurrentRecipe();
        return recipe.isPresent() && canInsertAmountIntoSlot(recipe.get().value().getResult(), OUTPUT_SLOT) && canInsertItemIntoSlot(recipe.get().value().getResult().getItem(), OUTPUT_SLOT);
    }

    private boolean canInsertItemIntoSlot(Item item, int slot) {
        return this.getItem(slot).getItem() == item || getItem(slot).isEmpty();
    }

    private boolean canInsertAmountIntoSlot(ItemStack result, int slot) {
        return this.getItem(slot).getCount() + result.getCount() <= getItem(slot).getMaxStackSize();
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.getItem(OUTPUT_SLOT).isEmpty() || getItem(OUTPUT_SLOT).getCount() < getItem(OUTPUT_SLOT).getMaxStackSize();
    }

    private int getCookTime(Level world, OneInputBlockEntity<T> entity) {
        SingleRecipeInput input = new SingleRecipeInput(entity.getItem(INPUT_SLOT));
        return entity.matchGetter.getRecipeFor(input, world).map((recipe) -> recipe.value().getTime()).orElse(200);
    }

    @Override
    public void setChanged() {
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
            super.setChanged();
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        ContainerHelper.loadAllItems(tag, this.inventory, registries);
        progress = tag.getInt("Progress");
        maxProgress = tag.getInt("MaxProgress");
        selectedRecipeIndex = tag.getInt("SelectedRecipeIndex");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        ContainerHelper.saveAllItems(tag, this.inventory, registries);
        tag.putInt("Progress", progress);
        tag.putInt("MaxProgress", maxProgress);
        tag.putInt("SelectedRecipeIndex", selectedRecipeIndex);
    }

}
