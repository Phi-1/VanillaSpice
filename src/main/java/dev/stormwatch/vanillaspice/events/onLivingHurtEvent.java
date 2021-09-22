package dev.stormwatch.vanillaspice.events;

import dev.stormwatch.vanillaspice.data.CapabilityMonsterLevel;
import dev.stormwatch.vanillaspice.util.ModifierUtil;
import dev.stormwatch.vanillaspice.util.XPUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ThreadLocalRandom;

public class onLivingHurtEvent {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void event(LivingHurtEvent event) {
        float baseDamage = event.getAmount();
        float finalDamage = baseDamage;
        LivingEntity target = event.getEntityLiving();
        DamageSource source = event.getSource();
        Entity sourceEntity = source.getEntity();
        boolean negateDamage = false;


        if (sourceEntity instanceof PlayerEntity && !sourceEntity.level.isClientSide()) {
            PlayerEntity player = (PlayerEntity) sourceEntity;

            if (source.isProjectile()) {
                if (source.toString().toLowerCase().contains("trident")) {
                    XPUtil.increaseMeleeXP(player, 2, 5);
                    finalDamage += finalDamage * ((float)XPUtil.getMeleeLevel(player) / 100);
                }
                else {
                    XPUtil.increaseArcheryXP(player, 2, 5);
                    finalDamage += finalDamage * ((float)XPUtil.getArcheryLevel(player) / 80);

                    int archeryTier = XPUtil.getArcheryTier(player);
                    if (archeryTier >= 1) {
                        if (ThreadLocalRandom.current().nextDouble() <= 0.2) {
                            finalDamage *= 3;
                        }

                        if (archeryTier >= 2) {
                            // Bonus damage to slowed targets needs to apply before the actual slowness, else all hits just trigger it
                            if (archeryTier >= 3 && target.hasEffect(Effects.MOVEMENT_SLOWDOWN)) {
                                finalDamage *= 1.5;
                            }
                            target.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 60, 1));
                        }
                    }
                }
            } else if (source.isMagic()) {
                XPUtil.increaseAlchemyXP(player, 8, 12);
                finalDamage += finalDamage * ((float)XPUtil.getAlchemyLevel(player) / 50);
            } else {
                XPUtil.increaseMeleeXP(player, 1, 3);
                finalDamage += finalDamage * ((float)XPUtil.getMeleeLevel(player) / 75);

                if (XPUtil.getMeleeTier(player) >= 2) {
                    player.addEffect(new EffectInstance(Effects.REGENERATION, 60, 0));
                    player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 40, 0));
                }
            }
        }

        if (target instanceof PlayerEntity && !target.level.isClientSide()) {
            PlayerEntity player = (PlayerEntity) target;

            XPUtil.increaseMeleeXP(player, 1, 4);
            // Damage reduction is half of level in percentages, eg level 20 would give 10% damage reduction
            finalDamage -= finalDamage * ((float)XPUtil.getMeleeLevel(player) / 200);

            int meleeTier = XPUtil.getMeleeTier(player);
            if (meleeTier >= 1) {
                player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 40, 2));
                // Jump boost 2 makes jump height 2 1/2 blocks, with alchemy amp bonus this would be 3 blocks
                player.addEffect(new EffectInstance(Effects.JUMP, 40, 1));

                if (meleeTier >= 3) {
                    if ((player.getHealth() - finalDamage) <= 0 && ThreadLocalRandom.current().nextDouble() <= 0.5) {
                        negateDamage = true;
                        player.removeAllEffects();
                        player.setHealth(1);
                        player.addEffect(new EffectInstance(Effects.ABSORPTION, 600, 1));
                        player.addEffect(new EffectInstance(Effects.REGENERATION, 600, 1));
                        player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 600, 1));
                        player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 600, 2));
                        player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 600, 1));
                        player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 600, 0));
                        player.addEffect(new EffectInstance(Effects.NIGHT_VISION, 600, 0));
                        player.addEffect(new EffectInstance(Effects.WATER_BREATHING, 600, 1));
                        player.addEffect(new EffectInstance(Effects.DIG_SPEED, 600, 1));
                    }
                }
            }
        }

        if (!negateDamage) { event.setAmount(finalDamage); }
    }

}
