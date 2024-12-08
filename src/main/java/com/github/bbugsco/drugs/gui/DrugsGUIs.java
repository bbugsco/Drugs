package com.github.bbugsco.drugs.gui;

import com.github.bbugsco.drugs.Drugs;
import com.github.bbugsco.drugs.gui.menu.HashPressMenu;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;

public class DrugsGUIs {

    public static final MenuType<HashPressMenu> HASH_PRESS_MENU = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "hash_press"),
            new ExtendedScreenHandlerType<>(HashPressMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static void registerScreenHandlers() {
        Drugs.LOGGER.info("Registering GUIs for " + Drugs.MOD_ID);
    }

}
