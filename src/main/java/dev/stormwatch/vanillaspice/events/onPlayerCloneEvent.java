package dev.stormwatch.vanillaspice.events;

import dev.stormwatch.vanillaspice.data.CapabilityPlayerStats;
import dev.stormwatch.vanillaspice.data.IPlayerStats;
import dev.stormwatch.vanillaspice.util.ModifierUtil;
import dev.stormwatch.vanillaspice.util.XPUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class onPlayerCloneEvent {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void event(PlayerEvent.Clone event) {
        PlayerEntity newPlayer = event.getPlayer();
        if (!newPlayer.level.isClientSide()) {
            // Restore player stats
            PlayerEntity oldPlayer = event.getOriginal();
            XPUtil.savePlayerStats(oldPlayer);
            XPUtil.restorePlayerStats(newPlayer);

            // Set attributes
            XPUtil.setPlayerAttributes(newPlayer);
        }
    }

}
