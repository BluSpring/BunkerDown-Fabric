/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.FlowerBlock
 *  net.minecraft.world.level.block.SoundType
 *  net.minecraft.world.level.block.state.BlockBehaviour$Properties
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.material.Material
 *  net.minecraft.world.level.storage.loot.LootContext$Builder
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult
 */
package net.mcreator.bunkerdown.block;

import java.util.Collections;
import java.util.List;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.mcreator.bunkerdown.init.BunkerDownModBlocks;
import net.mcreator.bunkerdown.procedures.UnderrootGenerateConditionProcedure;
import net.mcreator.bunkerdown.procedures.UnderrootSt2RCProcedure;
import net.mcreator.bunkerdown.procedures.UnderrootStage2GrowProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class UnderrootStage2Block
extends FlowerBlock {
    public UnderrootStage2Block() {
        super(MobEffects.MOVEMENT_SPEED, 100, BlockBehaviour.Properties.of((Material)Material.PLANT).randomTicks().sound(SoundType.GRASS).instabreak().noCollission());

        FlammableBlockRegistry.getDefaultInstance().add(this, 100, 60);
    }

    public int getEffectDuration() {
        return 100;
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack((ItemLike)BunkerDownModBlocks.UNDERROOT_STAGE_1.get());
    }

    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty()) {
            return dropsOriginal;
        }
        return Collections.singletonList(new ItemStack((ItemLike)BunkerDownModBlocks.UNDERROOT_STAGE_1.get()));
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

    public void tick(BlockState blockstate, ServerLevel world, BlockPos pos, RandomSource random) {
        super.tick(blockstate, world, pos, random);
        UnderrootStage2GrowProcedure.execute((LevelAccessor)world, pos.getX(), pos.getY(), pos.getZ());
    }

    public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
        super.use(blockstate, world, pos, entity, hand, hit);
        UnderrootSt2RCProcedure.execute((LevelAccessor)world, pos.getX(), pos.getY(), pos.getZ(), (Entity)entity);
        return InteractionResult.SUCCESS;
    }
}

