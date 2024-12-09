package com.github.bbugsco.drugs.gui;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.block.entity.BlockEntity;

public class HashPressMenu extends AbstractInputOutputProgressMenu {

    public HashPressMenu(int syncId, Inventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.level().getBlockEntity(pos), new SimpleContainerData(2));
    }

    public HashPressMenu(int syncId, Inventory inventory, BlockEntity entity, SimpleContainerData arrayPropertyDelegate) {
        super(DrugsMenus.HASH_PRESS_MENU, syncId, inventory, 2, entity, arrayPropertyDelegate, new Slot[]{new Slot((Container) entity, 0, 80, 11), new Slot((Container) entity, 1, 80, 59)});
    }

}
