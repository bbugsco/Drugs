package com.github.bbugsco.drugs.recipe;

import com.github.bbugsco.drugs.Drugs;
import com.github.bbugsco.drugs.recipe.recipes.CatalyticReformerRecipe;
import com.github.bbugsco.drugs.recipe.recipes.ElectrolysisRecipe;
import com.github.bbugsco.drugs.recipe.recipes.HashPressRecipe;
import com.github.bbugsco.drugs.recipe.recipes.OxidizerRecipe;
import com.github.bbugsco.drugs.recipe.recipes.RefineryRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class DrugsRecipes {

    public static void registerRecipes() {
        Drugs.LOGGER.info("Registering Mod Recipes for " + Drugs.MOD_ID);

        Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, HashPressRecipe.ID), HashPressRecipe.Type.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID,  HashPressRecipe.ID), HashPressRecipe.Serializer.INSTANCE);

        Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, RefineryRecipe.ID), RefineryRecipe.Type.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, RefineryRecipe.ID), RefineryRecipe.Serializer.INSTANCE);

        Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, ElectrolysisRecipe.ID), ElectrolysisRecipe.Type.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID,  ElectrolysisRecipe.ID), ElectrolysisRecipe.Serializer.INSTANCE);

        Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, OxidizerRecipe.ID), OxidizerRecipe.Type.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID,  OxidizerRecipe.ID), OxidizerRecipe.Serializer.INSTANCE);

        Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, CatalyticReformerRecipe.ID), CatalyticReformerRecipe.Type.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID,  CatalyticReformerRecipe.ID), CatalyticReformerRecipe.Serializer.INSTANCE);

    }

}
