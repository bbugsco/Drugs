package com.github.bbugsco.drugs.recipe.generic;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MultipleInputTimedRecipe implements Recipe<MultipleItemInput> {

    private final RecipeType<? extends MultipleInputTimedRecipe> type;
    private final RecipeSerializer<? extends MultipleInputTimedRecipe> serializer;

    protected final ItemStack result;
    protected final List<Ingredient> ingredients;
    protected final int time;

    public MultipleInputTimedRecipe
            (RecipeType<? extends MultipleInputTimedRecipe> type,
             RecipeSerializer<? extends MultipleInputTimedRecipe> serializer,
             ItemStack result, List<Ingredient> ingredients, int time)
    {
        this.type = type;
        this.serializer = serializer;
        this.result = result;
        this.ingredients = ingredients;
        this.time = time;
    }

    @NotNull
    public ItemStack result() {
        return result;
    }

    public int time() {
        return time;
    }

    @Override
    public boolean matches(MultipleItemInput input, Level level) {
        if (level.isClientSide()) return false;
        if (input.size() != ingredients.size()) return false;
        for (int i = 0; i < input.size(); i++) {
            boolean inputInIngredients = false;
            for (int j = i; j < input.size(); j++) {
                if (ingredients.get(i).test(input.getItem(i))) {
                    inputInIngredients = true;
                    break;
                }
            }
            if (!inputInIngredients) return false;
        }
        return true;
    }

    @Override
    public @NotNull ItemStack assemble(MultipleItemInput input, HolderLookup.Provider registries) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.Provider registries) {
        return result;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return serializer;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return type;
    }

    public interface Factory<T extends MultipleInputTimedRecipe> {
        T create(List<Ingredient> ingredient, ItemStack itemStack, int time);
    }

}
