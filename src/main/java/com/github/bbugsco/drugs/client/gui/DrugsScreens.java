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
        Drugs.LOGGER.info("Registering Screens for " + Drugs.MOD_ID);
    }

}
