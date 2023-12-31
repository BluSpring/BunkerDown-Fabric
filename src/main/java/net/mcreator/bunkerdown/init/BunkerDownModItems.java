/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.BlockItem
 *  net.minecraft.world.item.CreativeModeTab
 *  net.minecraft.world.item.DoubleHighBlockItem
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.level.block.Block
 *  net.minecraftforge.registries.DeferredRegister
 *  net.minecraftforge.registries.ForgeRegistries
 *  net.minecraftforge.registries.IForgeRegistry
 *  net.minecraftforge.registries.RegistryObject
 */
package net.mcreator.bunkerdown.init;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.mcreator.bunkerdown.init.BunkerDownModBlocks;
import net.mcreator.bunkerdown.item.BrittlePickaxeItem;
import net.mcreator.bunkerdown.item.ResetTesterItemItem;
import net.mcreator.bunkerdown.item.RoastedUnderrootSeedsItem;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class BunkerDownModItems {
    public static final LazyRegistrar<Item> REGISTRY = LazyRegistrar.create(Registry.ITEM, (String)"bunker_down");
    public static final RegistryObject<Item> RESET_TESTER_ITEM = REGISTRY.register("reset_tester_item", () -> new ResetTesterItemItem());
    public static final RegistryObject<Item> AIRLOCK_IRON_DOOR = BunkerDownModItems.doubleBlock(BunkerDownModBlocks.AIRLOCK_IRON_DOOR, CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Item> AIRLOCK_IRON_TRAPDOOR = BunkerDownModItems.block(BunkerDownModBlocks.AIRLOCK_IRON_TRAPDOOR, CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Item> CEILING_LAMP = BunkerDownModItems.block(BunkerDownModBlocks.CEILING_LAMP, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Item> STEEL_VENT = BunkerDownModItems.block(BunkerDownModBlocks.STEEL_VENT, CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Item> STEEL_BLOCK = BunkerDownModItems.block(BunkerDownModBlocks.STEEL_BLOCK, CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Item> UNDERROOT = BunkerDownModItems.block(BunkerDownModBlocks.UNDERROOT, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Item> UNDERROOT_STAGE_1 = BunkerDownModItems.block(BunkerDownModBlocks.UNDERROOT_STAGE_1, CreativeModeTab.TAB_MISC);
    public static final RegistryObject<Item> UNDERROOT_STAGE_2 = BunkerDownModItems.block(BunkerDownModBlocks.UNDERROOT_STAGE_2, CreativeModeTab.TAB_DECORATIONS);
    public static final RegistryObject<Item> STEEL_PRESSURE_PLATE = BunkerDownModItems.block(BunkerDownModBlocks.STEEL_PRESSURE_PLATE, CreativeModeTab.TAB_REDSTONE);
    public static final RegistryObject<Item> STEEL_BLOCK_SLAB = BunkerDownModItems.block(BunkerDownModBlocks.STEEL_BLOCK_SLAB, CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Item> BRITTLE_PICKAXE = REGISTRY.register("brittle_pickaxe", () -> new BrittlePickaxeItem());
    public static final RegistryObject<Item> ROASTED_UNDERROOT_SEEDS = REGISTRY.register("roasted_underroot_seeds", () -> new RoastedUnderrootSeedsItem());
    public static final RegistryObject<Item> BROKEN_CEILING_LAMP = BunkerDownModItems.block(BunkerDownModBlocks.BROKEN_CEILING_LAMP, CreativeModeTab.TAB_DECORATIONS);

    private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
        return REGISTRY.register(block.getId().getPath(), () -> new BlockItem((Block)block.get(), new Item.Properties().tab(tab)));
    }

    private static RegistryObject<Item> doubleBlock(RegistryObject<Block> block, CreativeModeTab tab) {
        return REGISTRY.register(block.getId().getPath(), () -> new DoubleHighBlockItem((Block)block.get(), new Item.Properties().tab(tab)));
    }
}

