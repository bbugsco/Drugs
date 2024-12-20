package com.github.bbugsco.substancecraft;

import com.github.bbugsco.substancecraft.block.SubstanceCraftBlocks;
import com.github.bbugsco.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.bbugsco.substancecraft.datagen.BlockTagGenerator;
import com.github.bbugsco.substancecraft.datagen.ItemTagGenerator;
import com.github.bbugsco.substancecraft.datagen.LootTableGenerator;
import com.github.bbugsco.substancecraft.datagen.ModelGenerator;
import com.github.bbugsco.substancecraft.datagen.RecipeGenerator;
import com.github.bbugsco.substancecraft.entities.SubstanceCraftEntities;
import com.github.bbugsco.substancecraft.gui.SubstanceCraftMenus;
import com.github.bbugsco.substancecraft.items.SubstanceCraftItems;
import com.github.bbugsco.substancecraft.recipe.SubstanceCraftRecipes;
import com.github.bbugsco.substancecraft.world.SubstanceCraftFeatures;
import com.github.bbugsco.substancecraft.world.SubstanceCraftLootTables;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubstanceCraft implements ModInitializer, DataGeneratorEntrypoint {

    public static final String MOD_ID = "substance";
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
        SubstanceCraftBlocks.registerBlocks();
        SubstanceCraftBlockEntities.registerBlockEntities();
        SubstanceCraftItems.registerModItems();
        SubstanceCraftItems.registerItemGroups();
        SubstanceCraftMenus.registerMenus();
        SubstanceCraftRecipes.registerRecipes();
        SubstanceCraftFeatures.registerFeatures();
        SubstanceCraftLootTables.registerLootTables();
        SubstanceCraftEntities.registerEntities();
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