package com.github.bbugsco.drugs.recipe.recipes;

import com.github.bbugsco.drugs.block.DrugsBlocks;
import com.github.bbugsco.drugs.recipe.generic.OneInputRecipe;
import com.github.bbugsco.drugs.recipe.generic.OneInputRecipeSerializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AirExtractorRecipe extends OneInputRecipe {

    public static final String ID = "air_extractor";

    public AirExtractorRecipe(Ingredient ingredient, ItemStack result, List<ItemStack> byproducts, int time) {
        super(AirExtractorRecipe.Type.INSTANCE, AirExtractorRecipe.Serializer.INSTANCE, ID, ingredient, result, byproducts, time);
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(DrugsBlocks.CATALYTIC_REFORMER);
    }

    public static class Type implements RecipeType<AirExtractorRecipe> {
        public static final AirExtractorRecipe.Type INSTANCE = new AirExtractorRecipe.Type();
    }

    public static class Serializer {
        public static final RecipeSerializer<AirExtractorRecipe> INSTANCE = new OneInputRecipeSerializer<>(AirExtractorRecipe::new);
    }

}
