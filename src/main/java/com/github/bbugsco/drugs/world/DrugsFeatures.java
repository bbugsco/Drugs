package com.github.bbugsco.drugs.world;

import com.github.bbugsco.drugs.Drugs;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class DrugsFeatures {

    public static void registerFeatures() {
        Drugs.LOGGER.info("Registering Features for "  + Drugs.MOD_ID);
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.JUNGLE, Biomes.BAMBOO_JUNGLE, Biomes.SPARSE_JUNGLE, Biomes.SWAMP),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "patch_marijuana"))
        );
    }

}
