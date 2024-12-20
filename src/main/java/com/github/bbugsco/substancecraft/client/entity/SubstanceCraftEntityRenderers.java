package com.github.bbugsco.substancecraft.client.entity;

import com.github.bbugsco.substancecraft.SubstanceCraft;
import com.github.bbugsco.substancecraft.client.entity.hatman.HatManModel;
import com.github.bbugsco.substancecraft.client.entity.hatman.HatmanRenderer;
import com.github.bbugsco.substancecraft.entities.SubstanceCraftEntities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class SubstanceCraftEntityRenderers {

    public static final ModelLayerLocation HATMAN_MODEL = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "hatman"), "main");

    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(SubstanceCraftEntities.HATMAN, (HatmanRenderer::new));
        EntityModelLayerRegistry.registerModelLayer(HATMAN_MODEL, HatManModel::createBodyLayer);
        SubstanceCraft.LOGGER.info("Registered " + SubstanceCraft.MOD_ID + " EntityRenderers");
    }

}
