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

public class HashPressRecipe extends OneInputRecipe {

    public static final String ID = "hash_press";

    public HashPressRecipe(Ingredient ingredient, ItemStack result, List<ItemStack> byproducts, int time) {
        super(Type.INSTANCE, Serializer.INSTANCE, "hash_press", ingredient, result, byproducts, time);
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(DrugsBlocks.HASH_PRESS);
    }

    public static class Type implements RecipeType<HashPressRecipe> {
        public static final Type INSTANCE = new Type();
    }

    public static class Serializer {
        public static final RecipeSerializer<HashPressRecipe> INSTANCE = new OneInputRecipeSerializer<>(HashPressRecipe::new);
    }

}
