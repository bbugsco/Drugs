package com.github.bbugsco.drugs.recipe.builder;

import com.github.bbugsco.drugs.recipe.generic.SingleInputTimedByproductRecipe;
import com.github.bbugsco.drugs.recipe.generic.SingleInputTimedByproductRecipeBuilder;
import com.github.bbugsco.drugs.recipe.recipes.ElectrolysisRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;

public class ElectrolysisRecipeBuilder extends SingleInputTimedByproductRecipeBuilder {

    protected ElectrolysisRecipeBuilder(ItemLike result, Ingredient ingredient, List<ItemStack> byproducts, int time, SingleInputTimedByproductRecipe.Factory<? extends SingleInputTimedByproductRecipe> factory) {
        super(ingredient, result, byproducts, time, factory);
    }

    public static ElectrolysisRecipeBuilder electrolysis(Ingredient ingredient, ItemLike result, List<ItemStack> byproducts, int time, SingleInputTimedByproductRecipe.Factory<ElectrolysisRecipe> factory) {
        return new ElectrolysisRecipeBuilder(result, ingredient, byproducts, time, factory);
    }

}
