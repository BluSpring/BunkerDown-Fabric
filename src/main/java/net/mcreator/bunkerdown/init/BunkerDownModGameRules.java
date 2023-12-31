/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.GameRules$BooleanValue
 *  net.minecraft.world.level.GameRules$Category
 *  net.minecraft.world.level.GameRules$IntegerValue
 *  net.minecraft.world.level.GameRules$Key
 *  net.minecraft.world.level.GameRules$Type
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 */
package net.mcreator.bunkerdown.init;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.level.GameRules;

public class BunkerDownModGameRules {
    public static void init() {}
    
    public static final GameRules.Key<GameRules.BooleanValue> ENABLESPAWNBUNKER = GameRuleRegistry.register("enableSpawnBunker", GameRules.Category.MISC, GameRules.BooleanValue.create(true));
    public static final GameRules.Key<GameRules.IntegerValue> SPAWNBUNKERSTARTINGHEIGHT = GameRuleRegistry.register("spawnBunkerStartingHeight", GameRules.Category.MISC, GameRules.IntegerValue.create(32));
    public static final GameRules.Key<GameRules.BooleanValue> ENABLESURFACEHATCH = GameRuleRegistry.register("enableSurfaceHatch", GameRules.Category.MISC, GameRules.BooleanValue.create(true));
    public static final GameRules.Key<GameRules.IntegerValue> UNDERROOTGROWABLEHEIGHT = GameRuleRegistry.register("underrootGrowableHeight", GameRules.Category.MISC, GameRules.IntegerValue.create(70));
    public static final GameRules.Key<GameRules.IntegerValue> BUNKERSPAWNPOINTX = GameRuleRegistry.register("bunkerSpawnPointX", GameRules.Category.MISC, GameRules.IntegerValue.create(0));
    public static final GameRules.Key<GameRules.IntegerValue> BUNKERSPAWNPOINTZ = GameRuleRegistry.register("bunkerSpawnPointZ", GameRules.Category.MISC, GameRules.IntegerValue.create(0));
}

