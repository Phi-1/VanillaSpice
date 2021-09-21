package dev.stormwatch.vanillaspice.util;

import dev.stormwatch.vanillaspice.data.CapabilityPlayerStats;
import dev.stormwatch.vanillaspice.data.IPlayerStats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class XPUtil {

    private static final Logger LOGGER = LogManager.getLogger();

    private static class XPStorage {
        public long mainXP;
        public int mainLevel;

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
    private static final Map<String, XPStorage> playerStats = new HashMap<String, XPStorage>();

    // Called on player death to remedy stat reset after respawn
    public static void savePlayerStats(PlayerEntity player) {
        XPStorage storage = new XPStorage();
        player.getCapability(CapabilityPlayerStats.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
            storage.mainXP = h.getMainXP();
            storage.mainLevel = h.getMainLevel();
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
            h.setMainXP(storage.mainXP);
            h.setMainLevel(storage.mainLevel);
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

    public static void setPlayerAttributes(PlayerEntity player) {
        int playerLevel = 0;
        IPlayerStats cap = player.getCapability(CapabilityPlayerStats.PLAYER_STATS_CAPABILITY).orElse(null);
        if (cap != null) { playerLevel = cap.getMainLevel(); }
        ModifierUtil.setPlayerBaseHealth(player);
        ModifierUtil.setMainLevelStats(player, playerLevel);
    }

    public static void increaseMainXP(PlayerEntity player, int mobLevel) {
        int xpGain = 0;
        int ring = (int) Math.ceil((double) mobLevel / 4);
        int rarity = mobLevel % 4; // 1, 2, 3, 0
        if (rarity == 1) {
            if (ring == 1) { xpGain = 10; }
            else { xpGain = 10 * (int) Math.pow(ring + 1, 2); }
        } else if (rarity == 2) {
            if (ring == 1) { xpGain = 20; }
            else { xpGain = 20 * (int) Math.pow(ring + 1, 2); }
        } else if (rarity == 3) {
            if (ring == 1) { xpGain = 80; }
            else { xpGain = 80 * (int) Math.pow(ring + 1, 2); }
        } else if (rarity == 0 && mobLevel > 0) {
            if (ring == 1) { xpGain = 160; }
            else { xpGain = 160 * (int) Math.pow(ring + 1, 2); }
        } else {
            xpGain = 10 * (int) Math.pow(ring + 1, 2);
        }

        IPlayerStats cap = player.getCapability(CapabilityPlayerStats.PLAYER_STATS_CAPABILITY).orElse(null);
        if (cap != null) {
            long currentXP = cap.getMainXP();
            if (cap.setMainXP(currentXP + xpGain)) {
                int currentLevel = cap.getMainLevel();
                ModifierUtil.setMainLevelStats(player, currentLevel);
                player.displayClientMessage(new StringTextComponent("Main level is now " + currentLevel), true);
            }
        }
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

    public static boolean increaseMeleeTier(PlayerEntity player, int targetTier) {
        AtomicBoolean tierUp = new AtomicBoolean(false);
        player.getCapability(CapabilityPlayerStats.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
            int currentTier = h.getMeleeTier();
            if (currentTier >= targetTier || currentTier < targetTier - 1) { return; }
            int currentLevel = h.getMeleeLevel();
            if (currentLevel < 10) { return; }
            h.setMeleeLevel(currentLevel - 10);
            h.setMeleeTier(targetTier);
            tierUp.set(true);
            player.displayClientMessage(new TranslationTextComponent("vanillaspice.message.meleetierup", targetTier), true);
        });
        return tierUp.get();
    }

    public static boolean increaseArcheryTier(PlayerEntity player, int targetTier) {
        AtomicBoolean tierUp = new AtomicBoolean(false);
        player.getCapability(CapabilityPlayerStats.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
            int currentTier = h.getArcheryTier();
            if (currentTier >= targetTier || currentTier < targetTier - 1) { return; }
            int currentLevel = h.getArcheryLevel();
            if (currentLevel < 10) { return; }
            h.setArcheryLevel(currentLevel - 10);
            h.setArcheryTier(targetTier);
            tierUp.set(true);
            player.displayClientMessage(new TranslationTextComponent("vanillaspice.message.archerytierup", targetTier), true);
        });
        return tierUp.get();
    }

    public static boolean increaseAlchemyTier(PlayerEntity player, int targetTier) {
        AtomicBoolean tierUp = new AtomicBoolean(false);
        player.getCapability(CapabilityPlayerStats.PLAYER_STATS_CAPABILITY).ifPresent(h -> {
            int currentTier = h.getAlchemyTier();
            if (currentTier >= targetTier || currentTier < targetTier - 1) { return; }
            int currentLevel = h.getAlchemyLevel();
            if (currentLevel < 10) { return; }
            h.setAlchemyLevel(currentLevel - 10);
            h.setAlchemyTier(targetTier);
            tierUp.set(true);
            player.displayClientMessage(new TranslationTextComponent("vanillaspice.message.alchemytierup", targetTier), true);
        });
        return tierUp.get();
    }

}
