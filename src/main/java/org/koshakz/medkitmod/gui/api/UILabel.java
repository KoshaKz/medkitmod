package org.koshakz.medkitmod.gui.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class UILabel extends UIWidget {
    private Component text;
    private int color;

    public UILabel(int x, int y, Component text, int color) {
        super(x, y, 0, 0);
        this.text = text;
        this.color = color;
        // Можно рассчитать размер на основе текста
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.drawString(Minecraft.getInstance().font,
                text, x, y, color);
    }
}