package com.github.bbugsco.drugs.recipe.recipes;

import com.github.bbugsco.drugs.block.DrugsBlocks;
import com.github.bbugsco.drugs.recipe.generic.SingleInputSerializer;
import com.github.bbugsco.drugs.recipe.generic.SingleInputTimedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;

public class RefineryRecipe extends SingleInputTimedRecipe {

    public static final String ID = "refinery";

    public RefineryRecipe(Ingredient ingredient, ItemStack result, int time) {
        super(Type.INSTANCE, Serializer.INSTANCE, ID, ingredient, result, time);
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(DrugsBlocks.REFINERY);
    }

    public static class Type implements RecipeType<RefineryRecipe> {
        public static final Type INSTANCE = new Type();
    }

    public static class Serializer {
        public static final RecipeSerializer<RefineryRecipe> INSTANCE = new SingleInputSerializer<>(RefineryRecipe::new);
    }

}
