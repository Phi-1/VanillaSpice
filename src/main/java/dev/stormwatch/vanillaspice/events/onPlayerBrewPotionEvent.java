package dev.stormwatch.vanillaspice.events;

import dev.stormwatch.vanillaspice.util.XPUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.brewing.PlayerBrewedPotionEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class onPlayerBrewPotionEvent {

    public static final Map<UUID, ArrayList<String>> potionsToReceiveCharges = new HashMap<UUID, ArrayList<String>>();

    public static void event(PlayerBrewedPotionEvent event) {
        // if player is t1+ alch add charges
        PlayerEntity player = event.getPlayer();
        ItemStack stack = event.getStack();
        if (!player.level.isClientSide()) {
            XPUtil.increaseAlchemyXP(player, 1, 3);

            if (XPUtil.getAlchemyTier(player) >= 1) {
                UUID playerUUID = player.getUUID();
                CompoundNBT tag = stack.getTag();
                if (tag != null) {
                    String potionType = tag.getString("Potion");
                    if (!potionsToReceiveCharges.containsKey(playerUUID)) { potionsToReceiveCharges.put(playerUUID, new ArrayList<String>()); }
                    potionsToReceiveCharges.get(playerUUID).add(potionType);
                }

            }
        }
    }

}
