package dev.stormwatch.vanillaspice.data;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

public class ModifiedEffectInstance extends EffectInstance {

    public ModifiedEffectInstance(Effect effecttype, int duration, int amplifier) {
        super(effecttype, duration, amplifier);
    }

}
