package com.github.bbugsco.drugs.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class SingleInputTimedRecipe extends SingleItemRecipe {

    private final ItemStack result;
    private final Ingredient ingredient;
    protected final int time;

    public SingleInputTimedRecipe(
            RecipeType<? extends SingleInputTimedRecipe> type,
            RecipeSerializer<? extends SingleInputTimedRecipe> serializer, String group,
            Ingredient ingredient, ItemStack result, int time)
    {
        super(type, serializer, group, ingredient, result);
        this.ingredient = ingredient;
        this.result = result;
        this.time = time;
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

    @NotNull
    public ItemStack result() {
        return result;
    }

    @NotNull
    public Ingredient input() {
        return ingredient;
    }

    @Override
    public @NotNull ItemStack assemble(SingleRecipeInput input, HolderLookup.Provider registries) {
        return result.copy();
    }

    public interface Factory<T extends SingleInputTimedRecipe> {
        T create(Ingredient ingredient, ItemStack itemStack, int time);
    }

}