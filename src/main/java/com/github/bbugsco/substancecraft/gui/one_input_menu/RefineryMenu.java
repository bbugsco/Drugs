package com.github.bbugsco.substancecraft.gui.one_input_menu;

import com.github.bbugsco.substancecraft.block.entity.one_input.RefineryBlockEntity;
import com.github.bbugsco.substancecraft.gui.SubstanceCraftMenus;
import com.github.bbugsco.substancecraft.recipe.recipes.RefineryRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;

public class RefineryMenu extends OneInputMenu<RefineryRecipe, RefineryBlockEntity> {

    public RefineryMenu(int syncId, Inventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.level().getBlockEntity(pos), new SimpleContainerData(3));
    }

    public RefineryMenu(int syncId, Inventory inventory, BlockEntity entity, SimpleContainerData arrayPropertyDelegate) {
        super(SubstanceCraftMenus.REFINERY_MENU, syncId, inventory, entity, arrayPropertyDelegate);
    }

}
