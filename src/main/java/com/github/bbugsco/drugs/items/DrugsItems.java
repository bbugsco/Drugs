package com.github.bbugsco.drugs.items;

import com.github.bbugsco.drugs.Drugs;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

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

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Drugs.LOGGER.info("Registering Mod Items for " + Drugs.MOD_ID);
    }

}
