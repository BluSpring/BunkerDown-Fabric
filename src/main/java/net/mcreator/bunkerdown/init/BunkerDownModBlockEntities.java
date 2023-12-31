/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.entity.BlockEntityType$BlockEntitySupplier
 *  net.minecraft.world.level.block.entity.BlockEntityType$Builder
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package net.mcreator.bunkerdown.init;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.mcreator.bunkerdown.block.entity.UnderrootStage1BlockEntity;
import net.mcreator.bunkerdown.init.BunkerDownModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BunkerDownModBlockEntities {
    public static final LazyRegistrar<BlockEntityType<?>> REGISTRY = LazyRegistrar.create(Registry.BLOCK_ENTITY_TYPE, (String)"bunker_down");
    public static final RegistryObject<BlockEntityType<?>> UNDERROOT_STAGE_1 = BunkerDownModBlockEntities.register("underroot_stage_1", BunkerDownModBlocks.UNDERROOT_STAGE_1, UnderrootStage1BlockEntity::new);

    private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
        return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of((BlockEntityType.BlockEntitySupplier)supplier, (Block[])new Block[]{(Block)block.get()}).build(null));
    }
}

