package com.github.bbugsco.drugs.recipe;


import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class HashPressRecipe extends AbstractSingleInputTimedRecipe {

    public HashPressRecipe(Ingredient ingredient, ItemStack result, int time) {
        super(DrugsRecipes.HASH_PRESS_RECIPE_TYPE, DrugsRecipes.HASH_PRESS_RECIPE_SERIALIZER, "hash_press", ingredient, result, time);
    }

    public interface RecipeFactory<HashPressRecipe> {
        HashPressRecipe create(Ingredient input, ItemStack result, int time);
    }

}
