/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.Mirror
 *  net.minecraft.world.level.block.Rotation
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate
 *  net.minecraftforge.event.level.LevelEvent$Load
 *  net.minecraftforge.eventbus.api.Event
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 */
package net.mcreator.bunkerdown.procedures;

import javax.annotation.Nullable;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.mcreator.bunkerdown.init.BunkerDownModGameRules;
import net.mcreator.bunkerdown.network.BunkerDownModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class StartWorldProcedure {
    public static void init() {
        ServerWorldEvents.LOAD.register(((server, world) -> {
            onWorldLoad(world);
        }));
    }

    public static void onWorldLoad(Level level) {
        StartWorldProcedure.execute(level);
    }

    private static void execute(LevelAccessor world) {
        boolean atsurfacehatch = false;
        double bunkerxcenter = 0.0;
        double bunkerzcenter = 0.0;
        double hatchypos = 0.0;
        if (!BunkerDownModVariables.WorldVariables.get(world).WorldFirstStart && world.getLevelData().getGameRules().getBoolean(BunkerDownModGameRules.ENABLESPAWNBUNKER)) {
            ResourceKey resourceKey;
            if (world instanceof Level _lvl) {
                resourceKey = _lvl.dimension();
            } else {
                resourceKey = Level.OVERWORLD;
            }
            if (resourceKey == Level.OVERWORLD) {
                ServerLevel _serverworld;
                StructureTemplate template;
                BunkerDownModVariables.WorldVariables.get(world).WorldFirstStart = true;
                BunkerDownModVariables.WorldVariables.get(world).syncData(world);
                bunkerxcenter = world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.BUNKERSPAWNPOINTX) - 5;
                bunkerzcenter = world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.BUNKERSPAWNPOINTZ) - 5;
                if (world instanceof ServerLevel && (template = (_serverworld = (ServerLevel)world).getStructureManager().getOrCreate(new ResourceLocation("bunker_down", "spawnbunkroom3-bunk8"))) != null) {
                    template.placeInWorld(_serverworld, new BlockPos(bunkerxcenter, world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.SPAWNBUNKERSTARTINGHEIGHT), bunkerzcenter), new BlockPos(bunkerxcenter, world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.SPAWNBUNKERSTARTINGHEIGHT), bunkerzcenter), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false), _serverworld.random, 3);
                }
                if (world instanceof ServerLevel && (template = (_serverworld = (ServerLevel)world).getStructureManager().getOrCreate(new ResourceLocation("bunker_down", "bunkerconnection"))) != null) {
                    template.placeInWorld(_serverworld, new BlockPos(bunkerxcenter + 4.0, world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.SPAWNBUNKERSTARTINGHEIGHT), bunkerzcenter + 11.0), new BlockPos(bunkerxcenter + 4.0, world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.SPAWNBUNKERSTARTINGHEIGHT), bunkerzcenter + 11.0), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false), _serverworld.random, 3);
                }
                if (Math.random() < 0.5) {
                    if (world instanceof ServerLevel && (template = (_serverworld = (ServerLevel)world).getStructureManager().getOrCreate(new ResourceLocation("bunker_down", "bunker3-hub1"))) != null) {
                        template.placeInWorld(_serverworld, new BlockPos(bunkerxcenter, world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.SPAWNBUNKERSTARTINGHEIGHT), bunkerzcenter + 12.0), new BlockPos(bunkerxcenter, world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.SPAWNBUNKERSTARTINGHEIGHT), bunkerzcenter + 12.0), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false), _serverworld.random, 3);
                    }
                } else if (world instanceof ServerLevel && (template = (_serverworld = (ServerLevel)world).getStructureManager().getOrCreate(new ResourceLocation("bunker_down", "bunker3-hub2"))) != null) {
                    template.placeInWorld(_serverworld, new BlockPos(bunkerxcenter, world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.SPAWNBUNKERSTARTINGHEIGHT), bunkerzcenter + 12.0), new BlockPos(bunkerxcenter, world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.SPAWNBUNKERSTARTINGHEIGHT), bunkerzcenter + 12.0), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false), _serverworld.random, 3);
                }
                if (world instanceof ServerLevel && (template = (_serverworld = (ServerLevel)world).getStructureManager().getOrCreate(new ResourceLocation("bunker_down", "bunkerconnection"))) != null) {
                    template.placeInWorld(_serverworld, new BlockPos(bunkerxcenter + 4.0, world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.SPAWNBUNKERSTARTINGHEIGHT), bunkerzcenter + 23.0), new BlockPos(bunkerxcenter + 4.0, world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.SPAWNBUNKERSTARTINGHEIGHT), bunkerzcenter + 23.0), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false), _serverworld.random, 3);
                }
                if (world.getLevelData().getGameRules().getBoolean(BunkerDownModGameRules.ENABLESURFACEHATCH)) {
                    if (world instanceof ServerLevel && (template = (_serverworld = (ServerLevel)world).getStructureManager().getOrCreate(new ResourceLocation("bunker_down", "hatchbase"))) != null) {
                        template.placeInWorld(_serverworld, new BlockPos(bunkerxcenter + 3.0, world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.SPAWNBUNKERSTARTINGHEIGHT), bunkerzcenter + 24.0), new BlockPos(bunkerxcenter + 3.0, world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.SPAWNBUNKERSTARTINGHEIGHT), bunkerzcenter + 24.0), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false), _serverworld.random, 3);
                    }
                    hatchypos = world.getLevelData().getGameRules().getInt(BunkerDownModGameRules.SPAWNBUNKERSTARTINGHEIGHT) + 4;
                    atsurfacehatch = false;
                    while (!atsurfacehatch) {
                        if (world.isEmptyBlock(new BlockPos(bunkerxcenter + 3.0, hatchypos, bunkerzcenter + 18.0)) && world.canSeeSkyFromBelowWater(new BlockPos(bunkerxcenter + 5.0, hatchypos, bunkerzcenter + 18.0)) || world.getBlockState(new BlockPos(bunkerxcenter + 3.0, hatchypos, bunkerzcenter + 18.0)).is(TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("forge:bunker_resurface")))) {
                            if (world instanceof ServerLevel && (template = (_serverworld = (ServerLevel)world).getStructureManager().getOrCreate(new ResourceLocation("bunker_down", "hatchtop2"))) != null) {
                                template.placeInWorld(_serverworld, new BlockPos(bunkerxcenter + 3.0, hatchypos, bunkerzcenter + 24.0), new BlockPos(bunkerxcenter + 3.0, hatchypos, bunkerzcenter + 24.0), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false), _serverworld.random, 3);
                            }
                            atsurfacehatch = true;
                            continue;
                        }
                        if (world instanceof ServerLevel && (template = (_serverworld = (ServerLevel)world).getStructureManager().getOrCreate(new ResourceLocation("bunker_down", "hatchmiddle_solidprep"))) != null) {
                            template.placeInWorld(_serverworld, new BlockPos(bunkerxcenter + 3.0, hatchypos, bunkerzcenter + 24.0), new BlockPos(bunkerxcenter + 3.0, hatchypos, bunkerzcenter + 24.0), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false), _serverworld.random, 3);
                        }
                        if (world instanceof ServerLevel && (template = (_serverworld = (ServerLevel)world).getStructureManager().getOrCreate(new ResourceLocation("bunker_down", "hatchmiddlesection"))) != null) {
                            template.placeInWorld(_serverworld, new BlockPos(bunkerxcenter + 3.0, hatchypos, bunkerzcenter + 24.0), new BlockPos(bunkerxcenter + 3.0, hatchypos, bunkerzcenter + 24.0), new StructurePlaceSettings().setRotation(Rotation.NONE).setMirror(Mirror.NONE).setIgnoreEntities(false), _serverworld.random, 3);
                        }
                        hatchypos += 1.0;
                    }
                }
            }
        }
    }
}

