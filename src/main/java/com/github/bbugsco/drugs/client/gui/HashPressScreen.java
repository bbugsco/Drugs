package com.github.bbugsco.drugs.client.gui;

import com.github.bbugsco.drugs.block.entity.HashPressBlockEntity;
import com.github.bbugsco.drugs.gui.HashPressMenu;
import com.github.bbugsco.drugs.recipe.recipes.HashPressRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class HashPressScreen extends OneInputScreen<HashPressRecipe, HashPressBlockEntity, HashPressMenu> {

    public HashPressScreen(HashPressMenu handler, Inventory inventory, Component title) {
        super(handler, inventory, title);
    }

}