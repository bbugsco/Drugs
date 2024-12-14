package com.github.bbugsco.drugs.client.gui;

import com.github.bbugsco.drugs.block.entity.ElectrolysisMachineBlockEntity;
import com.github.bbugsco.drugs.gui.ElectrolysisMachineMenu;
import com.github.bbugsco.drugs.recipe.recipes.ElectrolysisRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ElectrolysisMachineScreen extends OneInputScreen<ElectrolysisRecipe, ElectrolysisMachineBlockEntity, ElectrolysisMachineMenu> {

    public ElectrolysisMachineScreen(ElectrolysisMachineMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
