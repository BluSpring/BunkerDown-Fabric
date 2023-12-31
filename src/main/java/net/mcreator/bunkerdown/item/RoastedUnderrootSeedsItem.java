/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.food.FoodProperties$Builder
 *  net.minecraft.world.item.CreativeModeTab
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Item$Properties
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Rarity
 *  net.minecraft.world.level.block.state.BlockState
 */
package net.mcreator.bunkerdown.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.state.BlockState;

public class RoastedUnderrootSeedsItem
extends Item {
    public RoastedUnderrootSeedsItem() {
        super(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).stacksTo(64).rarity(Rarity.COMMON).food(new FoodProperties.Builder().nutrition(4).saturationMod(0.2f).build()));
    }

    public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
        return 0.0f;
    }
}

