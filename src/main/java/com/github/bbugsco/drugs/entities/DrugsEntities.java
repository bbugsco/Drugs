package com.github.bbugsco.drugs.entities;

import com.github.bbugsco.drugs.Drugs;
import com.github.bbugsco.drugs.entities.hatman.HatMan;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class DrugsEntities {

    public static final EntityType<HatMan> HATMAN = register("hatman", EntityType.Builder.of(HatMan::new, MobCategory.MISC).sized(0.6F, 2.9F).eyeHeight(2.55F).passengerAttachments(2.80625F).clientTrackingRange(8));

    public static void registerEntities() {
        FabricDefaultAttributeRegistry.register(HATMAN, HatMan.createMobAttributes());
        Drugs.LOGGER.info("Registering Drugs Entities");
    }

    private static <T extends Entity> EntityType<T> register(String key, EntityType.Builder<T> builder) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(Drugs.MOD_ID, key), builder.build(key));
    }


}
