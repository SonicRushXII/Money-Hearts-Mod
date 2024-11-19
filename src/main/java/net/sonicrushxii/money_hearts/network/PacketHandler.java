package net.sonicrushxii.money_hearts.network;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.sonicrushxii.money_hearts.MoneyHearts;

public class PacketHandler {
    static int id = 0;

    private static final String PROTOCOL_VERSION = "1";
    private static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MoneyHearts.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void register() {
        INSTANCE.messageBuilder(SyncPlayerTagsPacket.class, id++).encoder(SyncPlayerTagsPacket::encode).decoder(SyncPlayerTagsPacket::new).consumerMainThread(SyncPlayerTagsPacket::handle).add();
    }

    public static void sendToServer(Object msg) {
        INSTANCE.send(PacketDistributor.SERVER.noArg(), msg);
    }

    public static void sendToPlayer(ServerPlayer player, Object msg) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), msg);
    }

    public static void sendToChunkPlayers(LevelChunk levelChunk, Object msg) {
        INSTANCE.send(PacketDistributor.TRACKING_CHUNK.with(() -> levelChunk), msg);
    }

    public static void sendToALLPlayers(Object msg) {
        INSTANCE.send(PacketDistributor.ALL.noArg(), msg);
    }
}
