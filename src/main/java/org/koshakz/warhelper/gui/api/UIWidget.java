package org.koshakz.warhelper.gui.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import org.koshakz.warhelper.WarHelper;

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
    public UIWidget() {}

    public UIWidget(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public UIWidget(float percentX, float percentY , float percentWidth, float percentHeight) {
        final Screen screen = Minecraft.getInstance().screen;

        this.x = (int) (screen.width * percentX);
        this.y = (int) (screen.height * percentY);
        this.width = (int) (screen.height * percentWidth);
        this.height = (int) (screen.height * percentHeight);
        WarHelper.LOGGER.error(screen.width + " " + screen.height + " " + percentX + " " + percentY + " " + this.width + " " + this.height + " " + this);
    }

    public UIWidget(UIWidget parent, float percentX, float percentY , float percentWidth, float percentHeight) {
        this.x = parent.x + (int) (parent.width * percentX);
        this.y = parent.y + (int) (parent.height * percentY);
        this.width = (int) (parent.height * percentWidth);
        this.height = (int) (parent.height * percentHeight);
        WarHelper.LOGGER.error(parent.width + " " + parent.height + " " + percentX + " " + percentY + " " + this.width + " " + this.height + " "+this);
    }

    public abstract void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick);

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }

    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + width &&
                mouseY >= y && mouseY <= y + height;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}