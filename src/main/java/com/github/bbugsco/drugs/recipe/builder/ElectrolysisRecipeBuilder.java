package com.github.bbugsco.drugs.recipe.builder;

import com.github.bbugsco.drugs.recipe.generic.OneInputRecipe;
import com.github.bbugsco.drugs.recipe.generic.OneInputRecipeBuilder;
import com.github.bbugsco.drugs.recipe.recipes.ElectrolysisRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class ElectrolysisRecipeBuilder extends OneInputRecipeBuilder {

    protected ElectrolysisRecipeBuilder(ItemLike result, Ingredient ingredient, List<ItemStack> byproducts, int time, OneInputRecipe.Factory<? extends OneInputRecipe> factory) {
        super(ingredient, result, byproducts, time, factory);
    }

    public static ElectrolysisRecipeBuilder electrolysis(Ingredient ingredient, ItemLike result, List<ItemStack> byproducts, int time, OneInputRecipe.Factory<ElectrolysisRecipe> factory) {
        return new ElectrolysisRecipeBuilder(result, ingredient, byproducts, time, factory);
    }

    public static ElectrolysisRecipeBuilder electrolysis(Ingredient ingredient, ItemLike result, int time, OneInputRecipe.Factory<ElectrolysisRecipe> factory) {
        return new ElectrolysisRecipeBuilder(result, ingredient, null, time, factory);
    }

}
