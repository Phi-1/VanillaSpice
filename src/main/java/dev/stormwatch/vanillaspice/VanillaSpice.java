package dev.stormwatch.vanillaspice;

import dev.stormwatch.vanillaspice.data.CapabilityMonsterLevel;
import dev.stormwatch.vanillaspice.data.CapabilityPlayerStats;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import dev.stormwatch.vanillaspice.setup.RegistryHandler;
import dev.stormwatch.vanillaspice.setup.VSEventHandler;

@Mod(VanillaSpice.MOD_ID)
public class VanillaSpice
{

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "vanillaspice";

    public VanillaSpice() {
        RegistryHandler.register();
        MinecraftForge.EVENT_BUS.register(VSEventHandler.class);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        CapabilityPlayerStats.register();
        CapabilityMonsterLevel.register();
    }
}
