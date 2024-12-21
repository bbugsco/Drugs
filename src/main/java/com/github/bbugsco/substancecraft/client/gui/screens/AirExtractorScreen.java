package com.github.bbugsco.substancecraft.client.gui.screens;

import com.github.bbugsco.substancecraft.block.entity.entities.AirExtractorBlockEntity;
import com.github.bbugsco.substancecraft.client.gui.OneInputScreen;
import com.github.bbugsco.substancecraft.gui.menus.AirExtractorMenu;
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
