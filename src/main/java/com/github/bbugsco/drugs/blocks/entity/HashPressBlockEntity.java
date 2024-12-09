package com.github.bbugsco.drugs.blocks.entity;

import com.github.bbugsco.drugs.gui.HashPressMenu;
import com.github.bbugsco.drugs.recipe.HashPressRecipe;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Optional;

public class HashPressBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {

    private final NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    protected final SimpleContainerData simpleContainerData;
    private int progress;
    private int maxProgress;

    private final RecipeManager.CachedCheck<SingleRecipeInput, HashPressRecipe> matchGetter;

    public HashPressBlockEntity(BlockPos pos, BlockState state) {
        super(DrugsBlockEntities.HASH_PRESS, pos, state);
        this.simpleContainerData = new SimpleContainerData(2) {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> HashPressBlockEntity.this.progress;
                    case 1 -> HashPressBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> HashPressBlockEntity.this.progress = value;
                    case 1 -> HashPressBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
        this.matchGetter = RecipeManager.createCheck(HashPressRecipe.Type.INSTANCE);
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
        return Component.literal("Hash Press");
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int syncId, Inventory inventory, Player player) {
        return new HashPressMenu(syncId, inventory, this, this.simpleContainerData);
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

    public void tick(Level level, BlockPos pos, BlockState state) {
        if(level.isClientSide) return;
        if (isOutputSlotEmptyOrReceivable()) {
            if (this.hasRecipe()) {
                this.increaseCraftProgress();
                setChanged(level, pos, state);
                if (hasCraftingFinished()) {
                    this.craftItem();
                    this.resetProgress();
                }
            } else {
                this.resetProgress();
            }
        } else {
            this.resetProgress();
            setChanged(level, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        Optional<RecipeHolder<HashPressRecipe>> recipe = getCurrentRecipe();
        this.removeItem(INPUT_SLOT, 1);
        recipe.ifPresent(inputOutputRecipeRecipeEntry -> this.setItem(OUTPUT_SLOT, new ItemStack(
                inputOutputRecipeRecipeEntry.value().result().getItem(),
                getItem(OUTPUT_SLOT).getCount() + inputOutputRecipeRecipeEntry.value().result().getCount())));
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<HashPressRecipe>> recipe = getCurrentRecipe();
        return recipe.isPresent() && canInsertAmountIntoOutputSlot(recipe.get().value().result())
                && canInsertItemIntoOutputSlot(recipe.get().value().result().getItem());
    }

    private Optional<RecipeHolder<HashPressRecipe>> getCurrentRecipe() {
        SimpleContainer inv = new SimpleContainer(this.getContainerSize());
        for(int i = 0; i < this.getContainerSize(); i++) {
            inv.setItem(i, this.getItem(i));
        }
        return matchGetter.getRecipeFor(new SingleRecipeInput(inv.getItem(INPUT_SLOT)), getLevel());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getItem(OUTPUT_SLOT).getItem() == item || this.getItem(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getItem(OUTPUT_SLOT).getCount() + result.getCount() <= getItem(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.getItem(OUTPUT_SLOT).isEmpty() || this.getItem(OUTPUT_SLOT).getCount() < this.getItem(OUTPUT_SLOT).getMaxStackSize();
    }

    private static int getCookTime(Level world, HashPressBlockEntity hashPress) {
        SingleRecipeInput input = new SingleRecipeInput(hashPress.getItem(INPUT_SLOT));
        return hashPress.matchGetter.getRecipeFor(input, world).map((recipe) -> recipe.value().getTime()).orElse(200);
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