package dev.stormwatch.vanillaspice.setup;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import dev.stormwatch.vanillaspice.setup.RegistryHandler;

public class ModItems {
    public static final RegistryObject<Item> LEATHER_PATCH = RegistryHandler.ITEMS.register("leather_patch", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> IRON_PLATE = RegistryHandler.ITEMS.register("iron_plate", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> CRYSTALLINE_GUARD = RegistryHandler.ITEMS.register("crystalline_guard", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));

    static void register() {}
}