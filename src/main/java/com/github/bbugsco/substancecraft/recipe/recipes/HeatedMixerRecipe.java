package com.github.bbugsco.substancecraft.recipe.recipes;

import com.github.bbugsco.substancecraft.block.SubstanceCraftBlocks;
import com.github.bbugsco.substancecraft.recipe.generic.MultipleInputRecipe;
import com.github.bbugsco.substancecraft.recipe.generic.MultipleInputSerializer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HeatedMixerRecipe extends MultipleInputRecipe {

    public HeatedMixerRecipe(List<Ingredient> ingredients, ItemStack result, List<ItemStack> byproducts, int time) {
        super(HeatedMixerRecipe.Type.INSTANCE, HeatedMixerRecipe.Serializer.INSTANCE, ingredients, result, byproducts, time);
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(SubstanceCraftBlocks.OXIDATION_MACHINE);
    }

    public static class Type implements RecipeType<HeatedMixerRecipe> {
        public static final HeatedMixerRecipe.Type INSTANCE = new HeatedMixerRecipe.Type();
    }

    public static class Serializer {
        public static final RecipeSerializer<HeatedMixerRecipe> INSTANCE = new MultipleInputSerializer<>(HeatedMixerRecipe::new);
    }

}
