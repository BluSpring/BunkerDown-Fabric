/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.Block
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package net.mcreator.bunkerdown.init;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.mcreator.bunkerdown.block.AirlockIronDoorBlock;
import net.mcreator.bunkerdown.block.AirlockIronTrapdoorBlock;
import net.mcreator.bunkerdown.block.BrokenCeilingLampBlock;
import net.mcreator.bunkerdown.block.CeilingLampBlock;
import net.mcreator.bunkerdown.block.SteelBlockBlock;
import net.mcreator.bunkerdown.block.SteelBlockSlabBlock;
import net.mcreator.bunkerdown.block.SteelPressurePlateBlock;
import net.mcreator.bunkerdown.block.SteelVentBlock;
import net.mcreator.bunkerdown.block.UnderrootBlock;
import net.mcreator.bunkerdown.block.UnderrootStage1Block;
import net.mcreator.bunkerdown.block.UnderrootStage2Block;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;

public class BunkerDownModBlocks {
    public static final LazyRegistrar<Block> REGISTRY = LazyRegistrar.create(Registry.BLOCK, (String)"bunker_down");
    public static final RegistryObject<Block> AIRLOCK_IRON_DOOR = REGISTRY.register("airlock_iron_door", () -> new AirlockIronDoorBlock());
    public static final RegistryObject<Block> AIRLOCK_IRON_TRAPDOOR = REGISTRY.register("airlock_iron_trapdoor", () -> new AirlockIronTrapdoorBlock());
    public static final RegistryObject<Block> CEILING_LAMP = REGISTRY.register("ceiling_lamp", () -> new CeilingLampBlock());
    public static final RegistryObject<Block> STEEL_VENT = REGISTRY.register("steel_vent", () -> new SteelVentBlock());
    public static final RegistryObject<Block> STEEL_BLOCK = REGISTRY.register("steel_block", () -> new SteelBlockBlock());
    public static final RegistryObject<Block> UNDERROOT = REGISTRY.register("underroot", () -> new UnderrootBlock());
    public static final RegistryObject<Block> UNDERROOT_STAGE_1 = REGISTRY.register("underroot_stage_1", () -> new UnderrootStage1Block());
    public static final RegistryObject<Block> UNDERROOT_STAGE_2 = REGISTRY.register("underroot_stage_2", () -> new UnderrootStage2Block());
    public static final RegistryObject<Block> STEEL_PRESSURE_PLATE = REGISTRY.register("steel_pressure_plate", () -> new SteelPressurePlateBlock());
    public static final RegistryObject<Block> STEEL_BLOCK_SLAB = REGISTRY.register("steel_block_slab", () -> new SteelBlockSlabBlock());
    public static final RegistryObject<Block> BROKEN_CEILING_LAMP = REGISTRY.register("broken_ceiling_lamp", () -> new BrokenCeilingLampBlock());
}

