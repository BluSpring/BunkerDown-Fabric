/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Block
 */
package net.mcreator.bunkerdown.procedures;

import net.mcreator.bunkerdown.init.BunkerDownModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;

public class UnderrootStage2GrowProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        world.setBlock(new BlockPos(x, y, z), ((Block)BunkerDownModBlocks.UNDERROOT.get()).defaultBlockState(), 3);
    }
}

