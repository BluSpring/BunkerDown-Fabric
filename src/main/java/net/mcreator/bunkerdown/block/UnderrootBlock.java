/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.FlowerBlock
 *  net.minecraft.world.level.block.SoundType
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.material.Material
 *  net.minecraft.world.level.storage.loot.LootContext$Builder
 */
package net.mcreator.bunkerdown.block;

import java.util.Collections;
import java.util.List;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.mcreator.bunkerdown.procedures.UnderrootGenerateConditionProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;

public class UnderrootBlock
extends FlowerBlock {
    public UnderrootBlock() {
        super(MobEffects.MOVEMENT_SPEED, 100, BlockBehaviour.Properties.of((Material)Material.PLANT).sound(SoundType.GRASS).instabreak().lightLevel(s -> 2).noCollission());

        FlammableBlockRegistry.getDefaultInstance().add(this, 100, 60);
    }

    public int getEffectDuration() {
        return 100;
    }

    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 100;
    }

    public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        return 60;
    }

    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty()) {
            return dropsOriginal;
        }
        return Collections.singletonList(new ItemStack((ItemLike)this));
    }

    public boolean mayPlaceOn(BlockState groundState, BlockGetter worldIn, BlockPos pos) {
        boolean additionalCondition = true;
        if (worldIn instanceof LevelAccessor) {
            LevelAccessor world = (LevelAccessor)worldIn;
            int x = pos.getX();
            int y = pos.getY() + 1;
            int z = pos.getZ();
            BlockState blockstate = world.getBlockState(pos.above());
            additionalCondition = UnderrootGenerateConditionProcedure.execute(world, y);
        }
        return (groundState.is(Blocks.GRAVEL) || groundState.is(Blocks.STONE) || groundState.is(Blocks.DIRT) || groundState.is(Blocks.COARSE_DIRT) || groundState.is(Blocks.PODZOL) || groundState.is(Blocks.COBBLESTONE) || groundState.is(Blocks.MOSSY_COBBLESTONE) || groundState.is(Blocks.GRANITE) || groundState.is(Blocks.DIORITE) || groundState.is(Blocks.ANDESITE)) && additionalCondition;
    }

    public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
        BlockPos blockpos = pos.below();
        BlockState groundState = worldIn.getBlockState(blockpos);
        return this.mayPlaceOn(groundState, (BlockGetter)worldIn, blockpos);
    }
}

