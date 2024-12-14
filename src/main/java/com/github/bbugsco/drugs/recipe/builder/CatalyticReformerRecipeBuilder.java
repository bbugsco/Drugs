package com.github.bbugsco.drugs.recipe.builder;

import com.github.bbugsco.drugs.recipe.generic.SingleInputTimedRecipe;
import com.github.bbugsco.drugs.recipe.generic.SingleInputTimedRecipeBuilder;
import com.github.bbugsco.drugs.recipe.recipes.CatalyticReformerRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class CatalyticReformerRecipeBuilder extends SingleInputTimedRecipeBuilder {

    protected CatalyticReformerRecipeBuilder(Ingredient ingredient, ItemLike result, int time, SingleInputTimedRecipe.Factory<CatalyticReformerRecipe> factory) {
        super(ingredient, result, time, factory);
    }

    public static CatalyticReformerRecipeBuilder reform(Ingredient ingredient, ItemLike result, int time, SingleInputTimedRecipe.Factory<CatalyticReformerRecipe> factory) {
        return new CatalyticReformerRecipeBuilder(ingredient, result, time, factory);
    }

}
