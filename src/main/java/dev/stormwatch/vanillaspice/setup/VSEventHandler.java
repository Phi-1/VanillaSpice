package dev.stormwatch.vanillaspice.setup;

import dev.stormwatch.vanillaspice.data.CapabilityPlayerStats;
import dev.stormwatch.vanillaspice.data.PlayerStatsProvider;
import dev.stormwatch.vanillaspice.util.XPUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.brewing.PlayerBrewedPotionEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraft.entity.LivingEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import dev.stormwatch.vanillaspice.VanillaSpice;

import java.util.concurrent.ThreadLocalRandom;


@Mod.EventBusSubscriber( modid = VanillaSpice.MOD_ID )
public class VSEventHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void onEntityDamageEvent(LivingHurtEvent event) {
        float baseDamage = event.getAmount();
        float finalDamage = baseDamage;
        DamageSource source = event.getSource();
        Entity sourceEntity = source.getEntity();

        if (sourceEntity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) sourceEntity;

            if (source.isProjectile()) {
                if (source.toString().toLowerCase().contains("trident")) {
                    XPUtil.increaseMeleeXP(player, 3, 6);
                }
                else {
                    XPUtil.increaseArcheryXP(player, 1, 3);
                }
            } else if (source.isMagic()) {
                XPUtil.increaseAlchemyXP(player, 4, 8);
            } else {
                XPUtil.increaseMeleeXP(player, 1, 5);
            }
        }

        event.setAmount(finalDamage);
    }

    @SubscribeEvent
    public static void onPlayerBrewPotionEvent(PlayerBrewedPotionEvent event) {
        // if player is t1+ alch add charges
    }

    @SubscribeEvent
    public static void onItemUseFinishEvent(LivingEntityUseItemEvent.Finish event) {
        // get used item
        ItemStack item = event.getItem();
        // check if item is a potion
        // check if potion has charges
        // setResultStack() accordingly (either empty bottle (aka dont set it to anything cause thats default) or same potion with -1 stack)
    }

    @SubscribeEvent
    public static void onEntityAttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof PlayerEntity) {
            PlayerStatsProvider provider = new PlayerStatsProvider();
            event.addCapability(new ResourceLocation(VanillaSpice.MOD_ID, "playerstats"), provider);
            event.addListener(provider::invalidate);
        }
    }

    @SubscribeEvent
    public static void onItemAttachCapabilitiesEvent(AttachCapabilitiesEvent<Item> event) {
        if (event.getObject() instanceof PotionItem) {
            // add charges capability with charge set to 0
        }
    }

}