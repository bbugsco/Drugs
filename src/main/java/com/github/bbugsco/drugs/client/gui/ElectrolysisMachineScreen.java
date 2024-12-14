package com.github.bbugsco.drugs.client.gui;

import com.github.bbugsco.drugs.gui.ElectrolysisMachineMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class ElectrolysisMachineScreen extends InputOutputProgressScreen<ElectrolysisMachineMenu> {

    public ElectrolysisMachineScreen(ElectrolysisMachineMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }



}
