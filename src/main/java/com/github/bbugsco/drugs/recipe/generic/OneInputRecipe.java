package com.github.bbugsco.drugs.recipe.generic;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OneInputRecipe extends SingleItemRecipe {

    private final ItemStack result;
    private final Ingredient ingredient;
    private final List<ItemStack> byproducts;
    private final int time;

    public OneInputRecipe(
            RecipeType<? extends OneInputRecipe> type,
            RecipeSerializer<? extends OneInputRecipe> serializer,
            String group, Ingredient ingredient, ItemStack result, List<ItemStack> byproducts, int time)
    {
        super(type, serializer, group, ingredient, result);
        this.ingredient = ingredient;
        this.result = result;
        this.byproducts = byproducts != null ? byproducts : List.of();
        this.time = time;
    }

    @NotNull
    public Ingredient getInput() {
        return ingredient;
    }

    @NotNull
    public ItemStack getResult() {
        return result;
    }

    @NotNull
    public List<ItemStack> getByproducts() {
        return byproducts;
    }

    public int getTime() {
        return this.time;
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        if (level.isClientSide()) {
            return false;
        }
        return ingredient.test(input.getItem(0));
    }

    @Override
    public @NotNull ItemStack assemble(SingleRecipeInput input, HolderLookup.Provider registries) {
        return result.copy();
    }


    public interface Factory<T extends OneInputRecipe> {
        T create(Ingredient ingredient, ItemStack result, List<ItemStack> byproducts, int time);
    }

}
