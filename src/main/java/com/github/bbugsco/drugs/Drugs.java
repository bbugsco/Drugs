package com.github.bbugsco.drugs;

import com.github.bbugsco.drugs.block.DrugsBlocks;
import com.github.bbugsco.drugs.block.entity.DrugsBlockEntities;
import com.github.bbugsco.drugs.datagen.BlockTagGenerator;
import com.github.bbugsco.drugs.datagen.ItemTagGenerator;
import com.github.bbugsco.drugs.datagen.LootTableGenerator;
import com.github.bbugsco.drugs.datagen.ModelGenerator;
import com.github.bbugsco.drugs.datagen.RecipeGenerator;
import com.github.bbugsco.drugs.entities.DrugsEntities;
import com.github.bbugsco.drugs.gui.DrugsMenus;
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

    /*
     TODO:
     - yeast composter block
     - barley, hops
     - ergot?
     - fermentation tank (vinegar, alcohol)
     - dmt
     - bho/butane
     - chloroform recipe
     -
     - chloroform = heated chlorine, methane
     - ammonia = hydrogen + nitrogen heat
     - methylamine = ammonia + methanol
     - HCl = hydrogen, chlorine, water
     */

    @Override
    public void onInitialize() {
        DrugsBlocks.registerBlocks();
        DrugsBlockEntities.registerBlockEntities();
        DrugsItems.registerModItems();
        DrugsItemGroups.registerItemGroups();
        DrugsMenus.registerMenus();
        DrugsRecipes.registerRecipes();
        DrugsFeatures.registerFeatures();
        DrugsLootTables.registerLootTables();
        DrugsEntities.registerEntities();
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