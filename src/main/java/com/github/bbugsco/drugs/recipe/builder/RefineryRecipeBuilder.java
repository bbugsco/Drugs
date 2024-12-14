package com.github.bbugsco.drugs.recipe.builder;

import com.github.bbugsco.drugs.recipe.recipes.RefineryRecipe;
import com.github.bbugsco.drugs.recipe.generic.SingleInputTimedRecipe;
import com.github.bbugsco.drugs.recipe.generic.SingleInputTimedRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class RefineryRecipeBuilder extends SingleInputTimedRecipeBuilder {

    private RefineryRecipeBuilder(final ItemLike result, final Ingredient ingredient, int time, SingleInputTimedRecipe.Factory<RefineryRecipe> factory) {
        super(ingredient, result, time, factory);
    }

    public static RefineryRecipeBuilder refine(Ingredient ingredient, ItemLike result, int time, SingleInputTimedRecipe.Factory<RefineryRecipe> factory) {
        return new RefineryRecipeBuilder(result, ingredient, time, factory);
    }

}