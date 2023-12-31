/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.core.Direction
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.FriendlyByteBuf
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.saveddata.SavedData
 *  net.minecraftforge.common.capabilities.Capability
 *  net.minecraftforge.common.capabilities.CapabilityManager
 *  net.minecraftforge.common.capabilities.CapabilityToken
 *  net.minecraftforge.common.capabilities.ICapabilityProvider
 *  net.minecraftforge.common.capabilities.ICapabilitySerializable
 *  net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent
 *  net.minecraftforge.common.util.FakePlayer
 *  net.minecraftforge.common.util.LazyOptional
 *  net.minecraftforge.event.AttachCapabilitiesEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$Clone
 *  net.minecraftforge.event.entity.player.PlayerEvent$PlayerChangedDimensionEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$PlayerLoggedInEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$PlayerRespawnEvent
 *  net.minecraftforge.eventbus.api.SubscribeEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber$Bus
 *  net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
 *  net.minecraftforge.network.NetworkEvent$Context
 *  net.minecraftforge.network.PacketDistributor
 */
package net.mcreator.bunkerdown.network;

import java.util.function.Supplier;

import dev.architectury.event.events.common.PlayerEvent;
import io.github.fabricators_of_create.porting_lib.fake_players.FakePlayer;
import io.github.fabricators_of_create.porting_lib.util.LazyOptional;
import me.pepperbell.simplenetworking.C2SPacket;
import me.pepperbell.simplenetworking.S2CPacket;
import me.pepperbell.simplenetworking.SimpleChannel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.mcreator.bunkerdown.BunkerDownMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.util.thread.BlockableEventLoop;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.saveddata.SavedData;
import xyz.bluspring.bunkerdown.mixin.EntityAccessor;
import xyz.bluspring.forgecapabilities.capabilities.*;

