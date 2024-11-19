package net.sonicrushxii.money_hearts.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.sonicrushxii.money_hearts.client_data.ClientTagHolder;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public class SyncPlayerTagsPacket {
    private final Set<String> tags;

    public SyncPlayerTagsPacket(Set<String> tags) {
        this.tags = tags;
    }

    // Constructor for decoding
    public SyncPlayerTagsPacket(FriendlyByteBuf buf) {
        this.tags = new HashSet<>();
        int size = buf.readVarInt(); // Number of tags
        for (int i = 0; i < size; i++) {
            this.tags.add(buf.readUtf(32767)); // Read each tag
        }
    }

    // Encodes data to send to the client
    public void encode(FriendlyByteBuf buf) {
        buf.writeVarInt(tags.size());
        for (String tag : tags) {
            buf.writeUtf(tag);
        }
    }

    public void handle(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            ClientTagHolder.updateTags(this.tags);
        }));
        context.get().setPacketHandled(true);
    }
}
