package com.github.bbugsco.drugs.recipe.recipes;

import com.github.bbugsco.drugs.blocks.DrugsBlocks;
import com.github.bbugsco.drugs.recipe.SingleInputSerializer;
import com.github.bbugsco.drugs.recipe.SingleInputTimedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;

public class OxidizerRecipe extends SingleInputTimedRecipe {

    public static final String ID = "oxidizer";

    public OxidizerRecipe(Ingredient ingredient, ItemStack result, int time) {
        super(Type.INSTANCE, Serializer.INSTANCE, ID, ingredient, result, time);
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(DrugsBlocks.OXIDATION_MACHINE);
    }

    public interface RecipeFactory<OxidizerRecipe> {
        OxidizerRecipe create(Ingredient input, ItemStack result, int time);
    }

    public static class Type implements RecipeType<OxidizerRecipe> {
        public static final Type INSTANCE = new Type();
    }

    public static class Serializer {
        public static final RecipeSerializer<OxidizerRecipe> INSTANCE = new SingleInputSerializer<>(OxidizerRecipe::new);
    }

}
