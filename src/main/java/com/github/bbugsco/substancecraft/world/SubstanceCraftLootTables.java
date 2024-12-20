package com.github.bbugsco.substancecraft.world;

import com.github.bbugsco.substancecraft.SubstanceCraft;
import com.github.bbugsco.substancecraft.block.SubstanceCraftBlocks;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class SubstanceCraftLootTables {

    public static void registerLootTables() {
        SubstanceCraft.LOGGER.info("Registering Mod Loot Tables for "  + SubstanceCraft.MOD_ID);
        addMarijuanaPlantSeedsToJungleTempleChestLoot();
    }

    private static void addMarijuanaPlantSeedsToJungleTempleChestLoot() {
        LootTableEvents.MODIFY.register((lootTable, tableBuilder, lootTableSource, provider) -> {
            if (lootTableSource.isBuiltin() && BuiltInLootTables.JUNGLE_TEMPLE.equals(lootTable)) {
                LootPool.Builder lootPool = new LootPool.Builder();
                lootPool.setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MARIJUANA_PLANT))
                        .setWeight(15))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)));
                tableBuilder.withPool(lootPool);
            }
        });
    }

}
