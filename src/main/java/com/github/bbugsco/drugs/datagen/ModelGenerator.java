package com.github.bbugsco.drugs.datagen;

import com.github.bbugsco.drugs.blocks.DrugsBlocks;
import com.github.bbugsco.drugs.blocks.MarijuanaPlantBlock;
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
        blockStateModelGenerator.createCrossBlock(DrugsBlocks.MARIJUANA_PLANT, BlockModelGenerators.TintState.NOT_TINTED, MarijuanaPlantBlock.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        blockStateModelGenerator.createFurnace(DrugsBlocks.REFINERY, TexturedModel.ORIENTABLE_ONLY_TOP);
        blockStateModelGenerator.createTrivialCube(DrugsBlocks.OIL_SHALE);
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
    }


}
