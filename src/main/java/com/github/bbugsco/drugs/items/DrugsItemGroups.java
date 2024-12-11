package com.github.bbugsco.drugs.items;

import com.github.bbugsco.drugs.Drugs;
import com.github.bbugsco.drugs.blocks.DrugsBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class DrugsItemGroups {

    public static final CreativeModeTab DRUG_ITEMS_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "drugs"),
            FabricItemGroup.builder().title(Component.translatable("itemgroup.drugs.drugs"))
                    .icon(() -> new ItemStack(Items.OMINOUS_BOTTLE)).displayItems((displayContext, entries) -> {
                        entries.accept(DrugsItems.MARIJUANA);
                        entries.accept(DrugsItems.MARIJUANA_TRIM);
                        entries.accept(DrugsItems.EMPTY_DAB_RIG);
                        entries.accept(DrugsItems.DAB_RIG);
                        entries.accept(DrugsItems.HASH);
                        entries.accept(DrugsItems.DIPHENHYDRAMINE);
                        entries.accept(DrugsItems.KETAMINE);
                        entries.accept(DrugsBlocks.getBlockItem(DrugsBlocks.MARIJUANA_PLANT));
                        entries.accept(DrugsBlocks.getBlockItem(DrugsBlocks.HASH_PRESS));
                        entries.accept(DrugsBlocks.getBlockItem(DrugsBlocks.REFINERY));
                        entries.accept(DrugsBlocks.getBlockItem(DrugsBlocks.OIL_SHALE));
                    }).build());

    public static void registerItemGroups() {
        Drugs.LOGGER.info("Registering Item Groups for " + Drugs.MOD_ID);
    }
}

