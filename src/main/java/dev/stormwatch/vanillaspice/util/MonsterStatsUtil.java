package dev.stormwatch.vanillaspice.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.monster.EndermanEntity;

public class MonsterStatsUtil {

    private static final double[] hpPerLevel = new double[] { 1, 1.5, 1.8, 2,  2, 2.2, 2.5, 3,  3, 3.5, 4, 5,  5, 5, 5, 5 };
    private static final double[] atkPerLevel = new double[] { 1, 1.2, 1.5, 2,  2, 2.2, 3, 4,  4, 4.2, 4.5, 5,  5, 6, 8, 10 };
    private static final double[] spdPerLevel = new double[] { 1, 1.2, 1.5, 2,  1.2, 1.5, 1.8, 2,  1.2, 1.5, 2, 3,  1.5, 3, 3, 3 };
    private static final double[] rangePerLevel = new double[] { 1, 1.5, 3, 5,  3, 3.5, 5, 7,  5, 5.5, 6.5, 8,  10, 10, 10, 10 };

    public static void setMonsterStats(LivingEntity entity, int level) {
        if (level > 16 || level <= 0) { return; }
        ModifierUtil.setMaxHealth(entity, hpPerLevel[level-1]-1);
        ModifierUtil.setAttackDamage(entity, atkPerLevel[level-1]-1);
        ModifierUtil.setSpeed(entity, spdPerLevel[level-1]-1);
        ModifierUtil.setRange(entity, rangePerLevel[level-1]-1);
    }

    public static void setEnderDragonStats(EnderDragonEntity dragon) {
        ModifierUtil.setMaxHealth(dragon, 4);
        ModifierUtil.setAttackDamage(dragon, 4);
    }

    public static void setEndEndermanStats(EndermanEntity endermans) {
        ModifierUtil.setMaxHealth(endermans, 1);
        ModifierUtil.setAttackDamage(endermans, 1);
    }

}
