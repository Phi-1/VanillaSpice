package dev.stormwatch.vanillaspice.setup;

import dev.stormwatch.vanillaspice.items.VisionItem;
import dev.stormwatch.vanillaspice.lib.Classes;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {
    // Armor materials
    public static final RegistryObject<Item> LEATHER_PATCH = RegistryHandler.ITEMS.register("leather_patch", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> IRON_PLATE = RegistryHandler.ITEMS.register("iron_plate", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> CRYSTALLINE_GUARD = RegistryHandler.ITEMS.register("crystalline_guard", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));

    // Visions
    public static final RegistryObject<VisionItem> VISION_FLAME = RegistryHandler.ITEMS.register("vision_flame", () -> new VisionItem(new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant(), Classes.MELEE, 1));
    public static final RegistryObject<VisionItem> VISION_GUST = RegistryHandler.ITEMS.register("vision_gust", () -> new VisionItem(new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant(), Classes.ARCHERY, 1));
    public static final RegistryObject<VisionItem> VISION_STAR = RegistryHandler.ITEMS.register("vision_star", () -> new VisionItem(new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant(), Classes.ALCHEMY, 1));

    public static final RegistryObject<VisionItem> VISION_BLAZE = RegistryHandler.ITEMS.register("vision_blaze", () -> new VisionItem(new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(1).rarity(Rarity.RARE).fireResistant(), Classes.MELEE, 2));
    public static final RegistryObject<VisionItem> VISION_GALE = RegistryHandler.ITEMS.register("vision_gale", () -> new VisionItem(new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(1).rarity(Rarity.RARE).fireResistant(), Classes.ARCHERY, 2));
    public static final RegistryObject<VisionItem> VISION_NEBULA = RegistryHandler.ITEMS.register("vision_nebula", () -> new VisionItem(new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(1).rarity(Rarity.RARE).fireResistant(), Classes.ALCHEMY, 2));

    public static final RegistryObject<VisionItem> VISION_INFERNO = RegistryHandler.ITEMS.register("vision_inferno", () -> new VisionItem(new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(1).rarity(Rarity.EPIC).fireResistant(), Classes.MELEE, 3));
    public static final RegistryObject<VisionItem> VISION_HURRICANE = RegistryHandler.ITEMS.register("vision_hurricane", () -> new VisionItem(new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(1).rarity(Rarity.EPIC).fireResistant(), Classes.ARCHERY, 3));
    public static final RegistryObject<VisionItem> VISION_SINGULARITY = RegistryHandler.ITEMS.register("vision_singularity", () -> new VisionItem(new Item.Properties().tab(ItemGroup.TAB_MISC).stacksTo(1).rarity(Rarity.EPIC).fireResistant(), Classes.ALCHEMY, 3));

    // Misc items
    public static final RegistryObject<Item> GOLDEN_BEETROOT = RegistryHandler.ITEMS.register("golden_beetroot", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_BREWING).food(new Food.Builder().nutrition(4).saturationMod(1.2F).build())));
    public static final RegistryObject<Item> CELLULAR_IRON = RegistryHandler.ITEMS.register("cellular_iron", () -> new Item(new Item.Properties().tab(ItemGroup.TAB_BREWING).food(new Food.Builder().nutrition(3).saturationMod(0.2F).alwaysEat().build())));

    static void register() {}
}