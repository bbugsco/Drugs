package com.github.bbugsco.drugs.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ElectrolysisMachineMenu extends InputOutputProgressMenu {

    public ElectrolysisMachineMenu(int syncId, Inventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.level().getBlockEntity(pos), new SimpleContainerData(3));
    }

    public ElectrolysisMachineMenu(int syncId, Inventory inventory, BlockEntity entity, SimpleContainerData arrayPropertyDelegate) {
        super(DrugsMenus.ELECTROLYSIS_MACHINE, syncId, inventory, 4, entity, arrayPropertyDelegate,
                new Slot[]{new Slot((Container) entity, 0, 80, 11), new Slot((Container) entity, 1, 80, 59),
                           new Slot((Container) entity, 2, 103, 59), new Slot((Container) entity, 3, 121, 59)}, 2);
    }

}
