package dev.stormwatch.vanillaspice.events;

import dev.stormwatch.vanillaspice.data.InventoryData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.LogicalSide;

import java.util.ArrayList;
import java.util.UUID;

public class onPlayerTickEvent {

    public static void event(TickEvent.PlayerTickEvent event) {
        // if no inventory is saved for player, save it
        // if player id matches one that just brewed a potion, check for potion of that type that isn't in previous snapshot of inventory
        // and that doesn't have charges nbt
        // add charges nbt to potion
        if (event.side == LogicalSide.CLIENT) { return; }
        if (event.phase == TickEvent.Phase.START) { return; }
        PlayerEntity player = event.player;
        UUID playerUUID = player.getUUID();
        if (!InventoryData.hasPlayer(playerUUID)) { InventoryData.save(playerUUID, player.inventory.items); }

        if (onPlayerBrewPotionEvent.potionsToReceiveCharges.containsKey(playerUUID)) {
            ArrayList<String> potions = onPlayerBrewPotionEvent.potionsToReceiveCharges.get(playerUUID);
            ArrayList<String> toRemove = new ArrayList<String>();
            for (String potionType : potions) {
                ItemStack match = InventoryData.findNewInstance(playerUUID, player.inventory.items, PotionItem.class, potionType);
                if (match == ItemStack.EMPTY) { continue; }
                CompoundNBT tag = match.getTag();
                if (tag == null) { tag = new CompoundNBT(); }
                if (tag.contains("vs_charges")) {
                    toRemove.add(potionType);
                    continue;
                }
                tag.putInt("vs_charges", 3);
                match.setTag(tag);
                toRemove.add(potionType);
            }
            for (String potionType : toRemove) { potions.remove(potionType); }
        }

        InventoryData.save(playerUUID, player.inventory.items);
    }

}
