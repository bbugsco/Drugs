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

public class MixerRecipe extends MultipleInputRecipe {

    public static final String ID = "mixer";

    public MixerRecipe(List<Ingredient> ingredients, ItemStack result, List<ItemStack> byproducts, int time) {
        super(MixerRecipe.Type.INSTANCE, MixerRecipe.Serializer.INSTANCE, ingredients, result, byproducts, time);
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(SubstanceCraftBlocks.OXIDATION_MACHINE);
    }

    public static class Type implements RecipeType<MixerRecipe> {
        public static final MixerRecipe.Type INSTANCE = new MixerRecipe.Type();
    }

    public static class Serializer {
        public static final RecipeSerializer<MixerRecipe> INSTANCE = new MultipleInputSerializer<>(MixerRecipe::new);
    }

}
