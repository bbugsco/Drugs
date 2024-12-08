package com.github.bbugsco.drugs.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public class DabRig {

    static class DabRigItem extends Item {

        public DabRigItem(Properties properties) {
            super(properties);
        }

        @Override
        public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
            ItemStack item = player.getItemInHand(interactionHand);
            if (!(player.getOffhandItem().is(DrugsItems.HASH))) return InteractionResultHolder.fail(item);
            return super.use(level, player, interactionHand);
        }

        @Override
        public @NotNull ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
            if (!(livingEntity instanceof Player player)) return itemStack;
            if (player.getOffhandItem().is(DrugsItems.HASH)) {
                ItemStack hash = player.getOffhandItem();
                hash.setCount(hash.getCount() - 1);
                player.getCooldowns().addCooldown(this, 20 * 20);

                Vector3f positionVector = new Vector3f((float) player.getX(), (float) player.getY(), (float) player.getZ())
                                .add(player.getLookAngle().normalize().toVector3f().mul(0.8f))
                                .add(new Vector3f(0.0f, 1.5f, 0.0f));
                Vector3f smokeVelocity = player.getLookAngle().normalize().toVector3f()
                                .add(new Vector3f(0.0f, 0.8f, 0.0f))
                                .mul(0.01F);

                RandomSource randomSource = level.random;
                for (int x = -2; x < 3; x++) {
                    for (int z = -2; z < 3; z++) {
                        float chance = randomSource.nextFloat();
                        int rolls = (chance < 0.15F) ? 0 : ((chance < 0.5) ? 1 : ((chance < 0.8F) ? 2 : 3));
                        for (int i = 0; i < rolls; i++) {
                            level.addParticle(
                                    ParticleTypes.CAMPFIRE_COSY_SMOKE,
                                    positionVector.x + ((double) x) / 8,
                                    positionVector.y + randomSource.nextFloat() * 0.4F,
                                    positionVector.z + ((double) z) / 8,
                                    smokeVelocity.x + (((double) x) / 8) * (randomSource.nextFloat() * 0.07F),
                                    smokeVelocity.y + randomSource.nextFloat() * 0.05F,
                                    smokeVelocity.z + (((double) z) / 8) * (randomSource.nextFloat() * 0.07F));
                        }
                    }
                }

                level.playSound(
                        player,
                        new BlockPos(new Vec3i(Math.round(positionVector.x), Math.round(positionVector.y), Math.round(positionVector.z))),
                        SoundEvents.BREWING_STAND_BREW,
                        SoundSource.BLOCKS,
                        1.0F,
                        1.0F);

                int previousAmplifier = -1;
                int duration = (180 * 20);
                for (MobEffectInstance effect : player.getActiveEffects()) {
                    if (effect.getEffect() == MobEffects.HUNGER) {
                        previousAmplifier = effect.getAmplifier();
                        duration = (effect.getDuration() / 2) + duration;
                    }
                }
                if (previousAmplifier > 3) {
                    int secondaryEffectLevel = Math.min(9, previousAmplifier + 1);
                    player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, duration, secondaryEffectLevel, true, false));
                    player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, duration, Math.min(secondaryEffectLevel, 4), true, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, duration, Math.min(secondaryEffectLevel, 4), true, false));
                }
                player.addEffect(new MobEffectInstance(MobEffects.HUNGER, duration, Math.min(previousAmplifier + 1, 9), true, false));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration, Math.min((previousAmplifier < 4 ? previousAmplifier : 3) + 1, 9), true, false));
            }
            return itemStack;
        }
    }

    static class EmptyDabRigItem extends Item {

        public EmptyDabRigItem(Properties properties) {
            super(properties);
        }

        @Override
        public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
            ItemStack itemStack = player.getItemInHand(usedHand);
            BlockHitResult blockHitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
            if (blockHitResult.getType() != HitResult.Type.MISS) {
                if (blockHitResult.getType() == HitResult.Type.BLOCK) {
                    BlockPos blockPos = blockHitResult.getBlockPos();
                    if (!level.mayInteract(player, blockPos)) {
                        return InteractionResultHolder.pass(itemStack);
                    }
                    if (level.getFluidState(blockPos).is(FluidTags.WATER)) {
                        level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
                        level.gameEvent(player, GameEvent.FLUID_PICKUP, blockPos);
                        return InteractionResultHolder.sidedSuccess(this.turnBottleIntoItem(itemStack, player, new ItemStack(DrugsItems.DAB_RIG)), level.isClientSide());
                    }
                }
            }
            return InteractionResultHolder.pass(itemStack);
        }

        protected ItemStack turnBottleIntoItem(ItemStack bottleStack, Player player, ItemStack filledBottleStack) {
            player.awardStat(Stats.ITEM_USED.get(this));
            return ItemUtils.createFilledResult(bottleStack, player, filledBottleStack);
        }
    }

}