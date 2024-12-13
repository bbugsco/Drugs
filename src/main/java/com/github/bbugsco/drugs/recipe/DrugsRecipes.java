package com.github.bbugsco.drugs.recipe;

import com.github.bbugsco.drugs.Drugs;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class DrugsRecipes {

    public static void registerRecipes() {
        Drugs.LOGGER.info("Registering Mod Recipes for " + Drugs.MOD_ID);

        Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, HashPressRecipe.ID), HashPressRecipe.Type.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID,  HashPressRecipe.ID), HashPressRecipe.Serializer.INSTANCE);

        Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, RefineryRecipe.Type.ID), RefineryRecipe.Type.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, RefineryRecipe.Serializer.ID), RefineryRecipe.Serializer.INSTANCE);

    }

}
