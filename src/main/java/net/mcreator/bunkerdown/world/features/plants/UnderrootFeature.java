/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Holder
 *  net.minecraft.data.worldgen.features.FeatureUtils
 *  net.minecraft.data.worldgen.placement.PlacementUtils
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.WorldGenLevel
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.levelgen.feature.ConfiguredFeature
 *  net.minecraft.world.level.levelgen.feature.Feature
 *  net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
 *  net.minecraft.world.level.levelgen.feature.RandomPatchFeature
 *  net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
 *  net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration
 *  net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration
 *  net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
 *  net.minecraft.world.level.levelgen.placement.BiomeFilter
 *  net.minecraft.world.level.levelgen.placement.CountPlacement
 *  net.minecraft.world.level.levelgen.placement.InSquarePlacement
 *  net.minecraft.world.level.levelgen.placement.PlacedFeature
 *  net.minecraft.world.level.levelgen.placement.RarityFilter
 */
package net.mcreator.bunkerdown.world.features.plants;

import java.util.List;
import java.util.Set;
import net.mcreator.bunkerdown.init.BunkerDownModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class UnderrootFeature
extends RandomPatchFeature {
    public static UnderrootFeature FEATURE = null;
    public static Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> CONFIGURED_FEATURE = null;
    public static Holder<PlacedFeature> PLACED_FEATURE = null;
    private final Set<ResourceKey<Level>> generate_dimensions = Set.of(Level.OVERWORLD);

    public static Feature<?> feature() {
        FEATURE = new UnderrootFeature();
        CONFIGURED_FEATURE = FeatureUtils.register((String)"bunker_down:underroot", (Feature)FEATURE, (FeatureConfiguration)FeatureUtils.simplePatchConfiguration((Feature)Feature.SIMPLE_BLOCK, (FeatureConfiguration)new SimpleBlockConfiguration((BlockStateProvider)BlockStateProvider.simple((Block)((Block)BunkerDownModBlocks.UNDERROOT.get()))), List.of(), (int)32));
        PLACED_FEATURE = PlacementUtils.register((String)"bunker_down:underroot", CONFIGURED_FEATURE, List.of(CountPlacement.of((int)2), RarityFilter.onAverageOnceEvery((int)32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        return FEATURE;
    }

    public UnderrootFeature() {
        super(RandomPatchConfiguration.CODEC);
    }

    public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context) {
        WorldGenLevel world = context.level();
        if (!this.generate_dimensions.contains((Object)world.getLevel().dimension())) {
            return false;
        }
        return super.place(context);
    }
}

