package com.github.bbugsco.drugs.datagen;

import com.github.bbugsco.drugs.block.DrugsBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

public class LootTableGenerator extends FabricBlockLootTableProvider {

    public LootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        add(DrugsBlocks.HASH_PRESS, createNameableBlockEntityTable(DrugsBlocks.HASH_PRESS));
        add(DrugsBlocks.REFINERY, createNameableBlockEntityTable(DrugsBlocks.REFINERY));
        dropSelf(DrugsBlocks.OIL_SHALE);
        add(DrugsBlocks.OXIDATION_MACHINE, createNameableBlockEntityTable(DrugsBlocks.OXIDATION_MACHINE));
        add(DrugsBlocks.ELECTROLYSIS_MACHINE, createNameableBlockEntityTable(DrugsBlocks.ELECTROLYSIS_MACHINE));
        add(DrugsBlocks.AIR_EXTRACTOR, createNameableBlockEntityTable(DrugsBlocks.AIR_EXTRACTOR));
        add(DrugsBlocks.CATALYTIC_REFORMER, createNameableBlockEntityTable(DrugsBlocks.CATALYTIC_REFORMER));

        add(DrugsBlocks.SALT, block -> this.createSilkTouchDispatchTable(
                block,
                this.applyExplosionDecay(block, LootItem.lootTableItem(Items.GLOWSTONE_DUST)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                                .apply(LimitCount.limitCount(IntRange.range(1, 4))))));

    }

}
