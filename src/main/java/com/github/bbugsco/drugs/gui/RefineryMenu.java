package com.github.bbugsco.drugs.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.block.entity.BlockEntity;

public class RefineryMenu extends AbstractInputOutputProgressMenu {

    public RefineryMenu(int syncId, Inventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.level().getBlockEntity(pos), new SimpleContainerData(2));
    }

    public RefineryMenu(int syncId, Inventory inventory, BlockEntity entity, SimpleContainerData arrayPropertyDelegate) {
        super(DrugsMenus.REFINERY_MENU, syncId, inventory, entity, arrayPropertyDelegate, 3);
        this.addSlot(new Slot(this.inventory, 0, 80, 11));
        this.addSlot(new Slot(this.inventory, 1, 80, 59));
        this.addSlot(new Slot(this.inventory, 1, 80, 87));
    }

}
