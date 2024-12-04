package net.sonicrushxii.money_hearts;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.sonicrushxii.money_hearts.client_data.ClientTagHolder;

import java.util.Arrays;
import java.util.List;

public class HeartOverlay {
    private static final ResourceLocation EMPTY_HEART = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/empty.png");
    private static final ResourceLocation NORMAL_HEART_FULL = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/normal_full.png");
    private static final ResourceLocation NORMAL_HEART_HALF = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/normal_half.png");
    private static final ResourceLocation ARMOR_EMPTY = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/armor_empty.png");
    private static final ResourceLocation ARMOR_FULL = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/armor_full.png");
    private static final ResourceLocation ARMOR_HALF = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/armor_half.png");
    private static final ResourceLocation ABSORPTION_HEART_FULL = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/absorption_full.png");
    private static final ResourceLocation ABSORPTION_HEART_HALF = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/absorption_half.png");
    private static final ResourceLocation DOLLAR_1_FULL = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/1_dollar_full.png");
    private static final ResourceLocation DOLLAR_1_HALF = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/1_dollar_half.png");
    private static final ResourceLocation DOLLAR_5_FULL = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/5_dollar_full.png");
    private static final ResourceLocation DOLLAR_5_HALF = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/5_dollar_half.png");
    private static final ResourceLocation DOLLAR_25_FULL = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/25_dollar_full.png");
    private static final ResourceLocation DOLLAR_25_HALF = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/25_dollar_half.png");
    private static final ResourceLocation DOLLAR_100_FULL = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/100_dollar_full.png");
    private static final ResourceLocation DOLLAR_100_HALF = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/100_dollar_half.png");
    private static final ResourceLocation DOLLAR_1000_FULL = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/1000_dollar_full.png");
    private static final ResourceLocation DOLLAR_1000_HALF = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/1000_dollar_half.png");
    private static final ResourceLocation DOLLAR_10000_FULL = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/10000_dollar_full.png");
    private static final ResourceLocation DOLLAR_10000_HALF = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/10000_dollar_half.png");
    private static final ResourceLocation DOLLAR_50000_FULL = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/50000_dollar_full.png");
    private static final ResourceLocation DOLLAR_50000_HALF = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/50000_dollar_half.png");
    private static final ResourceLocation DOLLAR_100000_FULL = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/100000_dollar_full.png");
    private static final ResourceLocation DOLLAR_100000_HALF = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/100000_dollar_half.png");
    private static final ResourceLocation DOLLAR_500000_FULL = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/500000_dollar_full.png");
    private static final ResourceLocation DOLLAR_500000_HALF = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/500000_dollar_half.png");
    private static final ResourceLocation DOLLAR_1000000_FULL = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/1000000_dollar_full.png");
    private static final ResourceLocation DOLLAR_1000000_HALF = new ResourceLocation(MoneyHearts.MOD_ID,
            "textures/custom_hearts/1000000_dollar_half.png");


    //Register the Main Overlay
    public static final IGuiOverlay CUSTOM_OVERLAY = ((ForgeGui gui, GuiGraphics guiComponent, float partialTick, int screenWidth, int screenHeight) -> {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;

        renderHearts(player, gui, guiComponent, partialTick, screenWidth, screenHeight);
    });

