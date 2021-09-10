package dev.stormwatch.vanillaspice.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

public class CapabilityPotionCharges {

    private static final Logger LOGGER = LogManager.getLogger();

    @CapabilityInject(IPotionCharges.class)
    public static Capability<IPotionCharges> POTION_CHARGES_CAPABILITY = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(IPotionCharges.class, new Storage(), DefaultPotionCharges::new);
    }

    private static class Storage implements Capability.IStorage<IPotionCharges> {

        @Nullable
        @Override
        public INBT writeNBT(Capability<IPotionCharges> capability, IPotionCharges instance, Direction side) {
            LOGGER.info("potion writeNBT");
            CompoundNBT tag = new CompoundNBT();
            tag.putInt("charges", instance.getCharges());
            return tag;
        }

        @Override
        public void readNBT(Capability<IPotionCharges> capability, IPotionCharges instance, Direction side, INBT nbt) {
            LOGGER.info("potion readNBT");
            int charges = ((CompoundNBT) nbt).getInt("charges");
            instance.setCharges(charges);
        }

    }

}
