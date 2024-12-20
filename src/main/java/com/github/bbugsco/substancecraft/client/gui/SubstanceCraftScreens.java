package com.github.bbugsco.substancecraft.client.gui;

import com.github.bbugsco.substancecraft.SubstanceCraft;
import com.github.bbugsco.substancecraft.client.gui.one_input.AirExtractorScreen;
import com.github.bbugsco.substancecraft.client.gui.one_input.CatalyticReformerScreen;
import com.github.bbugsco.substancecraft.client.gui.one_input.ElectrolysisMachineScreen;
import com.github.bbugsco.substancecraft.client.gui.one_input.HashPressScreen;
import com.github.bbugsco.substancecraft.client.gui.one_input.OxidizerScreen;
import com.github.bbugsco.substancecraft.client.gui.one_input.RefineryScreen;
import com.github.bbugsco.substancecraft.gui.SubstanceCraftMenus;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.MenuScreens;

@Environment(EnvType.CLIENT)
public class SubstanceCraftScreens {

    public static void registerScreens() {
        MenuScreens.register(SubstanceCraftMenus.HASH_PRESS_MENU, HashPressScreen::new);
        MenuScreens.register(SubstanceCraftMenus.REFINERY_MENU, RefineryScreen::new);
        MenuScreens.register(SubstanceCraftMenus.OXIDIZER_MENU, OxidizerScreen::new);
        MenuScreens.register(SubstanceCraftMenus.ELECTROLYSIS_MACHINE, ElectrolysisMachineScreen::new);
        MenuScreens.register(SubstanceCraftMenus.CATALYTIC_REFORMER_MENU, CatalyticReformerScreen::new);
        MenuScreens.register(SubstanceCraftMenus.AIR_EXTRACTOR, AirExtractorScreen::new);
        SubstanceCraft.LOGGER.info("Registering Screens for " + SubstanceCraft.MOD_ID);
    }

}
