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

    public UILabel(int x, int y, Component text, int color) {
        super(x, y, 0, 0);
        this.text = text;
        this.color = color;
        this.font = Minecraft.getInstance().font;
        this.isCentre = false;
        //Можно;
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
        WarHelper.LOGGER.warn((screen.width / 1920f) + " " + (screen.height / 1080f));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scaleX, scaleY, 1); // Масштабирование
        if (isCentre) {
            guiGraphics.drawCenteredString(
                    font,
                    text,
                    (int) (x / scaleX),
                    (int) (y / scaleY),
                    color
            );
        } else {
            guiGraphics.drawString(
                    font,
                    text,
                    (int) (x / scaleX),
                    (int) (y / scaleY),
                    color,
                    true // dropShadow
            );
        }
        guiGraphics.pose().popPose();
    }

    @Override
    public void setY(int newY) {
        this.y = newY;
        WarHelper.LOGGER.warn(y + " " + newY + " " + this.scaleY);
    }

    public void changeText(String newText) {
        this.text = Component.literal(newText);
    }
}