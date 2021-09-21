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

public class CapabilityPlayerStats {

    private static final Logger LOGGER = LogManager.getLogger();

    @CapabilityInject(IPlayerStats.class)
    public static Capability<IPlayerStats> PLAYER_STATS_CAPABILITY = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(IPlayerStats.class, new Storage(), DefaultPlayerStats::new);
    }

    private static class Storage implements Capability.IStorage<IPlayerStats> {

        @Nullable
        @Override
        public INBT writeNBT(Capability<IPlayerStats> capability, IPlayerStats instance, Direction side) {
            CompoundNBT tag = new CompoundNBT();
            tag.putLong("mainXP", instance.getMainXP());
            tag.putInt("mainLevel", instance.getMainLevel());
            tag.putInt("meleeXP", instance.getMeleeXP());
            tag.putInt("archeryXP", instance.getArcheryXP());
            tag.putInt("alchemyXP", instance.getAlchemyXP());
            tag.putInt("meleeLevel", instance.getMeleeLevel());
            tag.putInt("archeryLevel", instance.getArcheryLevel());
            tag.putInt("alchemyLevel", instance.getAlchemyLevel());
            tag.putInt("meleeTier", instance.getMeleeTier());
            tag.putInt("archeryTier", instance.getArcheryTier());
            tag.putInt("alchemyTier", instance.getAlchemyTier());
            return tag;
        }

        @Override
        public void readNBT(Capability<IPlayerStats> capability, IPlayerStats instance, Direction side, INBT nbt) {
            long mainXP = ((CompoundNBT) nbt).getLong("mainXP");
            int mainLevel = ((CompoundNBT) nbt).getInt("mainLevel");
            int meleeXP = ((CompoundNBT) nbt).getInt("meleeXP");
            int archeryXP = ((CompoundNBT) nbt).getInt("archeryXP");
            int alchemyXP = ((CompoundNBT) nbt).getInt("alchemyXP");
            int meleeLevel = ((CompoundNBT) nbt).getInt("meleeLevel");
            int archeryLevel = ((CompoundNBT) nbt).getInt("archeryLevel");
            int alchemyLevel = ((CompoundNBT) nbt).getInt("alchemyLevel");
            int meleeTier = ((CompoundNBT) nbt).getInt("meleeTier");
            int archeryTier = ((CompoundNBT) nbt).getInt("archeryTier");
            int alchemyTier = ((CompoundNBT) nbt).getInt("alchemyTier");
            instance.setMainXP(mainXP);
            instance.setMainLevel(mainLevel);
            instance.setMeleeXP(meleeXP);
            instance.setArcheryXP(archeryXP);
            instance.setAlchemyXP(alchemyXP);
            instance.setMeleeLevel(meleeLevel);
            instance.setArcheryLevel(archeryLevel);
            instance.setAlchemyLevel(alchemyLevel);
            instance.setMeleeTier(meleeTier);
            instance.setArcheryTier(archeryTier);
            instance.setAlchemyTier(alchemyTier);
        }

    }

}