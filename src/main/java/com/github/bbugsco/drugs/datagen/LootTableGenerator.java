package com.github.bbugsco.drugs.datagen;

import com.github.bbugsco.drugs.block.DrugsBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class LootTableGenerator extends FabricBlockLootTableProvider {

    public LootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        add(DrugsBlocks.HASH_PRESS, createNameableBlockEntityTable(DrugsBlocks.HASH_PRESS));
        add(DrugsBlocks.REFINERY, createNameableBlockEntityTable(DrugsBlocks.REFINERY));
        add(DrugsBlocks.OIL_SHALE, createNameableBlockEntityTable(DrugsBlocks.OIL_SHALE));
    }

}
