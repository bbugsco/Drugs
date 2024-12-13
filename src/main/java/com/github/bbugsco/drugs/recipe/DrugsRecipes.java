package com.github.bbugsco.drugs.recipe;

import com.github.bbugsco.drugs.Drugs;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class DrugsRecipes {

    public static void registerRecipes() {
        Drugs.LOGGER.info("Registering Mod Recipes for "  + Drugs.MOD_ID);
        registerSingleInputRecipeTimed("hash_press");
        registerSingleInputRecipeTimed("refinery");
        registerSingleInputRecipeTimed("oxidizer");
        registerSingleInputRecipeTimed("electrolysis");

        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, RefineryRecipe.Serializer.ID), RefineryRecipe.Serializer.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, RefineryRecipe.Type.ID), RefineryRecipe.Type.INSTANCE);
    }

    private static void registerSingleInputRecipeTimed(String id) {
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, id), AbstractSingleInputTimedRecipe.Serializer.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, id), AbstractSingleInputTimedRecipe.Type.INSTANCE);
    }

}
