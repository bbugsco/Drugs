package com.github.bbugsco.drugs.recipe.builder;

import com.github.bbugsco.drugs.recipe.recipes.ElectrolysisRecipe;
import com.github.bbugsco.drugs.recipe.SingleInputTimedRecipe;
import com.github.bbugsco.drugs.recipe.SingleInputTimedRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class ElectrolysisRecipeBuilder extends SingleInputTimedRecipeBuilder {

    protected ElectrolysisRecipeBuilder(ItemLike result, Ingredient ingredient, int time, SingleInputTimedRecipe.Factory<? extends SingleInputTimedRecipe> factory) {
        super(result, ingredient, time, factory);
    }

    public static ElectrolysisRecipeBuilder electrolysis(Ingredient ingredient, ItemLike result, int time, SingleInputTimedRecipe.Factory<ElectrolysisRecipe> factory) {
        return new ElectrolysisRecipeBuilder(result, ingredient, time, factory);
    }

}
