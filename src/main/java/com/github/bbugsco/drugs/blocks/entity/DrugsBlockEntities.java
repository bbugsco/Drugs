package com.github.bbugsco.drugs.blocks.entity;

import com.github.bbugsco.drugs.Drugs;
import com.github.bbugsco.drugs.blocks.DrugsBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class DrugsBlockEntities {

    public static final BlockEntityType<HashPressBlockEntity> HASH_PRESS =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "hash_press_be"),
                    BlockEntityType.Builder.of(HashPressBlockEntity::new, DrugsBlocks.HASH_PRESS).build());

    public static void registerBlockEntities() {
        Drugs.LOGGER.info("Registering Block Entities for " + Drugs.MOD_ID);
    }

}
