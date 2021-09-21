package dev.stormwatch.vanillaspice.events;

import dev.stormwatch.vanillaspice.util.XPUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class onPlayerLoggedInEvent {

    public static void event(PlayerEvent.PlayerLoggedInEvent event) {
        PlayerEntity player = event.getPlayer();
        XPUtil.setPlayerAttributes(player);
    }

}