    private static void renderSlot(ResourceLocation SLOT_TEXTURE, GuiGraphics guiComponent, int x, int y, int[] textureDimensions) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, SLOT_TEXTURE);
        guiComponent.blit(SLOT_TEXTURE, x, y,
                0, 0, textureDimensions[0], textureDimensions[1], textureDimensions[0], textureDimensions[1]);
    }

    public static void renderHearts(LocalPlayer player, ForgeGui gui, GuiGraphics guiComponent, float partialTick, int screenWidth, int screenHeight) {
        if (player.isCreative() || player.isSpectator())
            return;

        final int[] textureDimensions = {9, 9};

        int playerHp = (int) Math.ceil(player.getHealth());
        int maxHP = (int) Math.ceil(player.getMaxHealth());
        int absorption = (int) Math.ceil(player.getAbsorptionAmount());
        int armorLevel = player.getArmorValue();

        List<ResourceLocation> iconTextures;

        if (ClientTagHolder.getTags().contains("millionHearts")) {
            iconTextures = Arrays.asList(
                    (playerHp > 1) ? DOLLAR_1000000_FULL : (playerHp == 1) ? DOLLAR_1000000_HALF : EMPTY_HEART,
                    (playerHp > 3) ? DOLLAR_1000000_FULL : (playerHp == 3) ? DOLLAR_1000000_HALF : EMPTY_HEART,
                    (playerHp > 5) ? DOLLAR_1000000_FULL : (playerHp == 5) ? DOLLAR_1000000_HALF : EMPTY_HEART,
                    (playerHp > 7) ? DOLLAR_1000000_FULL : (playerHp == 7) ? DOLLAR_1000000_HALF : EMPTY_HEART,
                    (playerHp > 9) ? DOLLAR_1000000_FULL : (playerHp == 9) ? DOLLAR_1000000_HALF : EMPTY_HEART,
                    (playerHp > 11) ? DOLLAR_1000000_FULL : (playerHp == 11) ? DOLLAR_1000000_HALF : EMPTY_HEART,
                    (playerHp > 13) ? DOLLAR_1000000_FULL : (playerHp == 13) ? DOLLAR_1000000_HALF : EMPTY_HEART,
                    (playerHp > 15) ? DOLLAR_1000000_FULL : (playerHp == 15) ? DOLLAR_1000000_HALF : EMPTY_HEART,
                    (playerHp > 17) ? DOLLAR_1000000_FULL : (playerHp == 17) ? DOLLAR_1000000_HALF : EMPTY_HEART,
                    (playerHp > 19) ? DOLLAR_1000000_FULL : (playerHp == 19) ? DOLLAR_1000000_HALF : EMPTY_HEART
            );
        } else {
            iconTextures = Arrays.asList(
                    (playerHp > 1) ? NORMAL_HEART_FULL : (playerHp == 1) ? NORMAL_HEART_HALF : EMPTY_HEART,
                    (playerHp > 3) ? DOLLAR_1_FULL : (playerHp == 3) ? DOLLAR_1_HALF : EMPTY_HEART,
                    (playerHp > 5) ? DOLLAR_5_FULL : (playerHp == 5) ? DOLLAR_5_HALF : EMPTY_HEART,
                    (playerHp > 7) ? DOLLAR_25_FULL : (playerHp == 7) ? DOLLAR_25_HALF : EMPTY_HEART,
                    (playerHp > 9) ? DOLLAR_100_FULL : (playerHp == 9) ? DOLLAR_100_HALF : EMPTY_HEART,
                    (playerHp > 11) ? DOLLAR_1000_FULL : (playerHp == 11) ? DOLLAR_1000_HALF : EMPTY_HEART,
                    (playerHp > 13) ? DOLLAR_10000_FULL : (playerHp == 13) ? DOLLAR_10000_HALF : EMPTY_HEART,
                    (playerHp > 15) ? DOLLAR_50000_FULL : (playerHp == 15) ? DOLLAR_50000_HALF : EMPTY_HEART,
                    (playerHp > 17) ? DOLLAR_100000_FULL : (playerHp == 17) ? DOLLAR_100000_HALF : EMPTY_HEART,
                    (playerHp > 19) ? DOLLAR_500000_FULL : (playerHp == 19) ? DOLLAR_500000_HALF : EMPTY_HEART
            );
        }

        int x = screenWidth / 2 - 92; // Starting X position for hearts
        int y = screenHeight - 39; // Default Y position for hearts

        //Render Normal Hearts
        for (int i = 0; i < (maxHP / 2) + (absorption / 2); ++i) {
            //Render Custom Hearts
            if (i < 10)
                renderSlot(iconTextures.get(i), guiComponent,
                        x + ((textureDimensions[0] - 1) * i), y, textureDimensions);
            //Render Normal Extra Hearts
            else if (i < (maxHP/2))
                renderSlot((playerHp > i * 2 + 1) ? NORMAL_HEART_FULL : (playerHp == i * 2 + 1) ? NORMAL_HEART_HALF : EMPTY_HEART
                        , guiComponent,
                        x + ((textureDimensions[0] - 1) * (i % 10)),
                        y - ((textureDimensions[1]) * (i / 10)),
                        textureDimensions);
            //Render Absorption hearts
            else if(i < ( (maxHP/2) + (absorption/2) ))
                renderSlot(ABSORPTION_HEART_FULL,
                        guiComponent,
                        x + ((textureDimensions[0] - 1) * (i % 10)),
                        y - ((textureDimensions[1]) * (i / 10)),
                        textureDimensions);
        }

        int rowAmt = ((maxHP/2) + (absorption/2) - 1);
        rowAmt = (rowAmt < 0)?rowAmt:rowAmt/10;

        //Render Armor
        if(armorLevel > 0)
            for(int a = 0; a < 10; ++a)
                renderSlot((armorLevel > a * 2 + 1) ? ARMOR_FULL : (armorLevel == a * 2 + 1) ? ARMOR_HALF : ARMOR_EMPTY
                        , guiComponent,
                        x + ((textureDimensions[0] - 1) * (a)),
                        y - ((textureDimensions[1]) * (rowAmt+1)) - 1,
                        textureDimensions);
    }
}
