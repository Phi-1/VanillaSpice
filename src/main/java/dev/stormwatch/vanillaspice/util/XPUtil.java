package dev.stormwatch.vanillaspice.util;

import dev.stormwatch.vanillaspice.data.CapabilityPlayerStats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;

import java.util.concurrent.ThreadLocalRandom;

public class XPUtil {

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

}
