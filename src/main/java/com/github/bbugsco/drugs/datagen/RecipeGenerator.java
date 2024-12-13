package com.github.bbugsco.drugs.datagen;

import com.github.bbugsco.drugs.Drugs;
import com.github.bbugsco.drugs.blocks.DrugsBlocks;
import com.github.bbugsco.drugs.items.DrugsItems;
import com.github.bbugsco.drugs.recipe.recipes.HashPressRecipe;
import com.github.bbugsco.drugs.recipe.builder.HashPressRecipeBuilder;
import com.github.bbugsco.drugs.recipe.recipes.RefineryRecipe;
import com.github.bbugsco.drugs.recipe.builder.RefineryRecipeBuilder;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
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

        ShapedRecipeBuilder.shaped(
                        RecipeCategory.MISC,
                        DrugsBlocks.getBlockItem(DrugsBlocks.REFINERY))
                .pattern("   ")
                .pattern("212")
                .pattern(" 2 ")
                .define('1', Items.CAULDRON)
                .define('2', Items.IRON_INGOT)
                .unlockedBy("has_item", has(Items.CAULDRON))
                .unlockedBy("has_item", has(Items.IRON_INGOT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "refinery"));


        HashPressRecipeBuilder.press(
                        Ingredient.of(DrugsItems.MARIJUANA),
                        DrugsItems.HASH,
                        1000,
                        HashPressRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.MARIJUANA))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "press_hash"));

        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(DrugsBlocks.getBlockItem(DrugsBlocks.OIL_SHALE)),
                        RecipeCategory.MISC,
                        DrugsItems.OIL,
                        10,
                        1000
                )
                .unlockedBy("has_item", has(DrugsBlocks.getBlockItem(DrugsBlocks.OIL_SHALE)))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "oil"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(DrugsItems.OIL),
                        DrugsItems.PETROLEUM_NAPHTHA,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.OIL))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "refine_naphtha"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(DrugsItems.OIL),
                        DrugsItems.KEROSENE,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.OIL))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "refine_kerosene"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(DrugsItems.OIL),
                        DrugsItems.GASOLINE,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.OIL))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "refine_gasoline"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(DrugsItems.OIL),
                        DrugsItems.DIPHENHYDRAMINE,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.OIL))
                    .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "refine_dph"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(DrugsItems.OIL),
                        DrugsItems.KETAMINE,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.OIL))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "refine_ketamine"));

    RefineryRecipeBuilder.refine(
                        Ingredient.of(Items.COAL),
                        DrugsItems.METHANOL,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.OIL))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "refine_methanol"));


    }

    @Override
    public @NotNull String getName() {
        return "";
    }

}