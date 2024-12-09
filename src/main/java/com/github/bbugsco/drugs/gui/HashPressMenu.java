package com.github.bbugsco.drugs.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.block.entity.BlockEntity;

public class HashPressMenu extends AbstractInputOutputProgressMenu {

    public HashPressMenu(int syncId, Inventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.level().getBlockEntity(pos), new SimpleContainerData(2));
    }

    public HashPressMenu(int syncId, Inventory playerInventory, BlockEntity blockEntity, SimpleContainerData arrayPropertyDelegate) {
        super(DrugsMenus.HASH_PRESS_MENU, syncId, playerInventory, blockEntity, arrayPropertyDelegate, 2);
        this.addSlot(new Slot(this.inventory, 0, 80, 11));
        this.addSlot(new Slot(this.inventory, 1, 80, 59));
    }

}
