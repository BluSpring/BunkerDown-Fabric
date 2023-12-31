/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.advancements.Advancement
 *  net.minecraft.advancements.AdvancementProgress
 *  net.minecraft.core.BlockPos
 *  net.minecraft.network.chat.Component
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.event.TickEvent$PlayerTickEvent
 *  net.minecraftforge.eventbus.api.Event
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 */
package net.mcreator.bunkerdown.procedures;

import java.util.Iterator;
import javax.annotation.Nullable;

import io.github.fabricators_of_create.porting_lib.event.common.PlayerTickEvents;
import net.mcreator.bunkerdown.init.BunkerDownModGameRules;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class SetPlayerBunkerSpawnProcedure {
    public static void init() {
        PlayerTickEvents.END.register((player -> {
            SetPlayerBunkerSpawnProcedure.execute(player.level, player);
        }));
    }

    private static void execute(LevelAccessor world, Entity entity) {
        block17: {
            ResourceKey resourceKey;
            block16: {
                if (entity == null) {
                    return;
                }
                if (!(entity instanceof ServerPlayer _plr)) break block16;
                if (_plr.level instanceof ServerLevel && _plr.getAdvancements().getOrStartProgress(_plr.server.getAdvancements().getAdvancement(new ResourceLocation("bunker_down:first_joined_world"))).isDone()) break block17;
            }
            if (world instanceof Level _lvl) {
                resourceKey = _lvl.dimension();
            } else {
                resourceKey = Level.OVERWORLD;
            }
            if (resourceKey == Level.OVERWORLD && world.getLevelData().getGameRules().getBoolean(BunkerDownModGameRules.ENABLESPAWNBUNKER)) {
                Player _player;
                Entity _ent = entity;
                _ent.teleportTo(world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.BUNKERSPAWNPOINTX), world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.SPAWNBUNKERSTARTINGHEIGHT) + 1, world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.BUNKERSPAWNPOINTZ));
                if (_ent instanceof ServerPlayer _serverPlayer) {
                    _serverPlayer.connection.teleport(world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.BUNKERSPAWNPOINTX), world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.SPAWNBUNKERSTARTINGHEIGHT) + 1, world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.BUNKERSPAWNPOINTZ), _ent.getYRot(), _ent.getXRot());
                }
                if (entity instanceof ServerPlayer _serverPlayer) {
                    _serverPlayer.setRespawnPosition(_serverPlayer.level.dimension(), new BlockPos(world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.BUNKERSPAWNPOINTX), world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.SPAWNBUNKERSTARTINGHEIGHT) + 1, world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.BUNKERSPAWNPOINTZ)), _serverPlayer.getYRot(), true, false);
                }
                if (entity instanceof Player) {
                    _player = (Player)entity;
                    if (!_player.level.isClientSide()) {
                        _player.displayClientMessage(Component.literal("Thank you for downloading Bunker Down!"), false);
                    }
                }
                if (entity instanceof Player) {
                    _player = (Player)entity;
                    if (!_player.level.isClientSide()) {
                        _player.displayClientMessage(Component.literal("Make sure to set your spawn point at one of the beds, and get ready for underground living"), false);
                    }
                }
            }
        }
        if (entity instanceof ServerPlayer _player) {
            Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("bunker_down:first_joined_world"));
            AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
            if (!_ap.isDone()) {
                Iterator _iterator = _ap.getRemainingCriteria().iterator();
                while (_iterator.hasNext()) {
                    _player.getAdvancements().award(_adv, (String)_iterator.next());
                }
            }
        }
    }
}

