package com.github.bbugsco.substancecraft.recipe.recipes;

import com.github.bbugsco.substancecraft.block.SubstanceCraftBlocks;
import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipe;
import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipeSerializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OxidizerRecipe extends OneInputRecipe {

    public static final String ID = "oxidizer";

    public OxidizerRecipe(Ingredient ingredient, ItemStack result, List<ItemStack> byproducts, int time) {
        super(Type.INSTANCE, Serializer.INSTANCE, ID, ingredient, result, byproducts, time);
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(SubstanceCraftBlocks.OXIDATION_MACHINE);
    }

    public static class Type implements RecipeType<OxidizerRecipe> {
        public static final Type INSTANCE = new Type();
    }

    public static class Serializer {
        public static final RecipeSerializer<OxidizerRecipe> INSTANCE = new OneInputRecipeSerializer<>(OxidizerRecipe::new);
    }

}
