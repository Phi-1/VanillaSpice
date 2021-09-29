package dev.stormwatch.vanillaspice.events;

import dev.stormwatch.vanillaspice.setup.ModEffects;
import dev.stormwatch.vanillaspice.util.XPUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class onLootingLevelEvent {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void event(LootingLevelEvent event) {
        Entity entity = event.getDamageSource().getEntity();
        if (entity instanceof PlayerEntity && !entity.level.isClientSide()) {
            PlayerEntity player = (PlayerEntity) entity;
            int lootingLevel = event.getLootingLevel();

            // +3 Looting for T2 alchs
            if (XPUtil.getAlchemyTier(player) >= 2) {
                lootingLevel += 3;
            }

            // +3 Looting per resplendence level
            EffectInstance effect = player.getEffect(ModEffects.RESPLENDENCE.get());
            if (effect != null) {
                int amp = effect.getAmplifier();
                lootingLevel += ((amp + 1) * 3);
            }

            event.setLootingLevel(lootingLevel);
        }
    }

}
