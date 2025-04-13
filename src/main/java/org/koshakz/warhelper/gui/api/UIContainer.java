package org.koshakz.warhelper.gui.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;

import java.util.ArrayList;
import java.util.List;



public class UIContainer extends UIWidget {

    public boolean isBackgroundEnable;
    public int color = 0x80FFFFFF;

    protected final List<UIWidget> children = new ArrayList<>();

    public UIContainer(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public UIContainer(int x, int y) {
        super(x, y);
    }

    public UIContainer() {
        super(0, 0);
    }

    public UIContainer(float percentX, float percentY , float percentWidth, float percentHeight) {
        super(percentX, percentY, percentWidth, percentHeight);
    }

    public UIContainer(UIWidget parent, float percentX, float percentY , float percentWidth, float percentHeight) {
        super(parent, percentX, percentY, percentWidth, percentHeight);
    }

    public void addChild(UIWidget widget) {
        //widget.x += this.x;
        //widget.y += this.y;
        children.add(widget);
    }

    public void removeChildren() {
        children.clear();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if(isBackgroundEnable){
            guiGraphics.fill(
                    this.x, this.y,
                    this.x + this.width, this.y + this.height,
                    color
            );
        }
        for (UIWidget child : children) {
            child.render(guiGraphics, mouseX, mouseY, partialTick);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // Обрабатываем в обратном порядке (от верхних элементов к нижним)
        for (UIWidget uiWidget : children) {
            if (uiWidget.mouseClicked(mouseX, mouseY, button)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        for (UIWidget uiWidget : children) {
            if (uiWidget.mouseScrolled(mouseX, mouseY, delta)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        for (UIWidget widget : children) {
            if (widget.keyPressed(keyCode, scanCode, modifiers)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        for (UIWidget widget : children) {
            if (widget.charTyped(codePoint, modifiers)) {
                return true;
            }
        }
        return false;
    }
}