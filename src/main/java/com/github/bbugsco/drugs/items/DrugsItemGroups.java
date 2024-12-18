package com.github.bbugsco.drugs.items;

import com.github.bbugsco.drugs.Drugs;
import com.github.bbugsco.drugs.block.DrugsBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class DrugsItemGroups {

    public static CreativeModeTab DRUG_ITEMS_GROUP;

    public static void registerItemGroups() {
        Drugs.LOGGER.info("Registering Item Groups for " + Drugs.MOD_ID);

        DRUG_ITEMS_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "drugs"),
                FabricItemGroup.builder().title(Component.translatable("itemgroup.drugs.drugs"))
                        .icon(() -> new ItemStack(Items.OMINOUS_BOTTLE)).displayItems((displayContext, entries) -> {
                            entries.accept(DrugsBlocks.getBlockItem(DrugsBlocks.MARIJUANA_PLANT));
                            entries.accept(DrugsItems.MARIJUANA);
                            entries.accept(DrugsItems.MARIJUANA_TRIM);
                            entries.accept(DrugsItems.HASH);
                            entries.accept(DrugsItems.EMPTY_DAB_RIG);
                            entries.accept(DrugsItems.DAB_RIG);
                            entries.accept(DrugsBlocks.getBlockItem(DrugsBlocks.HASH_PRESS));
                            entries.accept(DrugsBlocks.getBlockItem(DrugsBlocks.REFINERY));
                            entries.accept(DrugsBlocks.getBlockItem(DrugsBlocks.CATALYTIC_REFORMER));
                            entries.accept(DrugsBlocks.getBlockItem(DrugsBlocks.ELECTROLYSIS_MACHINE));
                            entries.accept(DrugsBlocks.getBlockItem(DrugsBlocks.OXIDATION_MACHINE));
                            entries.accept(DrugsBlocks.getBlockItem(DrugsBlocks.AIR_EXTRACTOR));
                            entries.accept(DrugsBlocks.getBlockItem(DrugsBlocks.OIL_SHALE));
                            entries.accept(DrugsBlocks.getBlockItem(DrugsBlocks.SALT));
                            entries.accept(DrugsItems.DIPHENHYDRAMINE);
                            entries.accept(DrugsItems.KETAMINE);
                            entries.accept(DrugsItems.SALT);
                            entries.accept(DrugsItems.SODIUM_HYDROXIDE);
                            entries.accept(DrugsItems.OIL);
                            entries.accept(DrugsItems.PETROLEUM_NAPHTHA);
                            entries.accept(DrugsItems.KEROSENE);
                            entries.accept(DrugsItems.GASOLINE);
                            entries.accept(DrugsItems.METHANOL);
                            entries.accept(DrugsItems.FORMALDEHYDE);
                            entries.accept(DrugsItems.CHLOROFORM);
                            entries.accept(DrugsItems.TOLUENE);
                            entries.accept(DrugsItems.BENZENE);
                            entries.accept(DrugsItems.BRINE);
                            entries.accept(DrugsItems.CHLORINE);
                            entries.accept(DrugsItems.HYDROGEN);
                            entries.accept(DrugsItems.METHANE);
                            entries.accept(DrugsItems.NITROGEN);
                            entries.accept(DrugsItems.OXYGEN);
                            entries.accept(DrugsItems.NATURAL_GAS);
                            entries.accept(DrugsItems.PROPANE);
                            entries.accept(DrugsItems.ETHANE);
                            entries.accept(DrugsItems.BUTANE);
                            entries.accept(DrugsItems.METHYLAMINE);
                            entries.accept(DrugsItems.ETHYLENE);
                            entries.accept(DrugsItems.PROPYLENE);
                            entries.accept(DrugsItems.DIESEL);
                        }).build());

    }
}

