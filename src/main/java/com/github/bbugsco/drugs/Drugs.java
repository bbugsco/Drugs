package com.github.bbugsco.drugs;

import com.github.bbugsco.drugs.blocks.DrugsBlocks;
import com.github.bbugsco.drugs.blocks.entity.DrugsBlockEntities;
import com.github.bbugsco.drugs.client.entity.DrugsEntityRenderers;
import com.github.bbugsco.drugs.datagen.BlockTagGenerator;
import com.github.bbugsco.drugs.datagen.ItemTagGenerator;
import com.github.bbugsco.drugs.datagen.LootTableGenerator;
import com.github.bbugsco.drugs.datagen.ModelGenerator;
import com.github.bbugsco.drugs.datagen.RecipeGenerator;
import com.github.bbugsco.drugs.entities.DrugsEntities;
import com.github.bbugsco.drugs.gui.DrugsGUIs;
import com.github.bbugsco.drugs.items.DrugsItemGroups;
import com.github.bbugsco.drugs.items.DrugsItems;
import com.github.bbugsco.drugs.recipe.DrugsRecipes;
import com.github.bbugsco.drugs.world.DrugsFeatures;
import com.github.bbugsco.drugs.world.DrugsLootTables;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Drugs implements ModInitializer, DataGeneratorEntrypoint {

    public static final String MOD_ID = "drugs";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        DrugsBlocks.registerBlocks();
        DrugsBlockEntities.registerBlockEntities();
        DrugsItems.registerModItems();
        DrugsItemGroups.registerItemGroups();
        DrugsGUIs.registerScreenHandlers();
        DrugsRecipes.registerRecipes();
        DrugsFeatures.registerFeatures();
        DrugsLootTables.registerLootTables();
        DrugsEntities.registerEntities();
        DrugsEntityRenderers.registerEntityRenderers();
    }

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(BlockTagGenerator::new);
        pack.addProvider(ItemTagGenerator::new);
        pack.addProvider(LootTableGenerator::new);
        pack.addProvider(ModelGenerator::new);
        pack.addProvider(RecipeGenerator::new);
    }

}