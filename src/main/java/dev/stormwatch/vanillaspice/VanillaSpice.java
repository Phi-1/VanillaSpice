package dev.stormwatch.vanillaspice;

import dev.stormwatch.vanillaspice.data.CapabilityMonsterLevel;
import dev.stormwatch.vanillaspice.data.CapabilityPlayerStats;
import dev.stormwatch.vanillaspice.events.onItemToolTipEvent;
import dev.stormwatch.vanillaspice.recipes.ModPotionRecipe;
import dev.stormwatch.vanillaspice.setup.ModItems;
import dev.stormwatch.vanillaspice.setup.ModPotions;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import dev.stormwatch.vanillaspice.setup.RegistryHandler;
import dev.stormwatch.vanillaspice.setup.VSEventHandler;

@Mod(VanillaSpice.MOD_ID)
public class VanillaSpice
{

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "vanillaspice";

    public VanillaSpice() {
        RegistryHandler.register();
        MinecraftForge.EVENT_BUS.register(VSEventHandler.class);
        MinecraftForge.EVENT_BUS.register(this);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> this::registerClientEvents);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        CapabilityPlayerStats.register();
        CapabilityMonsterLevel.register();
        event.enqueueWork(this::registerBrewingRecipes);
    }

    private void registerClientEvents() {
        MinecraftForge.EVENT_BUS.addListener(onItemToolTipEvent::event);
    }

    private void registerBrewingRecipes() {
        // Builder's potion
        BrewingRecipeRegistry.addRecipe(new ModPotionRecipe(ModItems.GOLDEN_BEETROOT.get(), Potions.AWKWARD, ModPotions.BUILDERS_POTION.get()));
        BrewingRecipeRegistry.addRecipe(new ModPotionRecipe(Items.REDSTONE, ModPotions.BUILDERS_POTION.get(), ModPotions.BUILDERS_POTION_LONG.get()));
        BrewingRecipeRegistry.addRecipe(new ModPotionRecipe(Items.GLOWSTONE_DUST, ModPotions.BUILDERS_POTION.get(), ModPotions.BUILDERS_POTION_STRONG.get()));
        // Potion of Swift Swim
        BrewingRecipeRegistry.addRecipe(new ModPotionRecipe(Items.SEA_PICKLE, Potions.AWKWARD, ModPotions.SWIFT_SWIM_POTION.get()));
        BrewingRecipeRegistry.addRecipe(new ModPotionRecipe(Items.REDSTONE, ModPotions.SWIFT_SWIM_POTION.get(), ModPotions.SWIFT_SWIM_POTION_LONG.get()));
        BrewingRecipeRegistry.addRecipe(new ModPotionRecipe(Items.GLOWSTONE_DUST, ModPotions.SWIFT_SWIM_POTION.get(), ModPotions.SWIFT_SWIM_POTION_STRONG.get()));
        // Potion of Lead Feet
        BrewingRecipeRegistry.addRecipe(new ModPotionRecipe(ModItems.CELLULAR_IRON.get(), Potions.AWKWARD, ModPotions.LEAD_FEET_POTION.get()));
        BrewingRecipeRegistry.addRecipe(new ModPotionRecipe(Items.REDSTONE, ModPotions.LEAD_FEET_POTION.get(), ModPotions.LEAD_FEET_POTION_LONG.get()));
        BrewingRecipeRegistry.addRecipe(new ModPotionRecipe(Items.GLOWSTONE_DUST, ModPotions.LEAD_FEET_POTION.get(), ModPotions.LEAD_FEET_POTION_STRONG.get()));
        // Potion of Inner Rage
        BrewingRecipeRegistry.addRecipe(new ModPotionRecipe(Items.CRIMSON_FUNGUS, Potions.AWKWARD, ModPotions.RAGE_POTION.get()));
        BrewingRecipeRegistry.addRecipe(new ModPotionRecipe(Items.REDSTONE, ModPotions.RAGE_POTION.get(), ModPotions.RAGE_POTION_LONG.get()));
        BrewingRecipeRegistry.addRecipe(new ModPotionRecipe(Items.GLOWSTONE_DUST, ModPotions.RAGE_POTION.get(), ModPotions.RAGE_POTION_STRONG.get()));
        // Potion of Inner Calm
        BrewingRecipeRegistry.addRecipe(new ModPotionRecipe(Items.WARPED_FUNGUS, Potions.AWKWARD, ModPotions.CALM_POTION.get()));
        BrewingRecipeRegistry.addRecipe(new ModPotionRecipe(Items.REDSTONE, ModPotions.CALM_POTION.get(), ModPotions.CALM_POTION_LONG.get()));
        BrewingRecipeRegistry.addRecipe(new ModPotionRecipe(Items.GLOWSTONE_DUST, ModPotions.CALM_POTION.get(), ModPotions.CALM_POTION_STRONG.get()));
        // Potion of Resplendence
        BrewingRecipeRegistry.addRecipe(new ModPotionRecipe(Items.HONEY_BOTTLE, Potions.AWKWARD, ModPotions.RESPLENDENCE_POTION.get()));
        BrewingRecipeRegistry.addRecipe(new ModPotionRecipe(Items.REDSTONE, ModPotions.RESPLENDENCE_POTION.get(), ModPotions.RESPLENDENCE_POTION_LONG.get()));
        BrewingRecipeRegistry.addRecipe(new ModPotionRecipe(Items.GLOWSTONE_DUST, ModPotions.RESPLENDENCE_POTION.get(), ModPotions.RESPLENDENCE_POTION_STRONG.get()));
    }
}
