package xyz.bluspring.bunkerdown.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.mcreator.bunkerdown.BunkerDownMod;
import net.mcreator.bunkerdown.init.BunkerDownModBlocks;
import net.minecraft.client.renderer.RenderType;

public class BunkerDownClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                BunkerDownModBlocks.AIRLOCK_IRON_DOOR.get(),
                BunkerDownModBlocks.STEEL_VENT.get(),
                BunkerDownModBlocks.UNDERROOT.get(),
                BunkerDownModBlocks.UNDERROOT_STAGE_1.get(),
                BunkerDownModBlocks.UNDERROOT_STAGE_2.get()
        );

        BunkerDownMod.PACKET_HANDLER.initClientListener();
    }
}
