package dev.stormwatch.vanillaspice.setup;

import dev.stormwatch.vanillaspice.items.VisionItem;
import dev.stormwatch.vanillaspice.lib.Visions;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;
import dev.stormwatch.vanillaspice.setup.RegistryHandler;

public class ModItems {
    // Armor materials
    public static final RegistryObject<Item> LEATHER_PATCH = RegistryHandler.ITEMS.register("leather_patch", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> IRON_PLATE = RegistryHandler.ITEMS.register("iron_plate", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> CRYSTALLINE_GUARD = RegistryHandler.ITEMS.register("crystalline_guard", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));

    // Class rings
    public static final RegistryObject<Item> STONE_RING = RegistryHandler.ITEMS.register("stone_ring", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS).stacksTo(16)));
    public static final RegistryObject<Item> BONE_RING = RegistryHandler.ITEMS.register("bone_ring", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS).stacksTo(1)));
    public static final RegistryObject<Item> FLESH_RING = RegistryHandler.ITEMS.register("flesh_ring", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS).stacksTo(1)));
    public static final RegistryObject<Item> DUST_RING = RegistryHandler.ITEMS.register("dust_ring", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS).stacksTo(1)));
    public static final RegistryObject<Item> ICHOR_RING = RegistryHandler.ITEMS.register("ichor_ring", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS).stacksTo(1)));

    // Class materials
    public static final RegistryObject<VisionItem> VISION_FLAME = RegistryHandler.ITEMS.register("vision_flame", () -> new VisionItem(new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant(), Visions.FLAME));
    public static final RegistryObject<VisionItem> VISION_GALE = RegistryHandler.ITEMS.register("vision_gale", () -> new VisionItem(new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant(), Visions.GALE));
    public static final RegistryObject<VisionItem> VISION_STAR = RegistryHandler.ITEMS.register("vision_star", () -> new VisionItem(new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant(), Visions.STAR));

    static void register() {}
}