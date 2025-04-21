package org.koshakz.warhelper.gui.api;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import org.koshakz.warhelper.WarHelper;

public class UIScrollableContainer extends UIContainer {

    private int scrollY;
    private int contentHeight;

    public UIScrollableContainer(float x, float y, float width, float height) {
        super(x, y, width, height);
    }


    public UIScrollableContainer(UIWidget parent, float x, float y, float width, float height) {
        super(parent, x, y, width, height);
        //isBackgroundEnable = true;
        //color = 0xFFFF0000;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (!isVisible) return;
        // Включаем ножницы для обрезки содержимого
        guiGraphics.enableScissor(x, y, x + width, y + height);

        super.render(guiGraphics, mouseX, mouseY, partialTick);

        guiGraphics.disableScissor();

    }
    private boolean isWidgetVisible(UIWidget widget) {
        return widget.y + widget.height > y && widget.y < y + height;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        if (!isVisible) return false;
        int newScroll = (scrollY - (int) (delta * -10));
        if (contentHeight + newScroll < height || newScroll > 0 ) {return false;}
        scrollY = newScroll;

        updateChildrenY();


        return true;
    }

    public void updateChildrenY() {
        int floor = y;
        for (UIWidget widget : children) {
            if (!widget.isVisible) continue;

            widget.setY(floor + scrollY);
            floor += widget.height + 10;

        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (!isVisible) return false;
        for (UIWidget widget : children) {
            if (isWidgetVisible(widget)) {
                if (widget.mouseClicked(mouseX, mouseY, button)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addChild(UIWidget widget) {
        super.addChild(widget);
        updateHeight();
        updateChildrenY();
    }

    @Override
    public void update() {
        updateHeight();
        updateChildrenY();
    }

    public void updateHeight() {
        contentHeight = children.stream().filter(w -> w.isVisible)
                .mapToInt(w -> (w.height + 10))
                .sum();
    }

    public void ClearChildren() {
        children.clear();
        updateHeight();
        updateChildrenY();
    }
}