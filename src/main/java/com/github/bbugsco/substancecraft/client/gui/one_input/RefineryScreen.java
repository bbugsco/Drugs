package com.github.bbugsco.substancecraft.client.gui.one_input;

import com.github.bbugsco.substancecraft.block.entity.entities.RefineryBlockEntity;
import com.github.bbugsco.substancecraft.gui.one_input_menu.RefineryMenu;
import com.github.bbugsco.substancecraft.recipe.recipes.RefineryRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class RefineryScreen extends OneInputScreen<RefineryRecipe, RefineryBlockEntity, RefineryMenu> {

    public RefineryScreen(RefineryMenu handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

}
