package com.github.bbugsco.drugs.client.gui;

import com.github.bbugsco.drugs.Drugs;
import com.github.bbugsco.drugs.gui.DrugsMenus;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.MenuScreens;

@Environment(EnvType.CLIENT)
public class DrugsScreens {

    public static void registerScreens() {
        MenuScreens.register(DrugsMenus.HASH_PRESS_MENU, HashPressScreen::new);
        MenuScreens.register(DrugsMenus.REFINERY_MENU, RefineryScreen::new);
        MenuScreens.register(DrugsMenus.OXIDIZER_MENU, OxidizerScreen::new);
        MenuScreens.register(DrugsMenus.ELECTROLYSIS_MACHINE, ElectrolysisMachineScreen::new);
        MenuScreens.register(DrugsMenus.CATALYTIC_REFORMER_MENU, CatalyticReformerScreen::new);
        Drugs.LOGGER.info("Registering Screens for " + Drugs.MOD_ID);
    }

}
