package dev.stormwatch.vanillaspice.setup;

import dev.stormwatch.vanillaspice.effects.*;
import dev.stormwatch.vanillaspice.util.ModifierUtil;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fml.RegistryObject;

public class ModEffects {

    public static final RegistryObject<Effect> REACH = RegistryHandler.EFFECTS.register(
            "reach", () -> new ReachEffect().addAttributeModifier(ForgeMod.REACH_DISTANCE.get(), ModifierUtil.MODIFIER_ID_REACH.toString(), 0.2, AttributeModifier.Operation.MULTIPLY_BASE));
    public static final RegistryObject<Effect> SWIFT_SWIM = RegistryHandler.EFFECTS.register(
            "swift_swim", () -> new SwiftSwimEffect().addAttributeModifier(ForgeMod.SWIM_SPEED.get(), ModifierUtil.MODIFIER_ID_SWIM.toString(), 0.5, AttributeModifier.Operation.MULTIPLY_BASE));
    public static final RegistryObject<Effect> LEAD_FEET = RegistryHandler.EFFECTS.register(
            "lead_feet", () -> (new LeadFeetEffect().addAttributeModifier(Attributes.ATTACK_KNOCKBACK, ModifierUtil.MODIFIER_ID_KNOCKBACK.toString(), 2, AttributeModifier.Operation.ADDITION)).addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, ModifierUtil.MODIFIER_ID_KNOCKBACKRES.toString(), 0.4, AttributeModifier.Operation.ADDITION));
    public static final RegistryObject<Effect> RAGE = RegistryHandler.EFFECTS.register(
            "rage", () -> new RageEffect().addAttributeModifier(Attributes.ATTACK_SPEED, ModifierUtil.MODIFIER_ID_ATTACKSPEED.toString(), 1.0, AttributeModifier.Operation.ADDITION));
    public static final RegistryObject<Effect> CALM = RegistryHandler.EFFECTS.register(
            "calm", () -> new CalmEffect());
    public static final RegistryObject<Effect> RESPLENDENCE = RegistryHandler.EFFECTS.register(
            "resplendence", () -> new ResplendenceEffect().addAttributeModifier(Attributes.LUCK, ModifierUtil.MODIFIER_ID_LUCK.toString(), 3.0, AttributeModifier.Operation.ADDITION));
    public static final RegistryObject<Effect> SHEER_FORCE = RegistryHandler.EFFECTS.register("sheer_force", () -> new SheerForceEffect());


    public static void register() {}

}
