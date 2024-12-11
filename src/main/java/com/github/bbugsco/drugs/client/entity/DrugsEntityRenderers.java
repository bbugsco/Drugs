package com.github.bbugsco.drugs.client.entity;

import com.github.bbugsco.drugs.Drugs;
import com.github.bbugsco.drugs.client.entity.hatman.HatManModel;
import com.github.bbugsco.drugs.client.entity.hatman.HatmanRenderer;
import com.github.bbugsco.drugs.entities.DrugsEntities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class DrugsEntityRenderers {

    public static final ModelLayerLocation HATMAN_MODEL = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "hatman"), "main");

    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(DrugsEntities.HATMAN, (HatmanRenderer::new));
        EntityModelLayerRegistry.registerModelLayer(HATMAN_MODEL, HatManModel::createBodyLayer);
        Drugs.LOGGER.info("Registered Drugs EntityRenderers");
    }

}
