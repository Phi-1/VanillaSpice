package dev.stormwatch.vanillaspice.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PotionChargesProvider implements ICapabilitySerializable<CompoundNBT> {

    private final DefaultPotionCharges potionCharges = new DefaultPotionCharges();
    private final LazyOptional<IPotionCharges> potionChargesOptional = LazyOptional.of(() -> potionCharges);

    public void invalidate() { potionChargesOptional.invalidate(); }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return potionChargesOptional.cast();
    }

    @Override
    public CompoundNBT serializeNBT() {
        if (CapabilityPotionCharges.POTION_CHARGES_CAPABILITY == null) {
            return new CompoundNBT();
        } else {
            return (CompoundNBT) CapabilityPotionCharges.POTION_CHARGES_CAPABILITY.writeNBT(potionCharges, null);
        }
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if (CapabilityPotionCharges.POTION_CHARGES_CAPABILITY != null) {
            CapabilityPotionCharges.POTION_CHARGES_CAPABILITY.readNBT(potionCharges, null, nbt);
        }
    }

}
