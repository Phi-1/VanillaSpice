package dev.stormwatch.vanillaspice.events;

import dev.stormwatch.vanillaspice.data.CapabilityMonsterLevel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ThreadLocalRandom;

public class onLivingSpecialSpawnEvent {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void event(LivingSpawnEvent.SpecialSpawn event) {
        LivingEntity entity = event.getEntityLiving();

        if (entity instanceof MonsterEntity && !entity.level.isClientSide()) {
            LOGGER.info(entity.toString() + " spawned");
            MonsterEntity monster = (MonsterEntity) entity;
            double x = event.getX();
            double z = event.getZ();
            int distanceFromZero = (int) Math.sqrt((Math.pow(x, 2) + Math.pow(z, 2)));
            int ring;
            if (distanceFromZero < 500) { ring = 0; }
            else if (distanceFromZero < 1500) { ring = 1; }
            else if (distanceFromZero < 5000) { ring = 2; }
            else { ring = 3; }
            int level = ThreadLocalRandom.current().nextInt(1, 4 + 1) + (ring * 4); // 4 + 1 to make clear to myself the level range is 1-4
            monster.getCapability(CapabilityMonsterLevel.MONSTER_LEVEL_CAPABILITY).ifPresent(h -> {
                h.setLevel(level);
            });
        }
    }

}
