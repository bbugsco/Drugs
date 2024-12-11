package com.github.bbugsco.drugs.gui;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractInputOutputProgressMenu extends AbstractContainerMenu {

    protected final Container inventory;
    protected final SimpleContainerData simpleContainerData;


    protected AbstractInputOutputProgressMenu(MenuType<? extends AbstractInputOutputProgressMenu> menu, int syncId, Inventory playerInventory, int inventorySize, BlockEntity entity, SimpleContainerData blockEntityData, Slot[] slots) {
        super(menu, syncId);
        checkContainerSize(((Container) entity), inventorySize);
        this.inventory = ((Container) entity);
        inventory.startOpen(playerInventory.player);
        this.simpleContainerData = blockEntityData;

        for (Slot slot : slots) {
            this.addSlot(slot);
        }

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addDataSlots(blockEntityData);
    }

    public boolean isCrafting() {
        return simpleContainerData.get(0) > 0;
    }

    public int getScaledProgress() {
        int progress = this.simpleContainerData.get(0);
        int maxProgress = this.simpleContainerData.get(1);
        int progressArrowSize = 26;
        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    private void addPlayerInventory(Container playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Container playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack originalStack = slot.getItem();
            newStack = originalStack.copy();
            if (index < this.inventory.getContainerSize()) {
                if (!this.moveItemStackTo(originalStack, this.inventory.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(originalStack, 0, this.inventory.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }
            if (originalStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return newStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.inventory.stillValid(player);
    }
}
