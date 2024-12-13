package com.github.bbugsco.drugs.recipe.generic;

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
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MultipleInputTimeRecipeBuilder implements RecipeBuilder {

    private final Item result;
    private final List<Ingredient> ingredients;
    private final int time;

    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    private final MultipleInputTimedRecipe.Factory<? extends MultipleInputTimedRecipe> factory;

    protected MultipleInputTimeRecipeBuilder(final ItemLike result, final List<Ingredient> ingredients, int time, MultipleInputTimedRecipe.Factory<? extends MultipleInputTimedRecipe> factory) {
        this.result = result.asItem();
        this.ingredients = ingredients;
        this.time = time;
        this.factory = factory;
    }

    @Override
    public @NotNull MultipleInputTimeRecipeBuilder unlockedBy(String string, Criterion<?> advancementCriterion) {
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
        MultipleInputTimedRecipe recipeFactory = this.factory.create(this.ingredients, new ItemStack(this.result), this.time);
        exporter.accept(recipeId, recipeFactory, builder.build(recipeId.withPrefix("recipes/")));
    }

    private void validate(ResourceLocation recipeId) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipeId);
        }
    }

}
