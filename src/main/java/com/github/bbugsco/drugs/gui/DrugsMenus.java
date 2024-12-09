package com.github.bbugsco.drugs.gui;

import com.github.bbugsco.drugs.Drugs;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;

public class DrugsMenus {

    public static final MenuType<HashPressMenu> HASH_PRESS_MENU = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "hash_press"),
            new ExtendedScreenHandlerType<>(HashPressMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static final MenuType<RefineryMenu> REFINERY_MENU = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "refinery"),
            new ExtendedScreenHandlerType<>(RefineryMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static void registerMenus() {
        Drugs.LOGGER.info("Registering Menus for " + Drugs.MOD_ID);
    }

}
