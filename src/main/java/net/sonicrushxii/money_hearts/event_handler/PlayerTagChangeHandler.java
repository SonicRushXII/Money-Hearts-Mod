package net.sonicrushxii.money_hearts.event_handler;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.server.level.ServerPlayer;
import net.sonicrushxii.money_hearts.MoneyHearts;
import net.sonicrushxii.money_hearts.network.PacketHandler;
import net.sonicrushxii.money_hearts.network.SyncPlayerTagsPacket;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = MoneyHearts.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerTagChangeHandler {

    // A map to store previous tags for each player
    private static final HashMap<ServerPlayer, Set<String>> previousTags = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        // Only run on server and for server players
        if (event.phase == TickEvent.Phase.END && event.player instanceof ServerPlayer player)
        {
            // Get the current tags
            Set<String> currentTags = player.getTags();

            // Retrieve or initialize previous tags
            Set<String> lastTags = previousTags.getOrDefault(player, new HashSet<>());

            // Detect changes
            if (!currentTags.equals(lastTags)) {
                // Handle tag changes
                onTagsChanged(player, currentTags);
                // Update the stored tags
                previousTags.put(player, new HashSet<>(currentTags));
            }
        }
    }

    private static void onTagsChanged(ServerPlayer player, Set<String> newTags) {
        PacketHandler.sendToPlayer(player,new SyncPlayerTagsPacket(newTags));
    }
}

