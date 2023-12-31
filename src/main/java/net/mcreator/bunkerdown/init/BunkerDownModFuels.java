/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.block.Block
 *  net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 */
package net.mcreator.bunkerdown.init;

import net.fabricmc.fabric.api.registry.FuelRegistry;

public class BunkerDownModFuels {
    public static void init() {
        FuelRegistry.INSTANCE.add(BunkerDownModBlocks.UNDERROOT.get().asItem(), 25);
        FuelRegistry.INSTANCE.add(BunkerDownModBlocks.UNDERROOT_STAGE_2.get().asItem(), 20);
        FuelRegistry.INSTANCE.add(BunkerDownModItems.BRITTLE_PICKAXE.get(), 20);
    }
}

