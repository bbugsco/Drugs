package com.github.bbugsco.substancecraft.block.entity;

import com.github.bbugsco.substancecraft.SubstanceCraft;
import com.github.bbugsco.substancecraft.block.SubstanceCraftBlocks;
import com.github.bbugsco.substancecraft.block.entity.entities.AirExtractorBlockEntity;
import com.github.bbugsco.substancecraft.block.entity.entities.CatalyticReformerBlockEntity;
import com.github.bbugsco.substancecraft.block.entity.entities.ElectrolysisMachineBlockEntity;
import com.github.bbugsco.substancecraft.block.entity.entities.FermentationTankBlockEntity;
import com.github.bbugsco.substancecraft.block.entity.entities.HashPressBlockEntity;
import com.github.bbugsco.substancecraft.block.entity.entities.HeatedMixerBlockEntity;
import com.github.bbugsco.substancecraft.block.entity.entities.MixerBlockEntity;
import com.github.bbugsco.substancecraft.block.entity.entities.OxidizerBlockEntity;
import com.github.bbugsco.substancecraft.block.entity.entities.RefineryBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class SubstanceCraftBlockEntities {

    public static final BlockEntityType<HashPressBlockEntity> HASH_PRESS =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "hash_press"),
                    BlockEntityType.Builder.of(HashPressBlockEntity::new, SubstanceCraftBlocks.HASH_PRESS).build());

    public static final BlockEntityType<RefineryBlockEntity> REFINERY =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refinery"),
                    BlockEntityType.Builder.of(RefineryBlockEntity::new, SubstanceCraftBlocks.REFINERY).build());

    public static final BlockEntityType<ElectrolysisMachineBlockEntity> ELECTROLYSIS_MACHINE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "electrolysis_machine"),
                    BlockEntityType.Builder.of(ElectrolysisMachineBlockEntity::new, SubstanceCraftBlocks.ELECTROLYSIS_MACHINE).build());

    public static final BlockEntityType<OxidizerBlockEntity> OXIDIZER =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "oxidizer"),
                    BlockEntityType.Builder.of(OxidizerBlockEntity::new, SubstanceCraftBlocks.OXIDATION_MACHINE).build());

    public static final BlockEntityType<CatalyticReformerBlockEntity> CATALYTIC_REFORMER =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "catalytic_reformer"),
                    BlockEntityType.Builder.of(CatalyticReformerBlockEntity::new, SubstanceCraftBlocks.CATALYTIC_REFORMER).build());

    public static final BlockEntityType<AirExtractorBlockEntity> AIR_EXTRACTOR =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "air_extractor"),
                    BlockEntityType.Builder.of(AirExtractorBlockEntity::new, SubstanceCraftBlocks.AIR_EXTRACTOR).build());

    public static final BlockEntityType<MixerBlockEntity> MIXER =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mixer"),
                    BlockEntityType.Builder.of(MixerBlockEntity::new, SubstanceCraftBlocks.MIXER).build());

    public static final BlockEntityType<HeatedMixerBlockEntity> HEATED_MIXER =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "heated_mixer"),
                    BlockEntityType.Builder.of(HeatedMixerBlockEntity::new, SubstanceCraftBlocks.HEATED_MIXER).build());

    public static final BlockEntityType<FermentationTankBlockEntity> FERMENTATION_TANK =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "air_extractor"),
                    BlockEntityType.Builder.of(FermentationTankBlockEntity::new, SubstanceCraftBlocks.FERMENTATION_TANK).build());

    public static void registerBlockEntities() {
        SubstanceCraft.LOGGER.info("Registering Block Entities for " + SubstanceCraft.MOD_ID);
    }

}
