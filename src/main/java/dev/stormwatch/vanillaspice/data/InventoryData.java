package dev.stormwatch.vanillaspice.data;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InventoryData {

    private static final Map<UUID, ArrayList<ItemStack>> playerInventoryMap = new HashMap<UUID, ArrayList<ItemStack>>();

    public static void save(UUID playerUUID, NonNullList<ItemStack> playerInventory) {
        // Saves a snapshot of the player's inventory
        ArrayList<ItemStack> playerInventoryArray = new ArrayList<ItemStack>(playerInventory);
        playerInventoryMap.put(playerUUID, playerInventoryArray);
    }

    public static boolean hasPlayer(UUID playerUUID) {
        return playerInventoryMap.containsKey(playerUUID);
    }

    public static ItemStack findNewInstance(UUID playerUUID, NonNullList<ItemStack> playerInventory, Class<?> itemClass, String potionType) {
        // Looks for items of type with (optionally) matching nbt tag in current player inventory, that isn't present in saved snapshot of player's inventory
        ArrayList<ItemStack> currentPlayerInventory = new ArrayList<ItemStack>(playerInventory);
        ArrayList<ItemStack> oldPlayerInventory = playerInventoryMap.get(playerUUID);
        if (currentPlayerInventory.equals(oldPlayerInventory)) { return ItemStack.EMPTY; }
        currentPlayerInventory.removeAll(oldPlayerInventory);
        for (ItemStack stack : currentPlayerInventory) {
            Item item = stack.getItem();
            if (itemClass.isInstance(item)) {
                if (potionType != null) {
                    CompoundNBT nbt = stack.getTag();
                    if (nbt == null) { continue; }
                    if (!nbt.getString("Potion").equals(potionType)) { continue; }
                }
                return stack;
            }
        }

        return ItemStack.EMPTY;
    }

}
