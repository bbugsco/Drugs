package com.github.bbugsco.substancecraft.recipe.builder;

import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipe;
import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.recipes.AirExtractorRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class AirExtractorRecipeBuilder extends OneInputRecipeBuilder {

    protected AirExtractorRecipeBuilder(Ingredient ingredient, ItemLike result, List<ItemStack> byproducts, int time, OneInputRecipe.Factory<? extends OneInputRecipe> factory) {
        super(ingredient, result, byproducts, time, factory);
    }

    public static AirExtractorRecipeBuilder extract(Ingredient ingredient, ItemLike result, List<ItemStack> byproducts, int time, OneInputRecipe.Factory<AirExtractorRecipe> factory) {
        return new AirExtractorRecipeBuilder(ingredient, result, byproducts, time, factory);
    }

    public static AirExtractorRecipeBuilder extract(Ingredient ingredient, ItemLike result, int time, OneInputRecipe.Factory<AirExtractorRecipe> factory) {
        return new AirExtractorRecipeBuilder(ingredient, result, null, time, factory);
    }

}
