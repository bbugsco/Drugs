package com.github.bbugsco.drugs.gui;

import com.github.bbugsco.drugs.block.entity.HashPressBlockEntity;
import com.github.bbugsco.drugs.recipe.recipes.HashPressRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.block.entity.BlockEntity;

public class HashPressMenu extends OneInputMenu<HashPressRecipe, HashPressBlockEntity> {

    public HashPressMenu(int syncId, Inventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.level().getBlockEntity(pos), new SimpleContainerData(3));
    }

    public HashPressMenu(int syncId, Inventory inventory, BlockEntity entity, SimpleContainerData arrayPropertyDelegate) {
        super(DrugsMenus.HASH_PRESS_MENU, syncId, inventory, entity, arrayPropertyDelegate,
                new Slot[]{OneInputMenu.inputSlot((Container) entity), OneInputMenu.outputSlot((Container) entity)});
    }

}
