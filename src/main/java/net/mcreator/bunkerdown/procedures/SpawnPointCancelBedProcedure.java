/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.entity.Entity
 *  net.minecraftforge.event.entity.player.PlayerSleepInBedEvent
 *  net.minecraftforge.eventbus.api.Event
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 */
package net.mcreator.bunkerdown.procedures;

import javax.annotation.Nullable;

import net.fabricmc.fabric.api.entity.event.v1.EntitySleepEvents;
import net.mcreator.bunkerdown.network.BunkerDownModVariables;
import net.minecraft.world.entity.Entity;

public class SpawnPointCancelBedProcedure {
    public static void init() {
        EntitySleepEvents.ALLOW_SLEEPING.register(((player, sleepingPos) -> {
            SpawnPointCancelBedProcedure.execute(player);
            return null;
        }));
    }

    private static void execute(Entity entity) {
        if (entity == null) {
            return;
        }
        boolean _setval = true;
        entity.getCapability(BunkerDownModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
            capability.FirstTimeJoined = _setval;
            capability.syncPlayerVariables(entity);
        });
    }
}

