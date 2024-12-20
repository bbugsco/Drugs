package com.github.bbugsco.substancecraft.recipe.recipes;

import com.github.bbugsco.substancecraft.block.SubstanceCraftBlocks;
import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipeSerializer;
import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ElectrolysisRecipe extends OneInputRecipe {

    public static final String ID = "electrolysis";

    public ElectrolysisRecipe(Ingredient ingredient, ItemStack result,  List<ItemStack> byproducts, int time) {
        super(Type.INSTANCE, Serializer.INSTANCE, ID, ingredient, result, byproducts, time);
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(SubstanceCraftBlocks.ELECTROLYSIS_MACHINE);
    }

    public static class Type implements RecipeType<ElectrolysisRecipe> {
        public static final Type INSTANCE = new Type();
    }

    public static class Serializer {
        public static final RecipeSerializer<ElectrolysisRecipe> INSTANCE = new OneInputRecipeSerializer<>(ElectrolysisRecipe::new);
    }

}
