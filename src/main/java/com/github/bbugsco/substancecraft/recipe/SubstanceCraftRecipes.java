package com.github.bbugsco.substancecraft.recipe;

import com.github.bbugsco.substancecraft.SubstanceCraft;
import com.github.bbugsco.substancecraft.recipe.recipes.AirExtractorRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.CatalyticReformerRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.ElectrolysisRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.HashPressRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.OxidizerRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.RefineryRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class SubstanceCraftRecipes {

    public static void registerRecipes() {
        SubstanceCraft.LOGGER.info("Registering Mod Recipes for " + SubstanceCraft.MOD_ID);

        Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, HashPressRecipe.ID), HashPressRecipe.Type.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID,  HashPressRecipe.ID), HashPressRecipe.Serializer.INSTANCE);

        Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, RefineryRecipe.ID), RefineryRecipe.Type.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, RefineryRecipe.ID), RefineryRecipe.Serializer.INSTANCE);

        Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, ElectrolysisRecipe.ID), ElectrolysisRecipe.Type.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID,  ElectrolysisRecipe.ID), ElectrolysisRecipe.Serializer.INSTANCE);

        Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, OxidizerRecipe.ID), OxidizerRecipe.Type.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID,  OxidizerRecipe.ID), OxidizerRecipe.Serializer.INSTANCE);

        Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, CatalyticReformerRecipe.ID), CatalyticReformerRecipe.Type.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID,  CatalyticReformerRecipe.ID), CatalyticReformerRecipe.Serializer.INSTANCE);

        Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, AirExtractorRecipe.ID), AirExtractorRecipe.Type.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID,  AirExtractorRecipe.ID), AirExtractorRecipe.Serializer.INSTANCE);

    }

}
