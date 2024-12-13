package com.github.bbugsco.drugs.client.gui.one_input;

import com.github.bbugsco.drugs.block.entity.one_input.ElectrolysisMachineBlockEntity;
import com.github.bbugsco.drugs.gui.one_input_menu.ElectrolysisMachineMenu;
import com.github.bbugsco.drugs.recipe.recipes.ElectrolysisRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

@Environment(EnvType.CLIENT)
public class ElectrolysisMachineScreen extends OneInputScreen<ElectrolysisRecipe, ElectrolysisMachineBlockEntity, ElectrolysisMachineMenu> {

    public ElectrolysisMachineScreen(ElectrolysisMachineMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
