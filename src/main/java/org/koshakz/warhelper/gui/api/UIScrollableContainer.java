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

    public UIScrollableContainer(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // Включаем ножницы для обрезки содержимого
        guiGraphics.enableScissor(this.x, this.y, this.x + this.width, this.y + this.width);

        // Рендерим видимые виджеты
        for (int i = 0; i < children.size(); i++) {
            UIWidget widget = children.get(i);

            if (isWidgetVisible(widget)) {
                widget.y = (int) (i * (widget.height * 1.2f)) + scrollY;
                widget.render(guiGraphics, mouseX, mouseY, partialTick);
            }

            widget.y = (int) (i * (widget.height * 1.2f)) + scrollY;
            widget.render(guiGraphics, mouseX, mouseY, partialTick);
        }
        guiGraphics.disableScissor();
        // Рендерим скроллбар

    }
    private boolean isWidgetVisible(UIWidget widget) {
        return widget.y + widget.height > y && widget.y < y + height;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {

        if (isMouseOver(mouseX, mouseY)) {
            scrollY = (scrollY - (int) (delta * 5));
            return true;
        }
        return false;
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
}