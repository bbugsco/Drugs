package com.github.bbugsco.drugs.client.gui;

import com.github.bbugsco.drugs.block.entity.RefineryBlockEntity;
import com.github.bbugsco.drugs.gui.RefineryMenu;
import com.github.bbugsco.drugs.recipe.recipes.RefineryRecipe;
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
