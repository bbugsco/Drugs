package com.github.bbugsco.drugs.blocks.entity;

import com.github.bbugsco.drugs.gui.RefineryMenu;
import com.github.bbugsco.drugs.recipe.RefineryRecipe;
import com.google.common.collect.Lists;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class RefineryBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {

    private final NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);
    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    int progress;
    int maxProgress;
    int selectedRecipeIndex;

    private List<RecipeHolder<RefineryRecipe>> recipes;

    protected final SimpleContainerData dataAccess = new SimpleContainerData(3) {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> RefineryBlockEntity.this.progress;
                case 1 -> RefineryBlockEntity.this.maxProgress;
                case 2 -> RefineryBlockEntity.this.selectedRecipeIndex;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> RefineryBlockEntity.this.progress = value;
                case 1 -> RefineryBlockEntity.this.maxProgress = value;
                case 2 -> RefineryBlockEntity.this.selectedRecipeIndex = value;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    };

    public RefineryBlockEntity(BlockPos pos, BlockState blockState) {
        super(DrugsBlockEntities.REFINERY, pos, blockState);
        this.recipes = Lists.newArrayList();
    }

    @Override
    public void setLevel(Level level) {
        super.setLevel(level);
        this.setupRecipeList();
    }

    public List<RecipeHolder<RefineryRecipe>> getRecipes() {
        return this.recipes;
    }

    public void setupRecipeList() {
        if (this.level != null) {
            this.recipes = this.level.getRecipeManager().getAllRecipesFor(RefineryRecipe.Type.INSTANCE);
        }
    }

    public int getSelectedRecipeIndex() {
        return this.selectedRecipeIndex;
    }

    public void setSelectedRecipeIndex(int selectedRecipeIndex) {
        this.selectedRecipeIndex = selectedRecipeIndex;
    }

    private int getCookTime() {
        if (hasRecipe()) {
            RefineryRecipe recipe = getRecipes().get(getSelectedRecipeIndex()).value();
            return recipe.getTime();
        }
        return 0;
    }

    private boolean hasRecipe() {
        RefineryRecipe recipe = getRecipes().get(getSelectedRecipeIndex()).value();
        return recipe.matches(new SingleRecipeInput(inventory.get(INPUT_SLOT)), getLevel());
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if(level.isClientSide) return;
        if (isOutputSlotEmptyOrReceivable()) {
            if (this.hasRecipe()) {
                RefineryRecipe recipe = getRecipes().get(getSelectedRecipeIndex()).value();
                if (recipe.matches(new SingleRecipeInput(inventory.get(INPUT_SLOT)), level)) {
                    if ((inventory.get(OUTPUT_SLOT).getCount() == 0) || inventory.get(OUTPUT_SLOT).getItem() == recipe.result().getItem()) {
                        progress++;
                        setChanged(level, pos, state);
                        if (progress >= maxProgress) {
                            this.removeItem(INPUT_SLOT, 1);
                            ItemStack result = recipe.result();
                            if (canInsertAmountIntoOutputSlot(result)) {
                                this.setItem(OUTPUT_SLOT, new ItemStack(result.getItem(), getItem(OUTPUT_SLOT).getCount() + recipe.result().getCount()));
                            }
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

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getItem(OUTPUT_SLOT).getCount() + result.getCount() <= getItem(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.getItem(OUTPUT_SLOT).isEmpty() || this.getItem(OUTPUT_SLOT).getCount() < this.getItem(OUTPUT_SLOT).getMaxStackSize();
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.literal("Refinery");
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayer player) {
        return worldPosition;
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int syncId, Inventory inventory, Player player) {
        return new RefineryMenu(syncId, inventory, this, this.dataAccess);
    }

    @Override
    public void setChanged() {
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
            super.setChanged();
        }
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
            this.maxProgress = getCookTime();
            this.progress = 0;
            this.setChanged();
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
