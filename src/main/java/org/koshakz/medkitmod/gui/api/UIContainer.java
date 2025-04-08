package org.koshakz.medkitmod.gui.api;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import org.koshakz.medkitmod.Medkitmod;

import java.util.ArrayList;
import java.util.List;

public class UIContainer extends UIWidget {
    private final List<UIWidget> children = new ArrayList<>();

    public UIContainer(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void addChild(UIWidget widget) {
        children.add(widget);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        for (UIWidget child : children) {
            child.render(guiGraphics, this.x + child.getX(), this.y + child.getY(), partialTick);
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
}