public class BunkerDownModVariables {
    public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>(){});

    public static void init() {
        BunkerDownMod.addNetworkMessage(SavedDataSyncMessage.class, SavedDataSyncMessage::new);
        BunkerDownMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::new);

        RegisterCapabilitiesCallback.EVENT.register((capabilities) -> {
            capabilities.add(PlayerVariables.class);
        });

        new PlayerVariablesProvider();
        EventBusVariableHandlers.init();
    }

    public static class SavedDataSyncMessage implements C2SPacket, S2CPacket {
        public int type;
        public SavedData data;

        public SavedDataSyncMessage(FriendlyByteBuf buffer) {
            this.type = buffer.readInt();
            SavedData savedData = this.data = this.type == 0 ? new MapVariables() : new WorldVariables();
            if (savedData instanceof MapVariables _mapvars) {
                _mapvars.read(buffer.readNbt());
            } else {
                savedData = this.data;
                if (savedData instanceof WorldVariables _worldvars) {
                    _worldvars.read(buffer.readNbt());
                }
            }
        }

        public SavedDataSyncMessage(int type, SavedData data) {
            this.type = type;
            this.data = data;
        }

        public static void buffer(SavedDataSyncMessage message, FriendlyByteBuf buffer) {
            buffer.writeInt(message.type);
            buffer.writeNbt(message.data.save(new CompoundTag()));
        }

        public static void handler(SavedDataSyncMessage message, BlockableEventLoop<?> loop) {
            loop.execute(() -> {
                if (loop instanceof MinecraftServer) {
                    if (message.type == 0) {
                        MapVariables.clientSide = (MapVariables)message.data;
                    } else {
                        WorldVariables.clientSide = (WorldVariables)message.data;
                    }
                }
            });
        }

        @Override
        public void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl listener, PacketSender responseSender, SimpleChannel channel) {
            handler(this, server);
        }

        @Environment(EnvType.CLIENT)
        @Override
        public void handle(Minecraft client, ClientPacketListener listener, PacketSender responseSender, SimpleChannel channel) {
            handler(this, client);
        }

        @Override
        public void encode(FriendlyByteBuf buf) {
            buffer(this, buf);
        }
    }

    public static class PlayerVariablesSyncMessage implements S2CPacket, C2SPacket {
        public PlayerVariables data;

        public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
            this.data = new PlayerVariables();
            this.data.readNBT(buffer.readNbt());
        }

        public PlayerVariablesSyncMessage(PlayerVariables data) {
            this.data = data;
        }

        public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
            buffer.writeNbt((CompoundTag)message.data.writeNBT());
        }

        public static void handler(PlayerVariablesSyncMessage message, BlockableEventLoop<?> loop) {
            loop.execute(() -> {
                if (loop instanceof MinecraftServer) {
                    PlayerVariables variables = Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables());
                    variables.FirstTimeJoined = message.data.FirstTimeJoined;
                }
            });
        }

        @Override
        public void handle(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl listener, PacketSender responseSender, SimpleChannel channel) {
            handler(this, server);
        }

        @Environment(EnvType.CLIENT)
        @Override
        public void handle(Minecraft client, ClientPacketListener listener, PacketSender responseSender, SimpleChannel channel) {
            handler(this, client);
        }

        @Override
        public void encode(FriendlyByteBuf buf) {
            buffer(this, buf);
        }
    }

    public static class PlayerVariables {
        public boolean FirstTimeJoined = false;

        public void syncPlayerVariables(Entity entity) {
            if (entity instanceof ServerPlayer serverPlayer) {
                BunkerDownMod.PACKET_HANDLER.sendToClient(new PlayerVariablesSyncMessage(this), serverPlayer);
            }
        }

        public Tag writeNBT() {
            CompoundTag nbt = new CompoundTag();
            nbt.putBoolean("FirstTimeJoined", this.FirstTimeJoined);
            return nbt;
        }

        public void readNBT(Tag Tag2) {
            CompoundTag nbt = (CompoundTag)Tag2;
            this.FirstTimeJoined = nbt.getBoolean("FirstTimeJoined");
        }
    }

    private static class PlayerVariablesProvider
    implements ICapabilitySerializable<Tag> {
        private final PlayerVariables playerVariables = new PlayerVariables();
        private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> this.playerVariables);

        private PlayerVariablesProvider() {
            AttachCapabilitiesCallback.EVENT.register(((obj, capabilities, listeners) -> {
                if (obj instanceof Player && !(obj instanceof FakePlayer)) {
                    capabilities.put(new ResourceLocation("bunker_down", "player_variables"), new PlayerVariablesProvider());
                }
            }));
        }

        public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
            return cap == PLAYER_VARIABLES_CAPABILITY ? this.instance.cast() : LazyOptional.empty();
        }

        public Tag serializeNBT() {
            return this.playerVariables.writeNBT();
        }

        public void deserializeNBT(Tag nbt) {
            this.playerVariables.readNBT(nbt);
        }
    }

    public static class MapVariables
    extends SavedData {
        public static final String DATA_NAME = "bunker_down_mapvars";
        static MapVariables clientSide = new MapVariables();

        public static MapVariables load(CompoundTag tag) {
            MapVariables data = new MapVariables();
            data.read(tag);
            return data;
        }

        public void read(CompoundTag nbt) {
        }

        public CompoundTag save(CompoundTag nbt) {
            return nbt;
        }

        public void syncData(LevelAccessor world) {
            this.setDirty();
            if (world instanceof Level && !world.isClientSide()) {
                BunkerDownMod.PACKET_HANDLER.sendToServer(new SavedDataSyncMessage(0, this));
            }
        }

        public static MapVariables get(LevelAccessor world) {
            if (world instanceof ServerLevelAccessor serverLevelAcc) {
                return serverLevelAcc.getLevel().getServer().getLevel(Level.OVERWORLD).getDataStorage().computeIfAbsent(e -> MapVariables.load(e), MapVariables::new, DATA_NAME);
            }
            return clientSide;
        }
    }

    public static class WorldVariables
    extends SavedData {
        public static final String DATA_NAME = "bunker_down_worldvars";
        public boolean WorldFirstStart = false;
        static WorldVariables clientSide = new WorldVariables();

        public static WorldVariables load(CompoundTag tag) {
            WorldVariables data = new WorldVariables();
            data.read(tag);
            return data;
        }

        public void read(CompoundTag nbt) {
            this.WorldFirstStart = nbt.getBoolean("WorldFirstStart");
        }

        public CompoundTag save(CompoundTag nbt) {
            nbt.putBoolean("WorldFirstStart", this.WorldFirstStart);
            return nbt;
        }

        public void syncData(LevelAccessor world) {
            Level level;
            this.setDirty();
            if (world instanceof Level && !(level = (Level)world).isClientSide()) {
                BunkerDownMod.PACKET_HANDLER.sendToClientsInWorld(new SavedDataSyncMessage(1, this), (ServerLevel) level);
            }
        }

        public static WorldVariables get(LevelAccessor world) {
            if (world instanceof ServerLevel level) {
                return level.getDataStorage().computeIfAbsent(e -> WorldVariables.load(e), WorldVariables::new, DATA_NAME);
            }
            return clientSide;
        }
    }

    public static class EventBusVariableHandlers {
        public static void init() {
            ServerPlayConnectionEvents.JOIN.register(((handler, sender, server) -> {
                server.execute(() -> {
                    onPlayerLoggedInSyncPlayerVariables(handler, sender);
                    onPlayerLoggedIn(handler, sender, server);
                });
            }));

            ServerPlayerEvents.AFTER_RESPAWN.register(((oldPlayer, newPlayer, alive) -> {
                onPlayerRespawnedSyncPlayerVariables(newPlayer);
            }));

            PlayerEvent.CHANGE_DIMENSION.register(((player, oldLevel, newLevel) -> {
                onPlayerChangedDimensionSyncPlayerVariables(player);
                onPlayerChangedDimension(player);
            }));

            PlayerEvent.PLAYER_CLONE.register(((oldPlayer, newPlayer, wonGame) -> {
                clonePlayer(oldPlayer, newPlayer);
            }));
        }

        public static void onPlayerLoggedInSyncPlayerVariables(ServerGamePacketListenerImpl handler, PacketSender sender) {
            var entity = handler.getPlayer();

            if (!entity.level.isClientSide()) {
                (entity.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(entity);
            }
        }

        public static void onPlayerRespawnedSyncPlayerVariables(Player player) {
            if (!player.level.isClientSide()) {
                player.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()).syncPlayerVariables(player);
            }
        }

        public static void onPlayerChangedDimensionSyncPlayerVariables(Player player) {
            if (!player.level.isClientSide()) {
                player.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()).syncPlayerVariables(player);
            }
        }

        public static void clonePlayer(Player originalPlayer, Player newPlayer) {
            ((EntityAccessor) originalPlayer).callUnsetRemoved();
            originalPlayer.reviveCaps();
            PlayerVariables original = originalPlayer.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables());
            PlayerVariables clone = newPlayer.getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables());
            clone.FirstTimeJoined = original.FirstTimeJoined;
        }

        public static void onPlayerLoggedIn(ServerGamePacketListenerImpl handler, PacketSender sender, MinecraftServer server) {
            var entity = handler.getPlayer();
            if (!entity.level.isClientSide()) {
                MapVariables mapdata = MapVariables.get(entity.level);
                WorldVariables worlddata = WorldVariables.get(entity.level);
                if (mapdata != null) {
                    BunkerDownMod.PACKET_HANDLER.sendToClient(new SavedDataSyncMessage(0, mapdata), entity);
                }
                if (worlddata != null) {
                    BunkerDownMod.PACKET_HANDLER.sendToClient(new SavedDataSyncMessage(1, worlddata), entity);
                }
            }
        }

        public static void onPlayerChangedDimension(Player player) {
            WorldVariables worlddata;
            if (!player.level.isClientSide() && (worlddata = WorldVariables.get(player.level)) != null) {
                BunkerDownMod.PACKET_HANDLER.sendToClient(new SavedDataSyncMessage(1, worlddata), (ServerPlayer) player);
            }
        }
    }
}

