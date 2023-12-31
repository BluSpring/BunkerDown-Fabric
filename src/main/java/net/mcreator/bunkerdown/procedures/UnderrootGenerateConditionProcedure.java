/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.LevelAccessor
 */
package net.mcreator.bunkerdown.procedures;

import net.mcreator.bunkerdown.init.BunkerDownModGameRules;
import net.minecraft.world.level.LevelAccessor;

public class UnderrootGenerateConditionProcedure {
    public static boolean execute(LevelAccessor world, double y) {
        return y <= (double)world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.UNDERROOTGROWABLEHEIGHT);
    }
}

