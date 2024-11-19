package net.sonicrushxii.money_hearts;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MoneyHearts.MOD_ID, value = Dist.CLIENT)
public class HeartRenderHandler {

    @SubscribeEvent
    public static void onRenderGui(RenderGuiOverlayEvent.Pre event) {
        if (event.getOverlay().id().toString().equals("minecraft:player_health")) { // Gui.HEARTS_LAYER is the overlay layer for hearts.
            event.setCanceled(true); // Cancels rendering of hearts.
        }
    }
}
