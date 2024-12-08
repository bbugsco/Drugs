package com.github.bbugsco.drugs.client.entity.hatman;

import com.github.bbugsco.drugs.Drugs;
import com.github.bbugsco.drugs.client.entity.DrugsEntityRenderers;
import com.github.bbugsco.drugs.entities.hatman.HatMan;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class HatmanRenderer extends MobRenderer<HatMan, HatManModel<HatMan>> {

    private static final ResourceLocation LOCATION = ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, "textures/entity/hatman.png");

    public HatmanRenderer(EntityRendererProvider.Context context) {
        super(context, new HatManModel<>(context.bakeLayer(DrugsEntityRenderers.HATMAN_MODEL)), 0.5F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(HatMan entity) {
        return LOCATION;
    }
}
