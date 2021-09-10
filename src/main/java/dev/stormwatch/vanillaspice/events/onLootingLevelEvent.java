package dev.stormwatch.vanillaspice.events;

import dev.stormwatch.vanillaspice.util.XPUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LootingLevelEvent;

public class onLootingLevelEvent {

    public static void event(LootingLevelEvent event) {
        Entity entity = event.getDamageSource().getEntity();
        if (entity instanceof PlayerEntity && !entity.level.isClientSide()) {
            PlayerEntity player = (PlayerEntity) entity;
            if (XPUtil.getAlchemyTier(player) >= 2) {
                int lootingLevel = event.getLootingLevel();
                event.setLootingLevel(lootingLevel + 6);
            }
        }
    }

}
