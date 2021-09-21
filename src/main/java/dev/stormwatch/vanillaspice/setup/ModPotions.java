package dev.stormwatch.vanillaspice.setup;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;

public class ModPotions {

    public static final RegistryObject<Potion> BUILDERS_POTION = RegistryHandler.POTIONS.register(
            "builders_potion",
            () -> new Potion(new EffectInstance(ModEffects.REACH.get(), 6000, 0)));

    public static final RegistryObject<Potion> BUILDERS_POTION_LONG = RegistryHandler.POTIONS.register(
            "builders_potion_long",
            () -> new Potion(new EffectInstance(ModEffects.REACH.get(), 14400, 0)));

    public static final RegistryObject<Potion> BUILDERS_POTION_STRONG = RegistryHandler.POTIONS.register(
            "builders_potion_strong",
            () -> new Potion(new EffectInstance(ModEffects.REACH.get(), 6000, 1)));


    public static final RegistryObject<Potion> SWIFT_SWIM_POTION = RegistryHandler.POTIONS.register(
            "swift_swim_potion",
            () -> new Potion(new EffectInstance(ModEffects.SWIFT_SWIM.get(), 3600, 0)));

    public static final RegistryObject<Potion> SWIFT_SWIM_POTION_LONG = RegistryHandler.POTIONS.register(
            "swift_swim_potion_long",
            () -> new Potion(new EffectInstance(ModEffects.SWIFT_SWIM.get(), 9600, 0)));

    public static final RegistryObject<Potion> SWIFT_SWIM_POTION_STRONG = RegistryHandler.POTIONS.register(
            "swift_swim_potion_strong",
            () -> new Potion(new EffectInstance(ModEffects.SWIFT_SWIM.get(), 3600, 1)));


    public static final RegistryObject<Potion> LEAD_FEET_POTION = RegistryHandler.POTIONS.register(
            "lead_feet_potion",
            () -> new Potion(new EffectInstance(ModEffects.LEAD_FEET.get(), 2400, 0)));

    public static final RegistryObject<Potion> LEAD_FEET_POTION_LONG = RegistryHandler.POTIONS.register(
            "lead_feet_potion_long",
            () -> new Potion(new EffectInstance(ModEffects.LEAD_FEET.get(), 6000, 0)));

    public static final RegistryObject<Potion> LEAD_FEET_POTION_STRONG = RegistryHandler.POTIONS.register(
            "lead_feet_potion_strong",
            () -> new Potion(new EffectInstance(ModEffects.LEAD_FEET.get(), 2400, 1)));


    public static final RegistryObject<Potion> RAGE_POTION = RegistryHandler.POTIONS.register(
            "rage_potion",
            () -> new Potion(new EffectInstance(ModEffects.RAGE.get(), 600, 0)));

    public static final RegistryObject<Potion> RAGE_POTION_LONG = RegistryHandler.POTIONS.register(
            "rage_potion_long",
            () -> new Potion(new EffectInstance(ModEffects.RAGE.get(), 1800, 0)));

    public static final RegistryObject<Potion> RAGE_POTION_STRONG = RegistryHandler.POTIONS.register(
            "rage_potion_strong",
            () -> new Potion(new EffectInstance(ModEffects.RAGE.get(), 600, 1)));


    public static final RegistryObject<Potion> CALM_POTION = RegistryHandler.POTIONS.register(
            "calm_potion",
            () -> new Potion(new EffectInstance(ModEffects.CALM.get(), 600, 0)));

    public static final RegistryObject<Potion> CALM_POTION_LONG = RegistryHandler.POTIONS.register(
            "calm_potion_long",
            () -> new Potion(new EffectInstance(ModEffects.CALM.get(), 1800, 0)));

    public static final RegistryObject<Potion> CALM_POTION_STRONG = RegistryHandler.POTIONS.register(
            "calm_potion_strong",
            () -> new Potion(new EffectInstance(ModEffects.CALM.get(), 600, 1)));


    public static final RegistryObject<Potion> RESPLENDENCE_POTION = RegistryHandler.POTIONS.register(
            "resplendence_potion",
            () -> new Potion(new EffectInstance(ModEffects.RESPLENDENCE.get(), 3600, 0)));

    public static final RegistryObject<Potion> RESPLENDENCE_POTION_LONG = RegistryHandler.POTIONS.register(
            "resplendence_potion_long",
            () -> new Potion(new EffectInstance(ModEffects.RESPLENDENCE.get(), 6000, 0)));

    public static final RegistryObject<Potion> RESPLENDENCE_POTION_STRONG = RegistryHandler.POTIONS.register(
            "resplendence_potion_strong",
            () -> new Potion(new EffectInstance(ModEffects.RESPLENDENCE.get(), 3600, 1)));

    public static void register() {}

}
