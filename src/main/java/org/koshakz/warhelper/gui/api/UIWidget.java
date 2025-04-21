package org.koshakz.warhelper.gui.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import org.koshakz.warhelper.WarHelper;

public abstract class UIWidget {
    public int x;
    public int y;
    public int width;
    public int height;

    public float percentX = 1f;
    public float percentY = 1f;



    public boolean isVisible = true;
    public UIWidget parent;

    public UIWidget(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public UIWidget(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public UIWidget(float percentX, float percentY , float percentWidth, float percentHeight) {
        final Screen screen = Minecraft.getInstance().screen;

        if (screen == null) return;

        this.x = Math.round(screen.width * percentX);
        this.y = Math.round(screen.height * percentY);
        this.width = Math.round(screen.width * percentWidth);
        this.height = Math.round(screen.height * percentHeight);
        this.percentX = percentX;
        this.percentY = percentY;
        //WarHelper.LOGGER.error(screen.width + " " + screen.height + " x: " + percentX + " -> " + this.x + ". y: " + percentY + " -> " + this.y + ". width: " + percentWidth + " -> " + this.width + ". height: " + percentHeight + " -> " + this.height + ". name: " + this);
    }

    public UIWidget(UIWidget parent, float percentX, float percentY , float percentWidth, float percentHeight) {
        this.x = parent.x + Math.round(parent.width * percentX);
        this.y = parent.y + Math.round(parent.height * percentY);
        this.width = Math.round(parent.width * percentWidth);
        this.height = Math.round(parent.height * percentHeight);
        this.parent = parent;
        this.percentX = percentX;
        this.percentY = percentY;
        //LOGGER.error(parent.width + " " + parent.height + " x: " + percentX + " -> " + this.x + ". y: " + percentY + " -> " + this.y + ". width: " + percentWidth + " -> " + this.width + ". height: " + percentHeight + " -> " + this.height + ". name: " + this);
    }

    public abstract void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick);

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }

    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        return false;
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) { return false; }

    public boolean charTyped(char codePoint, int modifiers)  { return false; }

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

    public void update() {return;}

    public void hide() {
        isVisible = false;
        if (parent != null) {
            parent.update();
        }
    }

    public void show() {
        isVisible = true;
        if (parent != null) {
            parent.update();
        }
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }
}