package com.github.bbugsco.substancecraft.block.entity;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleItemRecipe;

import java.util.List;

public interface RecipeList<T extends Recipe<?>> {

    List<RecipeHolder<T>> getRecipes();

    boolean selectsRecipe();

}
