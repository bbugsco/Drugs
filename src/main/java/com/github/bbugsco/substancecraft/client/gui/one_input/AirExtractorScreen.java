package com.github.bbugsco.substancecraft.client.gui.one_input;

import com.github.bbugsco.substancecraft.block.entity.one_input.AirExtractorBlockEntity;
import com.github.bbugsco.substancecraft.gui.one_input_menu.AirExtractorMenu;
import com.github.bbugsco.substancecraft.recipe.recipes.AirExtractorRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class AirExtractorScreen extends OneInputScreen<AirExtractorRecipe, AirExtractorBlockEntity, AirExtractorMenu> {

    public AirExtractorScreen(AirExtractorMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
