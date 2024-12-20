package com.github.bbugsco.substancecraft.gui;

import com.github.bbugsco.substancecraft.SubstanceCraft;
import com.github.bbugsco.substancecraft.gui.one_input_menu.AirExtractorMenu;
import com.github.bbugsco.substancecraft.gui.one_input_menu.CatalyticReformerMenu;
import com.github.bbugsco.substancecraft.gui.one_input_menu.ElectrolysisMachineMenu;
import com.github.bbugsco.substancecraft.gui.one_input_menu.HashPressMenu;
import com.github.bbugsco.substancecraft.gui.one_input_menu.OxidizerMenu;
import com.github.bbugsco.substancecraft.gui.one_input_menu.RefineryMenu;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;

public class SubstanceCraftMenus {

    public static final MenuType<HashPressMenu> HASH_PRESS_MENU = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "hash_press"),
            new ExtendedScreenHandlerType<>(HashPressMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static final MenuType<RefineryMenu> REFINERY_MENU = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refinery"),
            new ExtendedScreenHandlerType<>(RefineryMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static final MenuType<CatalyticReformerMenu> CATALYTIC_REFORMER_MENU = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "catalytic_reformer"),
            new ExtendedScreenHandlerType<>(CatalyticReformerMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static final MenuType<OxidizerMenu> OXIDIZER_MENU = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "oxidizer"),
            new ExtendedScreenHandlerType<>(OxidizerMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static final MenuType<ElectrolysisMachineMenu> ELECTROLYSIS_MACHINE = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "electrolysis_machine"),
            new ExtendedScreenHandlerType<>(ElectrolysisMachineMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static final MenuType<AirExtractorMenu> AIR_EXTRACTOR = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "air_extractor"),
            new ExtendedScreenHandlerType<>(AirExtractorMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static void registerMenus() {
        SubstanceCraft.LOGGER.info("Registering Menus for " + SubstanceCraft.MOD_ID);
    }

}
