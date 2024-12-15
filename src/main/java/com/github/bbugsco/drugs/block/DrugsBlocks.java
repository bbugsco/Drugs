package com.github.bbugsco.drugs.block;

import com.github.bbugsco.drugs.Drugs;
import com.github.bbugsco.drugs.block.blocks.one_input.AirExtractor;
import com.github.bbugsco.drugs.block.blocks.one_input.CatalyticReformer;
import com.github.bbugsco.drugs.block.blocks.one_input.ElectrolysisMachine;
import com.github.bbugsco.drugs.block.blocks.one_input.HashPress;
import com.github.bbugsco.drugs.block.blocks.plants.MarijuanaPlant;
import com.github.bbugsco.drugs.block.blocks.one_input.Oxidizer;
import com.github.bbugsco.drugs.block.blocks.one_input.Refinery;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.HashMap;

public class DrugsBlocks {

    private static final HashMap<Block, Item> BLOCK_ITEMS = new HashMap<>();

    public static final Block MARIJUANA_PLANT = registerBlock("marijuana_plant", new MarijuanaPlant(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().noCollission().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY)));
    public static final Block HASH_PRESS = registerBlock("hash_press", new HashPress(BlockBehaviour.Properties.of().strength(3.5F)));
    public static final Block REFINERY = registerBlock("refinery", new Refinery(BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE)));
    public static final Block OIL_SHALE = registerBlock("oil_shale", new Block(BlockBehaviour.Properties.of().strength(0.6F).sound(SoundType.GRAVEL)));
    public static final Block ELECTROLYSIS_MACHINE = registerBlock("electrolysis", new ElectrolysisMachine(BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE)));
    public static final Block OXIDATION_MACHINE = registerBlock("oxidation_machine", new Oxidizer(BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE)));
    public static final Block CATALYTIC_REFORMER = registerBlock("catalytic_reformer", new CatalyticReformer(BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE)));
    public static final Block AIR_EXTRACTOR = registerBlock("air_extractor", new AirExtractor(BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE)));
    public static final Block SALT = registerBlock("salt", new Block(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.CALCITE)));

    public static Item getBlockItem(Block block) {
        return BLOCK_ITEMS.get(block);
    }

    private static Block registerBlock(String name, Block block) {
        BLOCK_ITEMS.put(block, registerBlockItem(name, block));
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, name), new BlockItem(block, new Item.Properties()));
    }

    public static void registerBlocks() {
        Drugs.LOGGER.info("Registering Blocks for " + Drugs.MOD_ID);
    }

}
