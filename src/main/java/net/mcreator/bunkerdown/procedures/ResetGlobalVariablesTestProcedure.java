/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.LevelAccessor
 */
package net.mcreator.bunkerdown.procedures;

import net.mcreator.bunkerdown.network.BunkerDownModVariables;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;

public class ResetGlobalVariablesTestProcedure {
    public static void execute(LevelAccessor world, Entity entity) {
        if (entity == null) {
            return;
        }
        BunkerDownModVariables.WorldVariables.get((LevelAccessor)world).WorldFirstStart = false;
        BunkerDownModVariables.WorldVariables.get(world).syncData(world);
        boolean _setval = false;
        entity.getCapability(BunkerDownModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
            capability.FirstTimeJoined = _setval;
            capability.syncPlayerVariables(entity);
        });
    }
}

