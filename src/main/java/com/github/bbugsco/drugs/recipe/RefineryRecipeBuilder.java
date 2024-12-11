package com.github.bbugsco.drugs.recipe;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class RefineryRecipeBuilder implements RecipeBuilder {

    private final Item result;
    private final Ingredient ingredient;
    private final int time;

    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    private final RefineryRecipe.RecipeFactory<RefineryRecipe> factory;

    private RefineryRecipeBuilder(final ItemLike result, final Ingredient ingredient, int time, RefineryRecipe.RecipeFactory<RefineryRecipe> factory) {
        this.result = result.asItem();
        this.ingredient = ingredient;
        this.time = time;
        this.factory = factory;
    }

    public static RefineryRecipeBuilder refine(Ingredient ingredient, ItemLike result, int time, RefineryRecipe.RecipeFactory<RefineryRecipe> factory) {
        return new RefineryRecipeBuilder(result, ingredient, time, factory);
    }

    @Override
    public @NotNull RefineryRecipeBuilder unlockedBy(String string, Criterion<?> advancementCriterion) {
        this.criteria.put(string, advancementCriterion);
        return this;
    }

    @Override
    public @NotNull RecipeBuilder group(@Nullable String group) {
        return this;
    }

    @Override
    public @NotNull Item getResult() {
        return this.result;
    }

    @Override
    public void save(RecipeOutput exporter, ResourceLocation recipeId) {
        this.validate(recipeId);
        Advancement.Builder builder = exporter.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId)).rewards(AdvancementRewards.Builder.recipe(recipeId)).requirements(AdvancementRequirements.Strategy.OR);
        Objects.requireNonNull(builder);
        this.criteria.forEach(builder::addCriterion);
        RefineryRecipe recipeFactory = this.factory.create(this.ingredient, new ItemStack(this.result), this.time);
        exporter.accept(recipeId, recipeFactory, builder.build(recipeId.withPrefix("recipes/")));
    }

    private void validate(ResourceLocation recipeId) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipeId);
        }
    }

}