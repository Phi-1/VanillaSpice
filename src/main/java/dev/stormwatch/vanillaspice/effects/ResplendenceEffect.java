package dev.stormwatch.vanillaspice.effects;

import dev.stormwatch.vanillaspice.setup.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResplendenceEffect extends Effect {

    private static final Logger LOGGER = LogManager.getLogger();

    public ResplendenceEffect() { super(EffectType.BENEFICIAL, 0xffdd00); }

}
