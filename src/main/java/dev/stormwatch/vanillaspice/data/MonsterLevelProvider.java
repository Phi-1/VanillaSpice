package dev.stormwatch.vanillaspice.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MonsterLevelProvider implements ICapabilitySerializable<CompoundNBT> {

    private final DefaultMonsterLevel monsterLevel = new DefaultMonsterLevel();
    private final LazyOptional<IMonsterLevel> monsterLevelOptional = LazyOptional.of(() -> monsterLevel);

    public void invalidate() { monsterLevelOptional.invalidate(); }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return CapabilityMonsterLevel.MONSTER_LEVEL_CAPABILITY.orEmpty(cap, monsterLevelOptional);
    }

    @Override
    public CompoundNBT serializeNBT() {
        if (CapabilityMonsterLevel.MONSTER_LEVEL_CAPABILITY == null) {
            return new CompoundNBT();
        } else {
            return (CompoundNBT) CapabilityMonsterLevel.MONSTER_LEVEL_CAPABILITY.writeNBT(monsterLevel, null);
        }
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if (CapabilityMonsterLevel.MONSTER_LEVEL_CAPABILITY != null) {
            CapabilityMonsterLevel.MONSTER_LEVEL_CAPABILITY.readNBT(monsterLevel, null, nbt);
        }
    }

}
