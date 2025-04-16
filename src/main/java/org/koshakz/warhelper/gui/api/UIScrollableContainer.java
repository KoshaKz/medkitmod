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
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // Включаем ножницы для обрезки содержимого
        guiGraphics.enableScissor(x, y, x + width, y + height);

        // Рендерим видимые виджеты
        for (int i = 0; i < children.size(); i++) {

            UIWidget widget = children.get(i);
            widget.setY((int) ((i + 1) * (widget.height * 1.2f)) + scrollY);

            if (isWidgetVisible(widget)) {
                widget.render(guiGraphics, mouseX, mouseY, partialTick);
            }

        }
        guiGraphics.disableScissor();

    }
    private boolean isWidgetVisible(UIWidget widget) {
        return widget.y + widget.height > y && widget.y < y + height;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        int newScroll = (scrollY - (int) (delta * -10));
        if (contentHeight + newScroll < height || newScroll > 20 ) {return false;}
        scrollY = newScroll;
        return true;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // Обрабатываем в обратном порядке (от верхних элементов к нижним)
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
        contentHeight = children.stream()
                .mapToInt(w -> (int) (w.height * 1.2f))
                .sum();
    }

    public void ClearChildren() {
        children.clear();
    }
}