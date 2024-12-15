package com.github.bbugsco.drugs.items;

import com.github.bbugsco.drugs.Drugs;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;

public class DrugsItems {

    public static final Item MARIJUANA = registerItem("marijuana", new Item(new Item.Properties()));
    public static final Item MARIJUANA_TRIM = registerItem("marijuana_trim", new Item(new Item.Properties()));
    public static final Item HASH = registerItem("hash", new Item(new Item.Properties()));
    public static final Item EMPTY_DAB_RIG = registerItem("empty_dab_rig", new DabRig.EmptyDabRigItem(new Item.Properties()));
    public static final Item DAB_RIG = registerItem("dab_rig", new DabRig.DabRigItem(new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().nutrition(-1).build())));
    public static final Item DIPHENHYDRAMINE = registerItem("diphenhydramine", new SimpleDrugs.Diphenhydramine(new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().build())));
    public static final Item KETAMINE = registerItem("ketamine", new SimpleDrugs.Ketamine(new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().build())));
    public static final Item OIL = registerItem("oil", new Item(new Item.Properties()));
    public static final Item PETROLEUM_NAPHTHA = registerItem("petroleum_naphtha", new Item(new Item.Properties()));
    public static final Item KEROSENE = registerItem("kerosene", new Item(new Item.Properties()));
    public static final Item GASOLINE = registerItem("gasoline", new Item(new Item.Properties()));
    public static final Item METHANOL = registerItem("methanol", new Item(new Item.Properties()));
    public static final Item BENZENE = registerItem("benzene", new Item(new Item.Properties()));
    public static final Item CHLOROFORM = registerItem("chloroform", new Item(new Item.Properties()));
    public static final Item FORMALDEHYDE = registerItem("formaldehyde", new Item(new Item.Properties()));
    public static final Item TOLUENE = registerItem("toluene", new Item(new Item.Properties()));
    public static final Item SALT = registerItem("salt", new Item(new Item.Properties()));
    public static final Item BRINE = registerItem("brine", new Item(new Item.Properties()));
    public static final Item SODIUM_HYDROXIDE = registerItem("sodium_hydroxide", new Item(new Item.Properties()));
    public static final Item HYDROGEN = registerItem("hydrogen", new Item(new Item.Properties()));
    public static final Item CHLORINE = registerItem("chlorine", new Item(new Item.Properties()));
    public static final Item METHANE = registerItem("methane", new Item(new Item.Properties()));
    public static final Item NITROGEN = registerItem("nitrogen", new Item(new Item.Properties()));
    public static final Item OXYGEN = registerItem("oxygen", new Item(new Item.Properties()));
    public static final Item NATURAL_GAS = registerItem("natural_gas", new Item(new Item.Properties()));
    public static final Item ETHANE = registerItem("ethane", new Item(new Item.Properties()));
    public static final Item PROPANE = registerItem("propane", new Item(new Item.Properties()));
    public static final Item BUTANE = registerItem("butane", new Item(new Item.Properties()));
    public static final Item METHYLAMINE = registerItem("methylamine", new Item(new Item.Properties()));
    public static final Item ETHYLENE = registerItem("ethylene", new Item(new Item.Properties()));
    public static final Item PROPYLENE = registerItem("propylene", new Item(new Item.Properties()));
    public static final Item WATER = PotionContents.createItemStack(Items.POTION, Potions.WATER).getItem();

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Drugs.LOGGER.info("Registering Mod Items for " + Drugs.MOD_ID);
    }

}
