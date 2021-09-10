package dev.stormwatch.vanillaspice.events;

import dev.stormwatch.vanillaspice.data.ModifiedEffectInstance;
import dev.stormwatch.vanillaspice.util.XPUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event;

public class onPotionApplicableEvent {

    public static void event(PotionEvent.PotionApplicableEvent event) {
        LivingEntity entity = event.getEntityLiving();

        if (entity instanceof PlayerEntity && !entity.level.isClientSide()) {
            PlayerEntity player = (PlayerEntity) entity;
            EffectInstance baseEffect = event.getPotionEffect();
            Effect baseEffectType = baseEffect.getEffect();
            int baseEffectAmplifier = baseEffect.getAmplifier();
            int baseEffectDuration = baseEffect.getDuration();
            int alchLevel = XPUtil.getAlchemyLevel(player);
            int alchTier = XPUtil.getAlchemyTier(player);

            if (!(baseEffect instanceof ModifiedEffectInstance)) {
                int newDuration = baseEffectDuration;
                int newAmplifier = baseEffectAmplifier;
                // Increase duration based on alch level
                if (!baseEffectType.isInstantenous()) {
                    newDuration = baseEffectDuration + Math.round((float)baseEffectDuration * ((float)alchLevel / 30));
                }
                // Tier 3 alchs get an amplifier bonus
                if (alchTier >= 3) {
                    newAmplifier = baseEffectAmplifier + 1;
                }
                ModifiedEffectInstance newEffect = new ModifiedEffectInstance(baseEffectType, newDuration, newAmplifier);
                player.addEffect(newEffect);
                // Vanilla EffectInstance overrides ModifiedEffectInstance, so the application of the base potion effect needs to be canceled
                event.setResult(Event.Result.DENY);
            }
        }
    }

}
