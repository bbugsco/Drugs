package com.github.bbugsco.drugs.client.gui.one_input;

import com.github.bbugsco.drugs.block.entity.one_input.OxidizerBlockEntity;
import com.github.bbugsco.drugs.gui.one_input_menu.OxidizerMenu;
import com.github.bbugsco.drugs.recipe.recipes.OxidizerRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class OxidizerScreen extends OneInputScreen<OxidizerRecipe, OxidizerBlockEntity, OxidizerMenu> {

    public OxidizerScreen(OxidizerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
