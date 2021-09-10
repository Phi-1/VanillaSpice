package dev.stormwatch.vanillaspice.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerStatsProvider implements ICapabilitySerializable<CompoundNBT> {

    private final DefaultPlayerStats playerStats = new DefaultPlayerStats();
    private final LazyOptional<IPlayerStats> playerStatsOptional = LazyOptional.of(() -> playerStats);

    public void invalidate() { playerStatsOptional.invalidate(); }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return playerStatsOptional.cast();
    }

    @Override
    public CompoundNBT serializeNBT() {
        if (CapabilityPlayerStats.PLAYER_STATS_CAPABILITY == null) {
            return new CompoundNBT();
        } else {
            return (CompoundNBT) CapabilityPlayerStats.PLAYER_STATS_CAPABILITY.writeNBT(playerStats, null);
        }
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if (CapabilityPlayerStats.PLAYER_STATS_CAPABILITY != null) {
            CapabilityPlayerStats.PLAYER_STATS_CAPABILITY.readNBT(playerStats, null, nbt);
        }
    }

}
