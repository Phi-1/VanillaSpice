package dev.stormwatch.vanillaspice.events;

import dev.stormwatch.vanillaspice.data.CapabilityMonsterLevel;
import dev.stormwatch.vanillaspice.util.MonsterStatsUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ThreadLocalRandom;

public class onLivingSpecialSpawnEvent {

    private static final Logger LOGGER = LogManager.getLogger();

    public static int getRandomLevel() {
        int base;
        double rand = ThreadLocalRandom.current().nextDouble();
        if (rand >= 0.5) { base = 1; }       // 50%
        else if (rand >= 0.15) { base = 2; } // 35%
        else if (rand >= 0.05) { base = 3; } // 10%
        else { base = 4; }                   // 5%
        return base;
    }

    public static void event(LivingSpawnEvent.SpecialSpawn event) {
        LivingEntity entity = event.getEntityLiving();

        if (entity instanceof IMob && !entity.level.isClientSide()) {
            RegistryKey<World> dimension = entity.level.dimension();

            if (event.getSpawnReason() == SpawnReason.SPAWNER) {
                entity.getCapability(CapabilityMonsterLevel.MONSTER_LEVEL_CAPABILITY).ifPresent(h -> {
                    h.setXPScalesWithLevel(false);
                });
            }

            if (entity instanceof WitherEntity) {
                // Wither gets stats according to levels 1-4, but gives xp congruent with level 20
                entity.getCapability(CapabilityMonsterLevel.MONSTER_LEVEL_CAPABILITY).ifPresent(h -> { h.setLevel(20); });
                MonsterStatsUtil.setMonsterStats(entity, getRandomLevel());
            } else if (dimension == World.OVERWORLD) {
                double x = event.getX();
                double z = event.getZ();
                int distanceFromZero = (int) Math.sqrt((Math.pow(x, 2) + Math.pow(z, 2)));
                int ring;
                if (distanceFromZero < 500) { ring = 0; }
                else if (distanceFromZero < 1500) { ring = 1; }
                else if (distanceFromZero < 5000) { ring = 2; }
                else { ring = 3; }

                int base = getRandomLevel();
                int level = base + (ring * 4);

                entity.getCapability(CapabilityMonsterLevel.MONSTER_LEVEL_CAPABILITY).ifPresent(h -> { h.setLevel(level); });
                MonsterStatsUtil.setMonsterStats(entity, level);
            } else if (dimension == World.NETHER) {
                int level = getRandomLevel();
                entity.getCapability(CapabilityMonsterLevel.MONSTER_LEVEL_CAPABILITY).ifPresent(h -> { h.setLevel(level); });
                MonsterStatsUtil.setMonsterStats(entity, level);
            } else if (entity instanceof EnderDragonEntity) {
                EnderDragonEntity dragon = (EnderDragonEntity) entity;
                MonsterStatsUtil.setEnderDragonStats(dragon);
            } else if (dimension == World.END && entity instanceof EndermanEntity) {
                EndermanEntity enderman = (EndermanEntity) entity;
                MonsterStatsUtil.setEndEndermanStats(enderman);
                entity.getCapability(CapabilityMonsterLevel.MONSTER_LEVEL_CAPABILITY).ifPresent(h -> { h.setLevel(9); });
            }
        }
    }

}
