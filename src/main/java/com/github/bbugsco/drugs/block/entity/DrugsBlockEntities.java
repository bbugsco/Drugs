package com.github.bbugsco.drugs.block.entity;

import com.github.bbugsco.drugs.Drugs;
import com.github.bbugsco.drugs.block.DrugsBlocks;
import com.github.bbugsco.drugs.block.entity.one_input.CatalyticReformerBlockEntity;
import com.github.bbugsco.drugs.block.entity.one_input.ElectrolysisMachineBlockEntity;
import com.github.bbugsco.drugs.block.entity.one_input.HashPressBlockEntity;
import com.github.bbugsco.drugs.block.entity.one_input.OxidizerBlockEntity;
import com.github.bbugsco.drugs.block.entity.one_input.RefineryBlockEntity;
import com.github.bbugsco.drugs.block.entity.one_input.AirExtractorBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class DrugsBlockEntities {

    public static final BlockEntityType<HashPressBlockEntity> HASH_PRESS =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "hash_press"),
                    BlockEntityType.Builder.of(HashPressBlockEntity::new, DrugsBlocks.HASH_PRESS).build());

    public static final BlockEntityType<RefineryBlockEntity> REFINERY =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "refinery"),
                    BlockEntityType.Builder.of(RefineryBlockEntity::new, DrugsBlocks.REFINERY).build());

    public static final BlockEntityType<ElectrolysisMachineBlockEntity> ELECTROLYSIS_MACHINE =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "electrolysis_machine"),
                    BlockEntityType.Builder.of(ElectrolysisMachineBlockEntity::new, DrugsBlocks.ELECTROLYSIS_MACHINE).build());

    public static final BlockEntityType<OxidizerBlockEntity> OXIDIZER =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "oxidizer"),
                    BlockEntityType.Builder.of(OxidizerBlockEntity::new, DrugsBlocks.OXIDATION_MACHINE).build());

    public static final BlockEntityType<CatalyticReformerBlockEntity> CATALYTIC_REFORMER =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "catalytic_reformer"),
                    BlockEntityType.Builder.of(CatalyticReformerBlockEntity::new, DrugsBlocks.CATALYTIC_REFORMER).build());

    public static final BlockEntityType<AirExtractorBlockEntity> AIR_EXTRACTOR =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "air_extractor"),
                    BlockEntityType.Builder.of(AirExtractorBlockEntity::new, DrugsBlocks.AIR_EXTRACTOR).build());

    public static void registerBlockEntities() {
        Drugs.LOGGER.info("Registering Block Entities for " + Drugs.MOD_ID);
    }

}
