package com.github.bbugsco.substancecraft.gui.one_input_menu;

import com.github.bbugsco.substancecraft.block.entity.one_input.HashPressBlockEntity;
import com.github.bbugsco.substancecraft.gui.SubstanceCraftMenus;
import com.github.bbugsco.substancecraft.recipe.recipes.HashPressRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;

public class HashPressMenu extends OneInputMenu<HashPressRecipe, HashPressBlockEntity> {

    public HashPressMenu(int syncId, Inventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.level().getBlockEntity(pos), new SimpleContainerData(3));
    }

    public HashPressMenu(int syncId, Inventory inventory, BlockEntity entity, SimpleContainerData arrayPropertyDelegate) {
        super(SubstanceCraftMenus.HASH_PRESS_MENU, syncId, inventory, entity, arrayPropertyDelegate);
    }

}