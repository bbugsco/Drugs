package com.github.bbugsco.drugs.recipe.recipes;


import com.github.bbugsco.drugs.blocks.DrugsBlocks;
import com.github.bbugsco.drugs.recipe.SingleInputSerializer;
import com.github.bbugsco.drugs.recipe.SingleInputTimedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;

public class HashPressRecipe extends SingleInputTimedRecipe {

    public static final String ID = "hash_press";

    public HashPressRecipe(Ingredient ingredient, ItemStack result, int time) {
        super(Type.INSTANCE, Serializer.INSTANCE, "hash_press", ingredient, result, time);
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(DrugsBlocks.HASH_PRESS);
    }

    public static class Type implements RecipeType<HashPressRecipe> {
        public static final Type INSTANCE = new Type();
    }

    public static class Serializer {
        public static final RecipeSerializer<HashPressRecipe> INSTANCE = new SingleInputSerializer<>(HashPressRecipe::new);
    }

}
