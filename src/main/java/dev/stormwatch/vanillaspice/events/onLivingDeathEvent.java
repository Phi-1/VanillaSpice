package dev.stormwatch.vanillaspice.events;

import dev.stormwatch.vanillaspice.data.CapabilityMonsterLevel;
import dev.stormwatch.vanillaspice.data.IMonsterLevel;
import dev.stormwatch.vanillaspice.util.XPUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;


public class onLivingDeathEvent {

    public static void event(LivingDeathEvent event) {
        LivingEntity entity = event.getEntityLiving();
        Entity sourceEntity = event.getSource().getEntity();
        // Clear player inventory
        if (entity instanceof PlayerEntity && !entity.level.isClientSide()) {
            PlayerEntity player = (PlayerEntity) entity;
            player.inventory.clearContent();
        }
        // Player main xp
        else if (sourceEntity instanceof PlayerEntity && entity instanceof IMob && !entity.level.isClientSide()) {
            PlayerEntity player = (PlayerEntity) sourceEntity;
            long xpGain = 0;
            IMonsterLevel cap = entity.getCapability(CapabilityMonsterLevel.MONSTER_LEVEL_CAPABILITY).orElse(null);
            if (cap != null) { xpGain = cap.getXPDrop(); }
            XPUtil.increaseMainXP(player, xpGain);
        }
    }

}
