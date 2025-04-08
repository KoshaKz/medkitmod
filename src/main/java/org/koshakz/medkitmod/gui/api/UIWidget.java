package org.koshakz.medkitmod.gui.api;

import net.minecraft.client.gui.GuiGraphics;

public abstract class UIWidget {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public UIWidget(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick);

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }

    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + width &&
                mouseY >= y && mouseY <= y + height;
    }

    // Геттеры и сеттеры
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}