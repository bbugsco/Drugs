package com.github.bbugsco.drugs.datagen;

import com.github.bbugsco.drugs.block.DrugsBlocks;
import com.github.bbugsco.drugs.block.blocks.plants.MarijuanaPlant;
import com.github.bbugsco.drugs.items.DrugsItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TexturedModel;

public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createCrossBlock(DrugsBlocks.MARIJUANA_PLANT, BlockModelGenerators.TintState.NOT_TINTED, MarijuanaPlant.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        blockStateModelGenerator.createHorizontallyRotatedBlock(DrugsBlocks.REFINERY, TexturedModel.ORIENTABLE);
        blockStateModelGenerator.createTrivialCube(DrugsBlocks.OIL_SHALE);
        blockStateModelGenerator.createHorizontallyRotatedBlock(DrugsBlocks.ELECTROLYSIS_MACHINE, TexturedModel.ORIENTABLE);
        blockStateModelGenerator.createHorizontallyRotatedBlock(DrugsBlocks.OXIDATION_MACHINE, TexturedModel.ORIENTABLE);
        blockStateModelGenerator.createHorizontallyRotatedBlock(DrugsBlocks.CATALYTIC_REFORMER, TexturedModel.ORIENTABLE);
        blockStateModelGenerator.createHorizontallyRotatedBlock(DrugsBlocks.AIR_EXTRACTOR, TexturedModel.ORIENTABLE);
        blockStateModelGenerator.createTrivialCube(DrugsBlocks.SALT);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(DrugsItems.MARIJUANA_TRIM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.MARIJUANA, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.HASH, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.DAB_RIG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.EMPTY_DAB_RIG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.DIPHENHYDRAMINE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.KETAMINE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.OIL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.PETROLEUM_NAPHTHA, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.KEROSENE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.GASOLINE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.METHANOL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.FORMALDEHYDE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.CHLOROFORM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.BENZENE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.TOLUENE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.SALT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.BRINE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.SODIUM_HYDROXIDE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.METHANE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.CHLORINE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.HYDROGEN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.NITROGEN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.OXYGEN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.NATURAL_GAS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.PROPANE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.ETHANE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.BUTANE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.METHYLAMINE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.ETHYLENE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DrugsItems.PROPYLENE, ModelTemplates.FLAT_ITEM);
    }

}
