package com.github.bbugsco.drugs.client.gui.one_input;

import com.github.bbugsco.drugs.block.entity.one_input.CatalyticReformerBlockEntity;
import com.github.bbugsco.drugs.gui.one_input_menu.CatalyticReformerMenu;
import com.github.bbugsco.drugs.recipe.recipes.CatalyticReformerRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class CatalyticReformerScreen extends OneInputScreen<CatalyticReformerRecipe, CatalyticReformerBlockEntity, CatalyticReformerMenu> {

    public CatalyticReformerScreen(CatalyticReformerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
