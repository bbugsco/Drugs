package com.github.bbugsco.drugs.recipe.recipes;

import com.github.bbugsco.drugs.block.DrugsBlocks;
import com.github.bbugsco.drugs.recipe.generic.SingleInputSerializer;
import com.github.bbugsco.drugs.recipe.generic.SingleInputTimedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;

public class CatalyticReformerRecipe extends SingleInputTimedRecipe {

    public static final String ID = "catalytic_reformer";

    public CatalyticReformerRecipe(Ingredient ingredient, ItemStack result, int time) {
        super(Type.INSTANCE, Serializer.INSTANCE, ID, ingredient, result, time);
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(DrugsBlocks.CATALYTIC_REFORMER);
    }

    public static class Type implements RecipeType<CatalyticReformerRecipe> {
        public static final CatalyticReformerRecipe.Type INSTANCE = new CatalyticReformerRecipe.Type();
    }

    public static class Serializer {
        public static final RecipeSerializer<CatalyticReformerRecipe> INSTANCE = new SingleInputSerializer<>(CatalyticReformerRecipe::new);
    }

}
