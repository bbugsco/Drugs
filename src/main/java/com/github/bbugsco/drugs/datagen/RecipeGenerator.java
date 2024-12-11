package com.github.bbugsco.drugs.datagen;

import com.github.bbugsco.drugs.Drugs;
import com.github.bbugsco.drugs.blocks.DrugsBlocks;
import com.github.bbugsco.drugs.items.DrugsItems;
import com.github.bbugsco.drugs.recipe.HashPressRecipe;
import com.github.bbugsco.drugs.recipe.HashPressRecipeBuilder;
import com.github.bbugsco.drugs.recipe.RefineryRecipe;
import com.github.bbugsco.drugs.recipe.RefineryRecipeBuilder;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends FabricRecipeProvider {

    public RecipeGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {
        ShapedRecipeBuilder.shaped(
                        RecipeCategory.MISC,
                        DrugsBlocks.getBlockItem(DrugsBlocks.HASH_PRESS))
                .pattern("121")
                .pattern(" 3 ")
                .pattern("111")
                .define('1', Items.SMOOTH_STONE_SLAB)
                .define('2', Items.REDSTONE_BLOCK)
                .define('3', Items.PISTON)
                .unlockedBy("has_item", has(Items.SMOOTH_STONE_SLAB))
                .unlockedBy("has_item", has(Items.REDSTONE_BLOCK))
                .unlockedBy("has_item", has(Items.PISTON))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "hash_press_craft"));

        ShapedRecipeBuilder.shaped(
                        RecipeCategory.MISC,
                        DrugsItems.EMPTY_DAB_RIG)
                .pattern("1  ")
                .pattern("121")
                .pattern(" 1 ")
                .define('1', Items.GLASS)
                .define('2', Items.GLASS_BOTTLE)
                .unlockedBy("has_item", has(Items.GLASS))
                .unlockedBy("has_item", has(Items.GLASS_BOTTLE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "dab_rig"));

        HashPressRecipeBuilder.press(
                        Ingredient.of(DrugsItems.MARIJUANA),
                        DrugsItems.HASH,
                        1000,
                        HashPressRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.MARIJUANA))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "hash_press"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(Items.COAL),
                        DrugsItems.HASH,
                        100,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.MARIJUANA))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "refine1"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(Items.COAL),
                        DrugsItems.MARIJUANA_TRIM,
                        100,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.MARIJUANA))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "refine2"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(Items.COAL),
                        DrugsBlocks.getBlockItem(DrugsBlocks.MARIJUANA_PLANT),
                        100,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.MARIJUANA))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "refine3"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(Items.COAL),
                        DrugsItems.EMPTY_DAB_RIG,
                        100,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.MARIJUANA))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "refine4"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(Items.COAL),
                        DrugsItems.DAB_RIG,
                        100,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.MARIJUANA))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "refine5"));

        ItemLike[] colors = new ItemLike[]{Items.BLACK_CONCRETE, Items.WHITE_CONCRETE, Items.RED_CONCRETE,Items.ORANGE_CONCRETE, Items.YELLOW_CONCRETE, Items.LIME_CONCRETE,
                Items.GREEN_CONCRETE, Items.CYAN_CONCRETE, Items.LIGHT_BLUE_CONCRETE, Items.BLUE_CONCRETE, Items.MAGENTA_CONCRETE, Items.PURPLE_CONCRETE, Items.PINK_CONCRETE};

        for (ItemLike color : colors) {
            RefineryRecipeBuilder.refine(
                            Ingredient.of(Items.COAL),
                            color,
                            100,
                            RefineryRecipe::new
                    )
                    .unlockedBy("has_item", has(DrugsItems.MARIJUANA))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, ("refine" + "-" + color.asItem()).replace("minecraft:", "")));
        }

    }

    @Override
    public @NotNull String getName() {
        return "";
    }

}