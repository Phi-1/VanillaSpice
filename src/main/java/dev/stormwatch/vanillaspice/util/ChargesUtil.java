package dev.stormwatch.vanillaspice.util;


import dev.stormwatch.vanillaspice.data.CapabilityPotionCharges;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

public class ChargesUtil {

    private static final Logger LOGGER = LogManager.getLogger();

    public static int getCharges(ItemStack stack) {
        AtomicInteger charges = new AtomicInteger();
        stack.getCapability(CapabilityPotionCharges.POTION_CHARGES_CAPABILITY).ifPresent(h -> {
            LOGGER.info("ChargesUtil getCharges");
            charges.set(h.getCharges());
            LOGGER.info(h.getCharges());
        });
        return charges.get();
    }

    public static void setCharges(ItemStack stack, int amount) {
        stack.getCapability(CapabilityPotionCharges.POTION_CHARGES_CAPABILITY).ifPresent(h -> {
            LOGGER.info("ChargesUtil setCharges");
            h.setCharges(amount);
        });
    }

}
