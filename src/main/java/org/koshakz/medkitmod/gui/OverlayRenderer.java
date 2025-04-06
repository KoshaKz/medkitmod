package org.koshakz.medkitmod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = "medkitmod", value = Dist.CLIENT)
public class OverlayRenderer {

    private static final ResourceLocation IMAGE = new ResourceLocation("medkitmod", "textures/gui/test.png");

    private static final int x = 10;
    private static final int y = 10;
    public static boolean showButton;


    private static class ButtonScreen extends Screen {
        private static final int width = 64;
        private static final int height = 64;

        protected ButtonScreen() {
            super(Component.literal("Button GUI"));
        }

        @Override
        public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
            super.render(guiGraphics, mouseX, mouseY, partialTicks);
            guiGraphics.blit(IMAGE, x, y, 0, 0, width, height, width, height);
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
                if (mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height) {
                    showButton = false;
                    Minecraft.getInstance().setScreen(null); // закрыть экран и вернуть управление игроку
                    if (Minecraft.getInstance().player != null) {
                        Minecraft.getInstance().player.sendSystemMessage(Component.literal("Ты кликнул по картинке! 🎯"));
                    }
                    return true;
                }
            }
            return super.mouseClicked(mouseX, mouseY, button);
        }

        @Override
        public boolean isPauseScreen() {
            return false;
        }
    }

    @SubscribeEvent
    public static void onPlayerJoin(ClientPlayerNetworkEvent.LoggingIn event) {
        showButton = true;
        Minecraft.getInstance().tell(() -> Minecraft.getInstance().setScreen(new ButtonScreen()));
    }

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        // intentionally left empty to avoid drawing manually, screen handles drawing
    }

    @SubscribeEvent
    public static void onMouseClick(InputEvent.MouseButton event) {
        // input handled via screen
    }
}
