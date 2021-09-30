package dev.stormwatch.vanillaspice.effects;

import dev.stormwatch.vanillaspice.setup.ModEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.Event;

public class SheerForceEffect extends Effect {

    public SheerForceEffect() { super(EffectType.BENEFICIAL, 0xe3afd2); }

    public static void onCriticalHitEvent(CriticalHitEvent event) {
        PlayerEntity player = event.getPlayer();
        if (!player.level.isClientSide()) {
            EffectInstance sheerForce = player.getEffect(ModEffects.SHEER_FORCE.get());
            if (sheerForce == null) { return; }
            if (!event.isVanillaCritical()) {
                event.setResult(Event.Result.ALLOW);
            }
        }
    }

}
