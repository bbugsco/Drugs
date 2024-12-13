package com.github.bbugsco.drugs.datagen;

import com.github.bbugsco.drugs.Drugs;
import com.github.bbugsco.drugs.block.DrugsBlocks;
import com.github.bbugsco.drugs.items.DrugsItems;
import com.github.bbugsco.drugs.recipe.builder.AirExtractorRecipeBuilder;
import com.github.bbugsco.drugs.recipe.builder.CatalyticReformerRecipeBuilder;
import com.github.bbugsco.drugs.recipe.builder.ElectrolysisRecipeBuilder;
import com.github.bbugsco.drugs.recipe.builder.OxidizerRecipeBuilder;
import com.github.bbugsco.drugs.recipe.recipes.AirExtractorRecipe;
import com.github.bbugsco.drugs.recipe.recipes.CatalyticReformerRecipe;
import com.github.bbugsco.drugs.recipe.recipes.ElectrolysisRecipe;
import com.github.bbugsco.drugs.recipe.recipes.HashPressRecipe;
import com.github.bbugsco.drugs.recipe.builder.HashPressRecipeBuilder;
import com.github.bbugsco.drugs.recipe.recipes.OxidizerRecipe;
import com.github.bbugsco.drugs.recipe.recipes.RefineryRecipe;
import com.github.bbugsco.drugs.recipe.builder.RefineryRecipeBuilder;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.List;
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

        RefineryRecipeBuilder.refine(
                        Ingredient.of(DrugsBlocks.getBlockItem(DrugsBlocks.OIL_SHALE)),
                        DrugsItems.OIL,
                        List.of(new ItemStack(DrugsItems.NATURAL_GAS, 40 >> 1)),
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(DrugsBlocks.getBlockItem(DrugsBlocks.OIL_SHALE)))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "refine_oil"));

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
                        Ingredient.of(DrugsItems.NATURAL_GAS),
                        DrugsItems.METHANOL,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.NATURAL_GAS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "refine_methanol"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(DrugsItems.NATURAL_GAS),
                        DrugsItems.ETHANE,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.NATURAL_GAS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "refine_ethane"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(DrugsItems.NATURAL_GAS),
                        DrugsItems.BUTANE,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.NATURAL_GAS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "refine_butane"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(DrugsItems.NATURAL_GAS),
                        DrugsItems.PROPANE,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.NATURAL_GAS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "refine_propane"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(DrugsItems.PROPANE),
                        DrugsItems.PROPYLENE,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.PROPANE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "refine_propylene"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(DrugsItems.ETHANE),
                        DrugsItems.ETHYLENE,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.ETHANE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "refine_ethylene"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(DrugsItems.OIL),
                        DrugsItems.DIESEL,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.ETHANE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "refine_diesel"));

        CatalyticReformerRecipeBuilder.reform(
                        Ingredient.of(DrugsItems.PETROLEUM_NAPHTHA),
                        DrugsItems.BENZENE,
                        1000,
                        CatalyticReformerRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.PETROLEUM_NAPHTHA))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "reform_benzene"));

        CatalyticReformerRecipeBuilder.reform(
                        Ingredient.of(DrugsItems.PETROLEUM_NAPHTHA),
                        DrugsItems.TOLUENE,
                        1000,
                        CatalyticReformerRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.PETROLEUM_NAPHTHA))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "reform_toluene"));

        OxidizerRecipeBuilder.oxidize(
                        Ingredient.of(DrugsItems.METHANOL),
                        DrugsItems.FORMALDEHYDE,
                        1000,
                        OxidizerRecipe::new
                )
                .unlockedBy("has_item", has(DrugsItems.METHANOL))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "oxidize_formaldehyde"));

        ElectrolysisRecipeBuilder.electrolysis(
                Ingredient.of(DrugsItems.BRINE),
                DrugsItems.SODIUM_HYDROXIDE,
                List.of(new ItemStack(DrugsItems.CHLORINE, 30 >> 1), new ItemStack(DrugsItems.HYDROGEN, 30 >> 1)),
                1000,
                ElectrolysisRecipe::new
        )
                .unlockedBy("has_item", has(DrugsItems.BRINE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "electrolysis_brine"));

        ElectrolysisRecipeBuilder.electrolysis(
                Ingredient.of(Items.POTION),
                DrugsItems.HYDROGEN,
                List.of(new ItemStack(DrugsItems.OXYGEN, 50 >> 1)),
                1000,
                ElectrolysisRecipe::new
        )
                .unlockedBy("has_item", has(DrugsItems.BRINE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "electrolysis_water"));

        AirExtractorRecipeBuilder.extract(
                Ingredient.of(Items.GLASS_BOTTLE),
                DrugsItems.NITROGEN,
                600,
                AirExtractorRecipe::new
        )
                .unlockedBy("has_item", has(Items.GLASS_BOTTLE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "extract_nitrogen"));

        AirExtractorRecipeBuilder.extract(
                Ingredient.of(Items.GLASS_BOTTLE),
                DrugsItems.OXYGEN,
                1200,
                AirExtractorRecipe::new
        )
                .unlockedBy("has_item", has(Items.GLASS_BOTTLE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "extract_oxygen"));

    }

    @Override
    public @NotNull String getName() {
        return "";
    }

}