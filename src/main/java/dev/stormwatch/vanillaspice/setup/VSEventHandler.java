package dev.stormwatch.vanillaspice.setup;

import dev.stormwatch.vanillaspice.data.*;
import dev.stormwatch.vanillaspice.events.*;
import dev.stormwatch.vanillaspice.util.ChargesUtil;
import dev.stormwatch.vanillaspice.util.XPUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.brewing.PlayerBrewedPotionEvent;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.entity.LivingEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import dev.stormwatch.vanillaspice.VanillaSpice;


@Mod.EventBusSubscriber( modid = VanillaSpice.MOD_ID )
public class VSEventHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void onEntityDamageEvent(LivingHurtEvent event) { onLivingHurtEvent.event(event); }
    @SubscribeEvent
    public static void onPotionApplicableEvent(PotionEvent.PotionApplicableEvent event) { onPotionApplicableEvent.event(event); }
    @SubscribeEvent
    public static void onLootingLevelEvent(LootingLevelEvent event) { onLootingLevelEvent.event(event); }
    @SubscribeEvent
    public static void onLivingSpawnEvent(LivingSpawnEvent.SpecialSpawn event) { onLivingSpecialSpawnEvent.event(event); }

    @SubscribeEvent
    public static void onPlayerBrewPotionEvent(PlayerBrewedPotionEvent event) {
        // if player is t1+ alch add charges
        PlayerEntity player = event.getPlayer();
        ItemStack stack = event.getStack();
        if (!player.level.isClientSide()) {
            XPUtil.increaseAlchemyXP(player, 1, 3);

            LOGGER.info(XPUtil.getAlchemyTier(player));
            if (XPUtil.getAlchemyTier(player) >= 1) {
                stack.getCapability(CapabilityPotionCharges.POTION_CHARGES_CAPABILITY).ifPresent(h -> {
                    LOGGER.info("hello from inside the potion charge capability");
                    h.setCharges(3);
                    LOGGER.info(h.getCharges());
                });
                stack.getCapability(CapabilityPotionCharges.POTION_CHARGES_CAPABILITY).ifPresent(h -> {
                    LOGGER.info("after removing pot from stand but v2");
                    LOGGER.info(h.getCharges());
                });
            }
        }
    }

    @SubscribeEvent
    public static void onItemUseStartEvent(LivingEntityUseItemEvent.Start event) {
        ItemStack stack = event.getItem();
        LivingEntity entity = event.getEntityLiving();

        if (stack.getItem() instanceof PotionItem) {
            stack.getCapability(CapabilityPotionCharges.POTION_CHARGES_CAPABILITY).ifPresent(h -> {
                LOGGER.info("start using potion");
                LOGGER.info(h.getCharges());
                // Even here charges read 0, the problem is in the setting/saving of the charges then
            });
        }
    }

    @SubscribeEvent
    public static void onItemUseFinishEvent(LivingEntityUseItemEvent.Finish event) {
        ItemStack stack = event.getItem();
        LivingEntity entity = event.getEntityLiving();

        if (entity instanceof PlayerEntity && !entity.level.isClientSide()) {
            PlayerEntity player = (PlayerEntity) entity;

            if (stack.getItem() instanceof PotionItem) {
                XPUtil.increaseAlchemyXP(player, 4, 8);

                int charges = ChargesUtil.getCharges(stack);
                LOGGER.info(charges);
                if (charges > 0) {
                    ChargesUtil.setCharges(stack, charges - 1);
                    LOGGER.info(ChargesUtil.getCharges(stack));
                    event.setResultStack(stack);
                }
            }
            // check if potion has charges
            // setResultStack() accordingly (either empty bottle (aka dont set it to anything cause thats default) or same potion with -1 stack)
        }

    }

    @SubscribeEvent
    public static void onLivingDeathEvent(LivingDeathEvent event) {
        LivingEntity entity = event.getEntityLiving();
        // Save player stats to restore on respawn
        if (entity instanceof PlayerEntity && !entity.level.isClientSide()) {
            PlayerEntity player = (PlayerEntity) entity;
            XPUtil.savePlayerStats(player);
            player.inventory.clearContent();
        }
    }

    @SubscribeEvent
    public static void onDimensionTravelEvent(EntityTravelToDimensionEvent event) {
        Entity entity = event.getEntity();
        // Save player stats to restore on respawn
        if (entity instanceof PlayerEntity && !entity.level.isClientSide()) {
            PlayerEntity player = (PlayerEntity) entity;
            XPUtil.savePlayerStats(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawnEvent(PlayerEvent.PlayerRespawnEvent event) {
        PlayerEntity player = event.getPlayer();
        // Respawning resets stats so we restore them from the latest checkpoint
        if (!player.level.isClientSide()) {
            XPUtil.restorePlayerStats(player);
        }
    }

    @SubscribeEvent
    public static void onEntityAttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if (entity instanceof PlayerEntity) {
            PlayerStatsProvider provider = new PlayerStatsProvider();
            event.addCapability(new ResourceLocation(VanillaSpice.MOD_ID, "playerstats"), provider);
            event.addListener(provider::invalidate);
        } else if (entity instanceof MonsterEntity) {
            MonsterLevelProvider provider = new MonsterLevelProvider();
            event.addCapability(new ResourceLocation(VanillaSpice.MOD_ID, "monsterlevel"), provider);
            event.addListener(provider::invalidate);
        }
    }

    @SubscribeEvent
    public static void onItemAttachCapabilitiesEvent(AttachCapabilitiesEvent<ItemStack> event) {
        if (event.getObject().getItem() instanceof PotionItem) {
            PotionChargesProvider provider = new PotionChargesProvider();
            event.addCapability(new ResourceLocation(VanillaSpice.MOD_ID, "potioncharges"), provider);
            event.addListener(provider::invalidate);
        }
    }

}