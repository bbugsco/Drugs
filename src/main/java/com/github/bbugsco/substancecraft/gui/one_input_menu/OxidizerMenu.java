package com.github.bbugsco.substancecraft.gui.one_input_menu;

import com.github.bbugsco.substancecraft.block.entity.one_input.OxidizerBlockEntity;
import com.github.bbugsco.substancecraft.gui.SubstanceCraftMenus;
import com.github.bbugsco.substancecraft.recipe.recipes.OxidizerRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;

public class OxidizerMenu extends OneInputMenu<OxidizerRecipe, OxidizerBlockEntity> {

    public OxidizerMenu(int syncId, Inventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.level().getBlockEntity(pos), new SimpleContainerData(3));
    }

    public OxidizerMenu(int syncId, Inventory inventory, BlockEntity entity, SimpleContainerData arrayPropertyDelegate) {
        super(SubstanceCraftMenus.OXIDIZER_MENU, syncId, inventory, entity, arrayPropertyDelegate);
    }

}
