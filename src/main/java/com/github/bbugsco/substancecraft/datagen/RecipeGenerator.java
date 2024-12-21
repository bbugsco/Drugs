package com.github.bbugsco.substancecraft.datagen;

import com.github.bbugsco.substancecraft.SubstanceCraft;
import com.github.bbugsco.substancecraft.block.SubstanceCraftBlocks;
import com.github.bbugsco.substancecraft.items.SubstanceCraftItems;
import com.github.bbugsco.substancecraft.recipe.builder.AirExtractorRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.builder.CatalyticReformerRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.builder.ElectrolysisRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.builder.FermentationTankRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.builder.HeatedMixerRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.builder.MixerRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.builder.OxidizerRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.recipes.AirExtractorRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.CatalyticReformerRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.ElectrolysisRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.FermentationTankRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.HashPressRecipe;
import com.github.bbugsco.substancecraft.recipe.builder.HashPressRecipeBuilder;
import com.github.bbugsco.substancecraft.recipe.recipes.HeatedMixerRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.MixerRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.OxidizerRecipe;
import com.github.bbugsco.substancecraft.recipe.recipes.RefineryRecipe;
import com.github.bbugsco.substancecraft.recipe.builder.RefineryRecipeBuilder;
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
                        SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.HASH_PRESS))
                .pattern("121")
                .pattern(" 3 ")
                .pattern("111")
                .define('1', Items.SMOOTH_STONE_SLAB)
                .define('2', Items.REDSTONE_BLOCK)
                .define('3', Items.PISTON)
                .unlockedBy("has_item", has(Items.SMOOTH_STONE_SLAB))
                .unlockedBy("has_item", has(Items.REDSTONE_BLOCK))
                .unlockedBy("has_item", has(Items.PISTON))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "hash_press_craft"));

        ShapedRecipeBuilder.shaped(
                        RecipeCategory.MISC,
                        SubstanceCraftItems.EMPTY_DAB_RIG)
                .pattern("1  ")
                .pattern("121")
                .pattern(" 1 ")
                .define('1', Items.GLASS)
                .define('2', Items.GLASS_BOTTLE)
                .unlockedBy("has_item", has(Items.GLASS))
                .unlockedBy("has_item", has(Items.GLASS_BOTTLE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "dab_rig"));

        ShapedRecipeBuilder.shaped(
                        RecipeCategory.MISC,
                        SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.REFINERY))
                .pattern("   ")
                .pattern("212")
                .pattern(" 2 ")
                .define('1', Items.CAULDRON)
                .define('2', Items.IRON_INGOT)
                .unlockedBy("has_item", has(Items.CAULDRON))
                .unlockedBy("has_item", has(Items.IRON_INGOT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refinery"));

        HashPressRecipeBuilder.press(
                        Ingredient.of(SubstanceCraftItems.MARIJUANA),
                        SubstanceCraftItems.HASH,
                        1000,
                        HashPressRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.MARIJUANA))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "press_hash"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.OIL_SHALE)),
                        SubstanceCraftItems.OIL,
                        List.of(new ItemStack(SubstanceCraftItems.NATURAL_GAS, 40 >> 1)),
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.OIL_SHALE)))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_oil"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(SubstanceCraftItems.OIL),
                        SubstanceCraftItems.PETROLEUM_NAPHTHA,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.OIL))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_naphtha"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(SubstanceCraftItems.OIL),
                        SubstanceCraftItems.KEROSENE,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.OIL))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_kerosene"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(SubstanceCraftItems.OIL),
                        SubstanceCraftItems.GASOLINE,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.OIL))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_gasoline"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(SubstanceCraftItems.OIL),
                        SubstanceCraftItems.DIPHENHYDRAMINE,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.OIL))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_dph"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(SubstanceCraftItems.OIL),
                        SubstanceCraftItems.KETAMINE,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.OIL))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_ketamine"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(SubstanceCraftItems.NATURAL_GAS),
                        SubstanceCraftItems.METHANOL,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.NATURAL_GAS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_methanol"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(SubstanceCraftItems.NATURAL_GAS),
                        SubstanceCraftItems.ETHANE,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.NATURAL_GAS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_ethane"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(SubstanceCraftItems.NATURAL_GAS),
                        SubstanceCraftItems.BUTANE,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.NATURAL_GAS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_butane"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(SubstanceCraftItems.NATURAL_GAS),
                        SubstanceCraftItems.PROPANE,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.NATURAL_GAS))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_propane"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(SubstanceCraftItems.PROPANE),
                        SubstanceCraftItems.PROPYLENE,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.PROPANE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_propylene"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(SubstanceCraftItems.ETHANE),
                        SubstanceCraftItems.ETHYLENE,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.ETHANE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_ethylene"));

        RefineryRecipeBuilder.refine(
                        Ingredient.of(SubstanceCraftItems.OIL),
                        SubstanceCraftItems.DIESEL,
                        1000,
                        RefineryRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.ETHANE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refine_diesel"));

        CatalyticReformerRecipeBuilder.reform(
                        Ingredient.of(SubstanceCraftItems.PETROLEUM_NAPHTHA),
                        SubstanceCraftItems.BENZENE,
                        1000,
                        CatalyticReformerRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.PETROLEUM_NAPHTHA))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "reform_benzene"));

        CatalyticReformerRecipeBuilder.reform(
                        Ingredient.of(SubstanceCraftItems.PETROLEUM_NAPHTHA),
                        SubstanceCraftItems.TOLUENE,
                        1000,
                        CatalyticReformerRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.PETROLEUM_NAPHTHA))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "reform_toluene"));

        OxidizerRecipeBuilder.oxidize(
                        Ingredient.of(SubstanceCraftItems.METHANOL),
                        SubstanceCraftItems.FORMALDEHYDE,
                        1000,
                        OxidizerRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.METHANOL))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "oxidize_formaldehyde"));

        ElectrolysisRecipeBuilder.electrolysis(
                        Ingredient.of(SubstanceCraftItems.BRINE),
                        SubstanceCraftItems.SODIUM_HYDROXIDE,
                        List.of(new ItemStack(SubstanceCraftItems.CHLORINE, 30 >> 1), new ItemStack(SubstanceCraftItems.HYDROGEN, 30 >> 1)),
                        1000,
                        ElectrolysisRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.BRINE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "electrolysis_brine"));

        ElectrolysisRecipeBuilder.electrolysis(
                        Ingredient.of(Items.POTION),
                        SubstanceCraftItems.HYDROGEN,
                        List.of(new ItemStack(SubstanceCraftItems.OXYGEN, 50 >> 1)),
                        1000,
                        ElectrolysisRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.BRINE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "electrolysis_water"));

        AirExtractorRecipeBuilder.extract(
                        Ingredient.of(Items.GLASS_BOTTLE),
                        SubstanceCraftItems.NITROGEN,
                        600,
                        AirExtractorRecipe::new
                )
                .unlockedBy("has_item", has(Items.GLASS_BOTTLE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "extract_nitrogen"));

        AirExtractorRecipeBuilder.extract(
                        Ingredient.of(Items.GLASS_BOTTLE),
                        SubstanceCraftItems.OXYGEN,
                        1200,
                        AirExtractorRecipe::new
                )
                .unlockedBy("has_item", has(Items.GLASS_BOTTLE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "extract_oxygen"));

        MixerRecipeBuilder.mix(
                        List.of(Ingredient.of(SubstanceCraftItems.SALT), Ingredient.of(Items.POTION)),
                        SubstanceCraftItems.BRINE,
                        800,
                        MixerRecipe::new
                )
                .unlockedBy("has_item", has(Items.POTION))
                .unlockedBy("has_item", has(SubstanceCraftItems.SALT))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mix_brine"));


        HeatedMixerRecipeBuilder.mix(
                        List.of(Ingredient.of(SubstanceCraftItems.AMMONIA), Ingredient.of(SubstanceCraftItems.METHANOL)),
                        SubstanceCraftItems.METHYLAMINE,
                        800,
                        HeatedMixerRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.AMMONIA))
                .unlockedBy("has_item", has(SubstanceCraftItems.METHANOL))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mix_methylamine"));

        HeatedMixerRecipeBuilder.mix(
                        List.of(Ingredient.of(SubstanceCraftItems.CHLORINE), Ingredient.of(SubstanceCraftItems.METHANE)),
                        SubstanceCraftItems.CHLOROFORM,
                        800,
                        HeatedMixerRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.CHLORINE))
                .unlockedBy("has_item", has(SubstanceCraftItems.METHANE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mix_chloroform"));

        HeatedMixerRecipeBuilder.mix(
                        List.of(Ingredient.of(SubstanceCraftItems.CHLORINE), Ingredient.of(SubstanceCraftItems.HYDROGEN), Ingredient.of(Items.POTION)),
                        SubstanceCraftItems.HYDROCHLORIC_ACID,
                        800,
                        HeatedMixerRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.CHLORINE))
                .unlockedBy("has_item", has(SubstanceCraftItems.HYDROCHLORIC_ACID))
                .unlockedBy("has_item", has(Items.POTION))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mix_hcl"));

        HeatedMixerRecipeBuilder.mix(
                        List.of(Ingredient.of(SubstanceCraftItems.NITROGEN), Ingredient.of(SubstanceCraftItems.HYDROGEN)),
                        SubstanceCraftItems.AMMONIA,
                        800,
                        HeatedMixerRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.CHLORINE))
                .unlockedBy("has_item", has(SubstanceCraftItems.METHANE))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mix_ammonia"));

        FermentationTankRecipeBuilder.ferment(
                        List.of(Ingredient.of(SubstanceCraftItems.YEAST), Ingredient.of(SubstanceCraftItems.CORN)),
                        SubstanceCraftItems.ETHANOL,
                        2000,
                        FermentationTankRecipe::new
                )
                .unlockedBy("has_item", has(SubstanceCraftItems.YEAST))
                .unlockedBy("has_item", has(SubstanceCraftItems.CORN))
                .save(exporter, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "ferment_ethanol"));

    }

    @Override
    public @NotNull String getName() {
        return "";
    }

}