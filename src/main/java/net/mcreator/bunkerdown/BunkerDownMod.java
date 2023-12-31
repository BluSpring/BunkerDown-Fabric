/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.TickEvent$Phase
 *  net.minecraftforge.event.TickEvent$ServerTickEvent
 *  net.minecraftforge.eventbus.api.IEventBus
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
 *  net.minecraftforge.network.NetworkEvent$Context
 *  net.minecraftforge.network.NetworkRegistry
 *  net.minecraftforge.network.simple.SimpleChannel
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package net.mcreator.bunkerdown;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import me.pepperbell.simplenetworking.C2SPacket;
import me.pepperbell.simplenetworking.S2CPacket;
import me.pepperbell.simplenetworking.SimpleChannel;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.mcreator.bunkerdown.init.BunkerDownModBlockEntities;
import net.mcreator.bunkerdown.init.BunkerDownModBlocks;
import net.mcreator.bunkerdown.init.BunkerDownModFeatures;
import net.mcreator.bunkerdown.init.BunkerDownModItems;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BunkerDownMod {
    public static final Logger LOGGER = LogManager.getLogger(BunkerDownMod.class);
    public static final String MODID = "bunker_down";
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = new SimpleChannel((ResourceLocation)new ResourceLocation("bunker_down", "bunker_down"));
    private static int messageID = 0;
    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<AbstractMap.SimpleEntry<Runnable, Integer>>();

    public BunkerDownMod() {
        BunkerDownModBlocks.REGISTRY.register();
        BunkerDownModItems.REGISTRY.register();
        BunkerDownModBlockEntities.REGISTRY.register();
        BunkerDownModFeatures.REGISTRY.register();

        ServerTickEvents.END_SERVER_TICK.register(this::tick);
    }

    public static <T extends C2SPacket & S2CPacket> void addNetworkMessage(Class<T> messageType, Function<FriendlyByteBuf, T> decoder) {
        PACKET_HANDLER.registerC2SPacket(messageType, messageID, decoder);
        PACKET_HANDLER.registerS2CPacket(messageType, messageID, decoder);
        messageID += 2;
    }

    public static void queueServerWork(int tick, Runnable action) {
        workQueue.add(new AbstractMap.SimpleEntry<Runnable, Integer>(action, tick));
    }

    public void tick(MinecraftServer server) {
        ArrayList<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
        workQueue.forEach(work -> {
            work.setValue(work.getValue() - 1);
            if (work.getValue() == 0) {
                actions.add(work);
            }
        });
        actions.forEach(e -> e.getKey().run());
        workQueue.removeAll(actions);
    }
}

