package dev.stormwatch.vanillaspice.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class CapabilityMonsterLevel {

    @CapabilityInject(IMonsterLevel.class)
    public static Capability<IMonsterLevel> MONSTER_LEVEL_CAPABILITY = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(IMonsterLevel.class, new CapabilityMonsterLevel.Storage(), DefaultMonsterLevel::new);
    }

    private static class Storage implements Capability.IStorage<IMonsterLevel> {

        @Nullable
        @Override
        public INBT writeNBT(Capability<IMonsterLevel> capability, IMonsterLevel instance, Direction side) {
            CompoundNBT tag = new CompoundNBT();
            tag.putInt("level", instance.getLevel());
            return tag;
        }

        @Override
        public void readNBT(Capability<IMonsterLevel> capability, IMonsterLevel instance, Direction side, INBT nbt) {
            int level = ((CompoundNBT) nbt).getInt("level");
            instance.setLevel(level);
        }

    }

}
