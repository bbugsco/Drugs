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
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
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

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    protected final SimpleContainerData simpleContainerData;
    private int progress;
    private int maxProgress;

    private List<RecipeHolder<RefineryRecipe>> recipes;
    private final DataSlot selectedRecipeIndex;

    public RefineryBlockEntity(BlockPos pos, BlockState blockState) {
        super(DrugsBlockEntities.REFINERY, pos, blockState);
        this.simpleContainerData = new SimpleContainerData(2) {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> RefineryBlockEntity.this.progress;
                    case 1 -> RefineryBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> RefineryBlockEntity.this.progress = value;
                    case 1 -> RefineryBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
        this.recipes = Lists.newArrayList();
        this.selectedRecipeIndex = DataSlot.standalone();
    }


    public List<RecipeHolder<RefineryRecipe>> getRecipes() {
        return this.recipes;
    }

    private void setupRecipeList(Container container, ItemStack stack) {
        this.recipes.clear();
        if (!stack.isEmpty()) {
            this.recipes = this.level.getRecipeManager().getRecipesFor(RefineryRecipe.Type.INSTANCE, createRecipeInput(container), this.level);
        }
    }

    private static SingleRecipeInput createRecipeInput(Container container) {
        return new SingleRecipeInput(container.getItem(0));
    }


    public void tick(Level level, BlockPos pos, BlockState state) {

    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.literal("Hash Press");
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

    @Override
    public BlockPos getScreenOpeningData(ServerPlayer player) {
        return worldPosition;
    }


    @Override
    public @Nullable AbstractContainerMenu createMenu(int syncId, Inventory inventory, Player player) {
        return new RefineryMenu(syncId, inventory, this, this.simpleContainerData);
    }

    private static int getCookTime(Level world, RefineryBlockEntity hashPress) {
        SingleRecipeInput input = new SingleRecipeInput(hashPress.getItem(INPUT_SLOT));
        // return hashPress.matchGetter.getRecipeFor(input, world).map((recipe) -> recipe.value().getTime()).orElse(200);
        return 0;
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
