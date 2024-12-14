package com.github.bbugsco.drugs.client.gui;

import com.github.bbugsco.drugs.gui.OxidizerMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class OxidizerScreen extends InputOutputProgressScreen<OxidizerMenu> {

    public OxidizerScreen(OxidizerMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

}
