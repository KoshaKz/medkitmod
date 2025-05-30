package org.koshakz.warhelper.gui.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.WarHelper;

public class UILabel extends UIWidget {
    private Component text;
    private int color;
    private Font font;
    private final boolean isCentre;

    private float scaleX;
    public float scaleY;

    public UILabel(UIWidget parent, int x, int y, float scale, Component text, int color, boolean isCentre) {
        super(x, y, 0, 0);
        percentX = x / (float) parent.width;
        percentY = y / (float) parent.height;
        Screen screen = Minecraft.getInstance().screen;
        this.text = text;
        this.color = color;
        this.font = Minecraft.getInstance().font;
        this.scaleX = scale * (screen.width / 1920f);
        this.scaleY = scale * (screen.height / 1080f);
        this.isCentre = isCentre;
        this.width = (int) (font.width(text) * scaleX);
        this.height = (int) (font.lineHeight * scaleY);
        //WarHelper.LOGGER.warn((screen.width / 1920f) + " " + (screen.height / 1080f));
    }

    public UILabel(float percentX, float percentY, float scaleX, float scaleY, Component text, int color, boolean isCentre) {
        super(percentX, percentY, 0, 0);
        Screen screen = Minecraft.getInstance().screen;
        this.text = text;
        this.color = color;
        this.font = Minecraft.getInstance().font;
        this.scaleX = scaleX * (screen.width / 1920f);
        this.scaleY = scaleY * (screen.height / 1080f);
        this.isCentre = isCentre;
        this.width = (int) (font.width(text) * scaleX);
        this.height = (int) (font.lineHeight * scaleY);

    }

    public UILabel(UIWidget parent, float percentX, float percentY, float scaleX, float scaleY, Component text, int color, boolean isCentre) {
        super(parent, percentX, percentY, 0, 0);
        Screen screen = Minecraft.getInstance().screen;
        this.text = text;
        this.color = color;
        this.font = Minecraft.getInstance().font;
        this.scaleX = scaleX * (screen.width / 1920f);
        this.scaleY = scaleY * (screen.height / 1080f);
        this.isCentre = isCentre;
        this.width = (int) (font.width(text) * scaleX);
        this.height = (int) (font.lineHeight * scaleY);
        //WarHelper.LOGGER.warn((screen.width / 1920f) + " " + (screen.height / 1080f));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scaleX, scaleY, 1); // Масштабирование
        //guiGraphics.fill(Math.round(x / scaleX), Math.round(y / scaleY), Math.round(x / scaleX) + width, Math.round(y / scaleY) + height, 0x8800FF00);
        //WarHelper.devLog(y + " " + scaleY + " " + parent);
        if (isCentre) {
            guiGraphics.drawCenteredString(
                    font,
                    text,
                    Math.round(x / scaleX),
                    Math.round(y / scaleY),
                    color
            );
        } else {
            guiGraphics.drawString(
                    font,
                    text,
                    Math.round(x / scaleX),
                    Math.round(y / scaleY),
                    color,
                    true // dropShadow
            );
        }
        guiGraphics.pose().popPose();
    }


    public void changeText(String newText) {
        this.text = Component.literal(newText);
    }
}