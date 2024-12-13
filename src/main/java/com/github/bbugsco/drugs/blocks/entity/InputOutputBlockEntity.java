package com.github.bbugsco.drugs.blocks.entity;

import com.github.bbugsco.drugs.recipe.SingleInputTimedRecipe;
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

import java.util.Optional;

public abstract class InputOutputBlockEntity<T extends SingleInputTimedRecipe> extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {

    private final String displayName;
    private final NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);
    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    protected final SimpleContainerData simpleContainerData;
    private int progress;
    private int maxProgress;

    public final RecipeManager.CachedCheck<SingleRecipeInput, T> matchGetter;

    public InputOutputBlockEntity(BlockPos pos, BlockState state, String displayName, RecipeType<T> type, BlockEntityType<?> blockEntityType) {
        super(blockEntityType, pos, state);
        this.displayName = displayName;
        this.simpleContainerData = new SimpleContainerData(2) {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> InputOutputBlockEntity.this.progress;
                    case 1 -> InputOutputBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> InputOutputBlockEntity.this.progress = value;
                    case 1 -> InputOutputBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
        this.matchGetter = RecipeManager.createCheck(type);
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
        boolean itemsEqual = !stack.isEmpty() && net.minecraft.world.item.ItemStack.isSameItem(itemStack, stack);
        this.inventory.set(slot, stack);
        if (slot == INPUT_SLOT && !itemsEqual) {
            this.maxProgress = getCookTime(this.level, this);
            this.progress = 0;
            this.setChanged();
        }
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if(level.isClientSide) return;
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

    private void craftItem() {
        Optional<RecipeHolder<T>> recipe = getCurrentRecipe();
        this.removeItem(INPUT_SLOT, 1);
        recipe.ifPresent(inputOutputRecipeRecipeEntry -> this.setItem(OUTPUT_SLOT, new ItemStack(
                inputOutputRecipeRecipeEntry.value().result().getItem(),
                getItem(OUTPUT_SLOT).getCount() + inputOutputRecipeRecipeEntry.value().result().getCount())));
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<T>> recipe = getCurrentRecipe();
        return recipe.isPresent() && canInsertAmountIntoOutputSlot(recipe.get().value().result())
                && canInsertItemIntoOutputSlot(recipe.get().value().result().getItem());
    }

    private Optional<RecipeHolder<T>> getCurrentRecipe() {
        return matchGetter.getRecipeFor(new SingleRecipeInput(getItem(INPUT_SLOT)), level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getItem(OUTPUT_SLOT).getItem() == item || getItem(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(net.minecraft.world.item.ItemStack result) {
        return this.getItem(OUTPUT_SLOT).getCount() + result.getCount() <= getItem(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.getItem(OUTPUT_SLOT).isEmpty() || getItem(OUTPUT_SLOT).getCount() < getItem(OUTPUT_SLOT).getMaxStackSize();
    }

    private int getCookTime(Level world, InputOutputBlockEntity<T> entity) {
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
        progress = tag.getInt("io_block.progress");
        maxProgress = tag.getInt("io_block.max_progress");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        ContainerHelper.saveAllItems(tag, this.inventory, registries);
        tag.putInt("io_block.progress", progress);
        tag.putInt("io_block.max_progress", maxProgress);
    }

}
