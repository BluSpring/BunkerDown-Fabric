/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.levelgen.feature.Feature
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package net.mcreator.bunkerdown.init;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.mcreator.bunkerdown.world.features.AnimalBunkerFeature;
import net.mcreator.bunkerdown.world.features.FarmBunkerFeature;
import net.mcreator.bunkerdown.world.features.NetherPortalBunkerFeature;
import net.mcreator.bunkerdown.world.features.RedstoneBunkerFeature;
import net.mcreator.bunkerdown.world.features.plants.UnderrootFeature;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;

public class BunkerDownModFeatures {
    public static final LazyRegistrar<Feature<?>> REGISTRY = LazyRegistrar.create(Registry.FEATURE, (String)"bunker_down");
    public static final RegistryObject<Feature<?>> UNDERROOT = REGISTRY.register("underroot", UnderrootFeature::feature);
    public static final RegistryObject<Feature<?>> FARM_BUNKER = REGISTRY.register("farm_bunker", FarmBunkerFeature::feature);
    public static final RegistryObject<Feature<?>> REDSTONE_BUNKER = REGISTRY.register("redstone_bunker", RedstoneBunkerFeature::feature);
    public static final RegistryObject<Feature<?>> ANIMAL_BUNKER = REGISTRY.register("animal_bunker", AnimalBunkerFeature::feature);
    public static final RegistryObject<Feature<?>> NETHER_PORTAL_BUNKER = REGISTRY.register("nether_portal_bunker", NetherPortalBunkerFeature::feature);
}

