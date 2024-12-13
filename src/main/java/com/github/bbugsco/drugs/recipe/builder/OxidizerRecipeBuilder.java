package com.github.bbugsco.drugs.recipe.builder;

import com.github.bbugsco.drugs.recipe.recipes.OxidizerRecipe;
import com.github.bbugsco.drugs.recipe.SingleInputTimedRecipe;
import com.github.bbugsco.drugs.recipe.SingleInputTimedRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class OxidizerRecipeBuilder extends SingleInputTimedRecipeBuilder {

    protected OxidizerRecipeBuilder(ItemLike result, Ingredient ingredient, int time, SingleInputTimedRecipe.Factory<OxidizerRecipe> factory) {
        super(result, ingredient, time, factory);
    }

    public static OxidizerRecipeBuilder oxidize(Ingredient ingredient, ItemLike result, int time, SingleInputTimedRecipe.Factory<OxidizerRecipe> factory) {
        return new OxidizerRecipeBuilder(result, ingredient, time, factory);
    }

}
