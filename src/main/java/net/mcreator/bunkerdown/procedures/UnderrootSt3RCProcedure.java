/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.state.BlockState
 */
package net.mcreator.bunkerdown.procedures;

import net.mcreator.bunkerdown.init.BunkerDownModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class UnderrootSt3RCProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        BlockPos _pos = new BlockPos(x, y, z);
        Block.dropResources((BlockState)world.getBlockState(_pos), (LevelAccessor)world, (BlockPos)new BlockPos(x, y, z), null);
        world.destroyBlock(_pos, false);
        world.setBlock(new BlockPos(x, y, z), ((Block)BunkerDownModBlocks.UNDERROOT_STAGE_1.get()).defaultBlockState(), 3);
    }
}

