package com.github.bbugsco.drugs.recipe.generic;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;

public class SingleInputTimedByproductRecipe extends SingleInputTimedRecipe {

    private final List<ItemStack> byproducts;

    public SingleInputTimedByproductRecipe(
            RecipeType<? extends SingleInputTimedRecipe> type,
            RecipeSerializer<? extends SingleInputTimedRecipe> serializer,
            String group, Ingredient ingredient, ItemStack result, int time, List<ItemStack> byproducts)
    {
        super(type, serializer, group, ingredient, result, time);
        this.byproducts = byproducts;
    }

    public List<ItemStack> getByproducts() {
        return byproducts;
    }


    public interface Factory<T extends SingleInputTimedByproductRecipe> {
        T create(Ingredient ingredient, ItemStack itemStack, List<ItemStack> byproducts, int time);
    }

}
