package com.github.bbugsco.drugs.client;

import com.github.bbugsco.drugs.blocks.DrugsBlocks;
import com.github.bbugsco.drugs.blocks.entity.DrugsBlockEntities;
import com.github.bbugsco.drugs.client.block.entity.renderer.HashPressBlockEntityRenderer;
import com.github.bbugsco.drugs.client.entity.DrugsEntityRenderers;
import com.github.bbugsco.drugs.gui.DrugsGUIs;
import com.github.bbugsco.drugs.gui.screen.HashPressScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

public class DrugsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(DrugsBlocks.MARIJUANA_PLANT, RenderType.cutout());
        BlockEntityRenderers.register(DrugsBlockEntities.HASH_PRESS, context -> new HashPressBlockEntityRenderer());
        MenuScreens.register(DrugsGUIs.HASH_PRESS_MENU, HashPressScreen::new);
        DrugsEntityRenderers.registerEntityRenderers();
    }

}
