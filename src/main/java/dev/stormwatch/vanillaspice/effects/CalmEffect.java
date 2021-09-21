package dev.stormwatch.vanillaspice.effects;

import dev.stormwatch.vanillaspice.setup.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.concurrent.ThreadLocalRandom;

public class CalmEffect extends Effect {

    public CalmEffect() { super(EffectType.BENEFICIAL, 0x92cde0); }

    public static void onLivingEntityHurtEvent(LivingHurtEvent event) {
        LivingEntity target = event.getEntityLiving();
        DamageSource source = event.getSource();
        boolean negateDamage = false;
        if (source.isProjectile() && target instanceof PlayerEntity && !target.level.isClientSide()) {
            PlayerEntity player = (PlayerEntity) target;
            EffectInstance calmInstance = player.getEffect(ModEffects.CALM.get());
            if (calmInstance != null) {
                int amp = calmInstance.getAmplifier();
                double rand = ThreadLocalRandom.current().nextDouble();
                double threshold = 0.3 * (amp + 1);
                if (rand <= threshold) { negateDamage = true; }
            }
        }

        if (negateDamage) {
            target.level.playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.SHULKER_HURT_CLOSED, SoundCategory.HOSTILE, 1F, 1F);
            event.setAmount(0);
        }
    }

}
