package dev.stormwatch.vanillaspice.effects;

import dev.stormwatch.vanillaspice.setup.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.entity.living.LootingLevelEvent;

public class ResplendenceEffect extends Effect {

    public ResplendenceEffect() { super(EffectType.BENEFICIAL, 0xffdd00); }

    public static void onLootingLevelEvent(LootingLevelEvent event) {
        LivingEntity living = event.getEntityLiving();
        EffectInstance effect = living.getEffect(ModEffects.RESPLENDENCE.get());
        if (effect != null && living instanceof PlayerEntity && !living.level.isClientSide()) {
            int amp = effect.getAmplifier();
            int lootingLevel = event.getLootingLevel();
            event.setLootingLevel(lootingLevel + ((amp + 1) * 3));
        }
    }

}
