package com.github.bbugsco.drugs.recipe;

import com.github.bbugsco.drugs.Drugs;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class DrugsRecipes {

    public static final RecipeType<HashPressRecipe> HASH_PRESS_RECIPE_TYPE = registerRecipeType("hash_press");
    public static final RecipeType<RefineryRecipe> REFINERY_RECIPE_TYPE = registerRecipeType("refinery");
    public static final RecipeType<?> OXIDIZER_RECIPE_TYPE = registerRecipeType("oxidizer");
    public static final RecipeType<?> ELECTROLYSIS_RECIPE_TYPE = registerRecipeType("electrolysis");

    public static final RecipeSerializer<HashPressRecipe> HASH_PRESS_RECIPE_SERIALIZER = registerRecipeSerializer(
            "hash_press", new AbstractSingleInputTimedRecipe.Serializer<>(HashPressRecipe::new));
    public static final RecipeSerializer<RefineryRecipe> REFINERY_RECIPE_SERIALIZER = registerRecipeSerializer(
            "refinery", RefineryRecipe.Serializer.INSTANCE);
   // public static final RecipeSerializer<RefineryRecipe> OXIDIZER_RECIPE_SERIALIZER = registerRecipeSerializer(
   //         "oxidizer", new AbstractSingleInputTimedRecipe.Serializer<>();
   // public static final RecipeSerializer<RefineryRecipe> ELECTROLYSIS_RECIPE_SERIALIZER = registerRecipeSerializer(
   //         "electrolysis", new AbstractSingleInputTimedRecipe.Serializer<>());

    public static void registerRecipes() {
        Drugs.LOGGER.info("Registering Mod Recipes for " + Drugs.MOD_ID);
    }

    static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String identifier) {
        return Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceLocation.withDefaultNamespace(identifier),
        new RecipeType<T>() {
            public String toString() {
                return identifier;
            }
        });
    }

    static <S extends RecipeSerializer<T>, T extends Recipe<?>> S registerRecipeSerializer(String key, S recipeSerializer) {
        return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, key, recipeSerializer);
    }

}
