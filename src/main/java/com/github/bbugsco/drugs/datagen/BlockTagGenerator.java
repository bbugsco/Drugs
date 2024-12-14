package com.github.bbugsco.drugs.datagen;

import com.github.bbugsco.drugs.block.DrugsBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class BlockTagGenerator extends FabricTagProvider.BlockTagProvider {

    public BlockTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(DrugsBlocks.HASH_PRESS).add(DrugsBlocks.REFINERY);
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_SHOVEL).add(DrugsBlocks.OIL_SHALE);

    }
}