package com.github.bbugsco.drugs.client;

import com.github.bbugsco.drugs.blocks.DrugsBlocks;
import com.github.bbugsco.drugs.blocks.entity.DrugsBlockEntities;
import com.github.bbugsco.drugs.client.block.entity.renderer.HashPressBlockEntityRenderer;
import com.github.bbugsco.drugs.client.entity.DrugsEntityRenderers;
import com.github.bbugsco.drugs.client.gui.DrugsScreens;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

@Environment(EnvType.CLIENT)
public class DrugsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(DrugsBlocks.MARIJUANA_PLANT, RenderType.cutout());
        BlockEntityRenderers.register(DrugsBlockEntities.HASH_PRESS, context -> new HashPressBlockEntityRenderer());

        DrugsScreens.registerScreens();
        DrugsEntityRenderers.registerEntityRenderers();
    }

}
