package com.github.bbugsco.drugs.client.gui;

import com.github.bbugsco.drugs.block.entity.CatalyticReformerBlockEntity;
import com.github.bbugsco.drugs.gui.CatalyticReformerMenu;
import com.github.bbugsco.drugs.recipe.recipes.CatalyticReformerRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class CatalyticReformerScreen extends OneInputScreen<CatalyticReformerRecipe, CatalyticReformerBlockEntity, CatalyticReformerMenu> {

    public CatalyticReformerScreen(CatalyticReformerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
