package dev.stormwatch.vanillaspice.util;

import dev.stormwatch.vanillaspice.data.CapabilityPlayerStats;
import dev.stormwatch.vanillaspice.lib.Visions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class XPUtil {

    private static class XPStorage {
        public int meleeXP;
        public int archeryXP;
        public int alchemyXP;

        public int meleeLevel;
        public int archeryLevel;
        public int alchemyLevel;

        public int meleeTier;
        public int archeryTier;
        public int alchemyTier;
    }

    // Contains the UUID of the player and their saved stats
    private static Map<String, XPStorage> playerStats = new HashMap<String, XPStorage>();

    // Called on player death to remedy stat reset after respawn
    public static void savePlayerStats(PlayerEntity player) {
        XPStorage storage = new XPStorage();
        player.getCapability(CapabilityPlayerStats.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
            storage.meleeXP = h.getMeleeXP();
            storage.archeryXP = h.getArcheryXP();
            storage.alchemyXP = h.getAlchemyXP();
            storage.meleeLevel = h.getMeleeLevel();
            storage.archeryLevel = h.getArcheryLevel();
            storage.alchemyLevel = h.getAlchemyLevel();
            storage.meleeTier = h.getMeleeTier();
            storage.archeryTier = h.getArcheryTier();
            storage.alchemyTier = h.getAlchemyTier();
        });

        playerStats.put(player.getUUID().toString(), storage);
    }

    public static void restorePlayerStats(PlayerEntity player) {
        XPStorage storage = playerStats.get(player.getUUID().toString());
        if (storage == null) { return; }
        player.getCapability(CapabilityPlayerStats.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
            h.setMeleeTier(storage.meleeTier);
            h.setArcheryTier(storage.archeryTier);
            h.setAlchemyTier(storage.alchemyTier);
            h.setMeleeLevel(storage.meleeLevel);
            h.setArcheryLevel(storage.archeryLevel);
            h.setAlchemyLevel(storage.alchemyLevel);
            h.setMeleeXP(storage.meleeXP);
            h.setArcheryXP(storage.archeryXP);
            h.setAlchemyXP(storage.alchemyXP);
        });
    }

    public static void increaseMeleeXP(PlayerEntity player, int minXP, int maxXP) {
        player.getCapability(CapabilityPlayerStats.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
            int xp = h.getMeleeXP();
            int gain = ThreadLocalRandom.current().nextInt(minXP, maxXP + 1);
            if (h.setMeleeXP(xp + gain)) {
                player.displayClientMessage(new StringTextComponent("Melee level " + h.getMeleeLevel()), true);
            }
        });
    }

    public static void increaseArcheryXP(PlayerEntity player, int minXP, int maxXP) {
        player.getCapability(CapabilityPlayerStats.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
            int xp = h.getArcheryXP();
            int gain = ThreadLocalRandom.current().nextInt(minXP, maxXP + 1);
            if (h.setArcheryXP(xp + gain)) {
                player.displayClientMessage(new StringTextComponent("Archery level " + h.getArcheryLevel()), true);
            }
        });
    }

    public static void increaseAlchemyXP(PlayerEntity player, int minXP, int maxXP) {
        player.getCapability(CapabilityPlayerStats.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
            int xp = h.getAlchemyXP();
            int gain = ThreadLocalRandom.current().nextInt(minXP, maxXP + 1);
            if (h.setAlchemyXP(xp + gain)) {
                player.displayClientMessage(new StringTextComponent("Alchemy level " + h.getAlchemyLevel()), true);
            }
        });
    }

    public static int getMeleeLevel(PlayerEntity player) {
        AtomicInteger level = new AtomicInteger();
        player.getCapability(CapabilityPlayerStats.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
            level.set(h.getMeleeLevel());
        });
        return level.get();
    }

    public static int getArcheryLevel(PlayerEntity player) {
        AtomicInteger level = new AtomicInteger();
        player.getCapability(CapabilityPlayerStats.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
            level.set(h.getArcheryLevel());
        });
        return level.get();
    }

    public static int getAlchemyLevel(PlayerEntity player) {
        AtomicInteger level = new AtomicInteger();
        player.getCapability(CapabilityPlayerStats.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
            level.set(h.getAlchemyLevel());
        });
        return level.get();
    }

    public static int getMeleeTier(PlayerEntity player) {
        AtomicInteger tier = new AtomicInteger();
        player.getCapability(CapabilityPlayerStats.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
            tier.set(h.getMeleeTier());
        });
        return tier.get();
    }

    public static int getArcheryTier(PlayerEntity player) {
        AtomicInteger tier = new AtomicInteger();
        player.getCapability(CapabilityPlayerStats.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
            tier.set(h.getArcheryTier());
        });
        return tier.get();
    }

    public static int getAlchemyTier(PlayerEntity player) {
        AtomicInteger tier = new AtomicInteger();
        player.getCapability(CapabilityPlayerStats.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
            tier.set(h.getAlchemyTier());
        });
        return tier.get();
    }

    public static void handleVisionUse(PlayerEntity player, String visionType) {
        player.getCapability(CapabilityPlayerStats.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
            if (visionType == Visions.FLAME) {
                int currentXP = h.getMeleeXP();
                int currentLevel = h.getMeleeLevel();
                int currentTier = h.getMeleeTier();
                int currentThreshold = h.nextLevelThreshold(currentLevel);
                if (currentLevel >= 10 && currentTier < 3) {
                    h.setMeleeLevel(currentLevel - 10);
                    h.setMeleeTier(currentTier + 1);
                } else {
                    player.displayClientMessage(new StringTextComponent("Melee T" + currentTier + ", Level " + currentLevel + ": " + currentXP + " / " + currentThreshold), true);
                }

            } else if (visionType == Visions.GALE) {
                int currentXP = h.getArcheryXP();
                int currentLevel = h.getArcheryLevel();
                int currentTier = h.getArcheryTier();
                int currentThreshold = h.nextLevelThreshold(currentLevel);
                if (currentLevel >= 10 && currentTier < 3) {
                    h.setArcheryLevel(currentLevel - 10);
                    h.setArcheryTier(currentTier + 1);
                } else {
                    player.displayClientMessage(new StringTextComponent("Archery Tier " + currentTier + ", Level " + currentLevel + ": " + currentXP + " / " + currentThreshold), true);
                }

            } else if (visionType == Visions.STAR) {
                int currentXP = h.getAlchemyXP();
                int currentLevel = h.getAlchemyLevel();
                int currentTier = h.getAlchemyTier();
                int currentThreshold = h.nextLevelThreshold(currentLevel);
                if (currentLevel >= 10 && currentTier < 3) {
                    h.setAlchemyLevel(currentLevel - 10);
                    h.setAlchemyTier(currentTier + 1);
                } else {
                    player.displayClientMessage(new StringTextComponent("Alchemy Tier " + currentTier + ", Level " + currentLevel + ": " + currentXP + " / " + currentThreshold), true);
                }

            }
        });
    }

}
