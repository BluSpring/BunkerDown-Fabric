package xyz.bluspring.bunkerdown;

import net.fabricmc.api.ModInitializer;
import net.mcreator.bunkerdown.BunkerDownMod;
import net.mcreator.bunkerdown.init.*;
import net.mcreator.bunkerdown.network.BunkerDownModVariables;
import net.mcreator.bunkerdown.procedures.SetPlayerBunkerSpawnProcedure;
import net.mcreator.bunkerdown.procedures.SpawnPointCancelBedProcedure;
import net.mcreator.bunkerdown.procedures.StartWorldProcedure;

public class BunkerDown implements ModInitializer {
    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        var mod = new BunkerDownMod();

        BunkerDownModFuels.init();
        BunkerDownModGameRules.init();

        BunkerDownModVariables.init();
        StartWorldProcedure.init();
        SpawnPointCancelBedProcedure.init();
        SetPlayerBunkerSpawnProcedure.init();

        BunkerDownMod.PACKET_HANDLER.initServerListener();
    }
}
