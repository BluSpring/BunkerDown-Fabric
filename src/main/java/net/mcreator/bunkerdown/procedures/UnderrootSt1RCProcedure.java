/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Block
 */
package net.mcreator.bunkerdown.procedures;

import net.mcreator.bunkerdown.init.BunkerDownModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;

public class UnderrootSt1RCProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        block8: {
            block10: {
                block9: {
                    ItemStack itemStack;
                    if (entity == null) {
                        return;
                    }
                    if (entity instanceof LivingEntity) {
                        LivingEntity _livEnt = (LivingEntity)entity;
                        itemStack = _livEnt.getMainHandItem();
                    } else {
                        itemStack = ItemStack.EMPTY;
                    }
                    if (itemStack.getItem() != Items.BONE_MEAL) break block8;
                    if (!(entity instanceof Player)) break block9;
                    Player _plr = (Player)entity;
                    if (_plr.getAbilities().instabuild) break block10;
                }
                if (entity instanceof Player) {
                    Player _player = (Player)entity;
                    ItemStack _stktoremove = new ItemStack((ItemLike)Items.BONE_MEAL);
                    _player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, (Container)_player.inventoryMenu.getCraftSlots());
                }
            }
            if (world instanceof ServerLevel) {
                ServerLevel _level = (ServerLevel)world;
                _level.sendParticles((ParticleOptions)ParticleTypes.HAPPY_VILLAGER, x, y, z, 5, 1.0, 1.0, 1.0, 1.0);
            }
            world.setBlock(new BlockPos(x, y, z), ((Block)BunkerDownModBlocks.UNDERROOT_STAGE_2.get()).defaultBlockState(), 3);
        }
    }
}

