package com.github.bbugsco.drugs.client.gui;

import com.github.bbugsco.drugs.gui.CatalyticReformerMenu;
import com.github.bbugsco.drugs.recipe.recipes.CatalyticReformerRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class CatalyticReformerScreen extends RecipeSelectionIoScreen<CatalyticReformerRecipe, CatalyticReformerMenu> {

    public CatalyticReformerScreen(CatalyticReformerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
