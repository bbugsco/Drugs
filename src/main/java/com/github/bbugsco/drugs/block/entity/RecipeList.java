package com.github.bbugsco.drugs.block.entity;

import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleItemRecipe;

import java.util.List;

public interface RecipeList<T extends SingleItemRecipe> {

    List<RecipeHolder<T>> getRecipes();

    boolean selectsRecipe();

}
