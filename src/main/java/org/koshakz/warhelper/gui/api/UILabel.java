package org.koshakz.warhelper.gui.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class UILabel extends UIWidget {
    private Component text;
    private int color;
    private Font font;

    private float scaleX;
    private float scaleY;

    public UILabel(int x, int y, Component text, int color) {
        super(x, y, 0, 0);
        this.text = text;
        this.color = color;
        this.font = Minecraft.getInstance().font;
        //Можно;
    }

    public UILabel(float percentX, float percentY, float scaleX, float scaleY, Component text, int color) {
        super(percentX, percentY, 0, 0);
        this.text = text;
        this.color = color;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public UILabel(UIWidget parent, float percentX, float percentY, float scaleX, float scaleY, Component text, int color) {
        super(parent, percentX, percentY, 0, 0);
        this.text = text;
        this.color = color;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scaleX, scaleY, 1f); // Масштабирование
        guiGraphics.drawString(
                Minecraft.getInstance().font,
                text,
                x,
                y,
                color,
                true // dropShadow
        );
        guiGraphics.pose().popPose();
    }

    public void changeText(String newText) {
        this.text = Component.literal(newText);
    }
}