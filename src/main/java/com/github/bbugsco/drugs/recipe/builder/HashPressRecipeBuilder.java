package com.github.bbugsco.drugs.recipe.builder;

import com.github.bbugsco.drugs.recipe.recipes.HashPressRecipe;
import com.github.bbugsco.drugs.recipe.SingleInputTimedRecipe;
import com.github.bbugsco.drugs.recipe.SingleInputTimedRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class HashPressRecipeBuilder extends SingleInputTimedRecipeBuilder {

    private HashPressRecipeBuilder(final ItemLike result, final Ingredient ingredient, int time, SingleInputTimedRecipe.Factory<HashPressRecipe> factory) {
        super(result, ingredient, time, factory);
    }

    public static HashPressRecipeBuilder press(Ingredient ingredient, ItemLike result, int time, SingleInputTimedRecipe.Factory<HashPressRecipe> factory) {
        return new HashPressRecipeBuilder(result, ingredient, time, factory);
    }

}