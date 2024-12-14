package com.github.bbugsco.drugs.gui;

import com.github.bbugsco.drugs.block.entity.CatalyticReformerBlockEntity;
import com.github.bbugsco.drugs.recipe.recipes.CatalyticReformerRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.block.entity.BlockEntity;

public class CatalyticReformerMenu extends RecipeSelectionIoMenu<CatalyticReformerRecipe, CatalyticReformerBlockEntity> {

    public CatalyticReformerMenu(int syncId, Inventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.level().getBlockEntity(pos), new SimpleContainerData(3));
    }

    public CatalyticReformerMenu(int syncId, Inventory inventory, BlockEntity entity, SimpleContainerData arrayPropertyDelegate) {
        super(DrugsMenus.CATALYTIC_REFORMER_MENU, syncId, inventory, 2, entity, arrayPropertyDelegate, new Slot[]{new Slot((Container) entity, 0, 121, 11), new Slot((Container) entity, 1, 121, 59)});
    }

}
