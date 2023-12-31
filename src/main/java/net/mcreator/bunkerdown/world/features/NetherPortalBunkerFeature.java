/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Holder
 *  net.minecraft.data.worldgen.features.FeatureUtils
 *  net.minecraft.data.worldgen.placement.PlacementUtils
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.Mirror
 *  net.minecraft.world.level.block.Rotation
 *  net.minecraft.world.level.levelgen.Heightmap$Types
 *  net.minecraft.world.level.levelgen.feature.ConfiguredFeature
 *  net.minecraft.world.level.levelgen.feature.Feature
 *  net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
 *  net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
 *  net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration
 *  net.minecraft.world.level.levelgen.placement.PlacedFeature
 *  net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate
 */
package net.mcreator.bunkerdown.world.features;

import java.util.List;
import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class NetherPortalBunkerFeature
extends Feature<NoneFeatureConfiguration> {
    public static NetherPortalBunkerFeature FEATURE = null;
    public static Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> CONFIGURED_FEATURE = null;
    public static Holder<PlacedFeature> PLACED_FEATURE = null;
    private final Set<ResourceKey<Level>> generate_dimensions = Set.of(Level.OVERWORLD);
    private final List<Block> base_blocks = List.of(Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.DEEPSLATE, Blocks.TUFF);
    private StructureTemplate template = null;

    public static Feature<?> feature() {
        FEATURE = new NetherPortalBunkerFeature();
        CONFIGURED_FEATURE = FeatureUtils.register((String)"bunker_down:nether_portal_bunker", (Feature)FEATURE, (FeatureConfiguration)FeatureConfiguration.NONE);
        PLACED_FEATURE = PlacementUtils.register((String)"bunker_down:nether_portal_bunker", CONFIGURED_FEATURE, List.of());
        return FEATURE;
    }

    public NetherPortalBunkerFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        if (!this.generate_dimensions.contains((Object)context.level().getLevel().dimension())) {
            return false;
        }
        if (this.template == null) {
            this.template = context.level().getLevel().getStructureManager().getOrCreate(new ResourceLocation("bunker_down", "netherportalbunker2"));
        }
        if (this.template == null) {
            return false;
        }
        boolean anyPlaced = false;
        if (context.random().nextInt(1000000) + 1 <= 4600) {
            int count = context.random().nextInt(1) + 1;
            for (int a = 0; a < count; ++a) {
                int i = context.origin().getX() + context.random().nextInt(16);
                int k = context.origin().getZ() + context.random().nextInt(16);
                int j = context.level().getHeight(Heightmap.Types.OCEAN_FLOOR_WG, i, k);
                j = Mth.nextInt((RandomSource)context.random(), (int)(8 + context.level().getMinBuildHeight()), (int)Math.max(j, 9 + context.level().getMinBuildHeight()));
                if (!this.base_blocks.contains((Object)context.level().getBlockState(new BlockPos(i, j, k)).getBlock())) continue;
                BlockPos spawnTo = new BlockPos(i + 0, j + 0, k + 0);
                if (!this.template.placeInWorld((ServerLevelAccessor)context.level(), spawnTo, spawnTo, new StructurePlaceSettings().setMirror(Mirror.values()[context.random().nextInt(2)]).setRotation(Rotation.values()[context.random().nextInt(3)]).setRandom(context.random()).addProcessor((StructureProcessor)BlockIgnoreProcessor.STRUCTURE_BLOCK).setIgnoreEntities(false), context.random(), 2)) continue;
                anyPlaced = true;
            }
        }
        return anyPlaced;
    }
}

