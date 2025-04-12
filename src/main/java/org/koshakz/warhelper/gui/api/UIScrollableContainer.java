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

public class UIScrollableContainer extends UIContainer {
    private final List<UIWidget> children = new ArrayList<>();
    private int scrollY;
    private int contentHeight;
    private boolean isScrolling;
    private final int scrollbarWidth = 6;
    private final int scrollbarPadding = 2;

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

            widget.y = (i * (this.y / 5)) + scrollY;
            widget.render(guiGraphics, mouseX, mouseY, partialTick);
        }

        guiGraphics.disableScissor();

        // Рендерим скроллбар
        renderScrollbar(guiGraphics, mouseX, mouseY);
    }

    private boolean isWidgetVisible(UIWidget widget) {
        return true;
    }

    private void renderScrollbar(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        if (contentHeight <= height) return;

        // Позиция и размер ползунка
        float scrollProgress = (float)scrollY / (contentHeight - height);
        int scrollbarHeight = (int)((float)height * (height / (float)contentHeight));
        scrollbarHeight = Math.max(20, scrollbarHeight);

        int scrollbarX = x + height - scrollbarWidth - scrollbarPadding;
        int scrollbarY = y + (int)((height - scrollbarHeight) * scrollProgress);

        // Рисуем ползунок
        int color = isScrolling ? 0xFFAAAAAA :
                isMouseOverScrollbar(mouseX, mouseY) ? 0xFF888888 : 0xFF666666;
        guiGraphics.fill(scrollbarX, scrollbarY,
                scrollbarX + scrollbarWidth,
                scrollbarY + scrollbarHeight,
                color);
    }

    private boolean isMouseOverScrollbar(int mouseX, int mouseY) {
        if (contentHeight <= height) return false;
        return mouseX >= x + width - scrollbarWidth - scrollbarPadding &&
                mouseX <= x + width &&
                mouseY >= y &&
                mouseY <= y + height;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isMouseOverScrollbar((int)mouseX, (int)mouseY)) {
            isScrolling = true;
            return true;
        }

        // Передаем клик виджетам с учетом скролла
        for (UIWidget child : children) {
            if (isWidgetVisible(child) &&
                    child.mouseClicked(mouseX, mouseY + scrollY, button)) {
                return true;
            }
        }
        return false;
    }

    //@Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        isScrolling = false;
        //return super.mouseReleased(mouseX, mouseY, button);
        return true;
    }

    //@Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (isScrolling) {
            float scrollAmount = (float)dragY / height * contentHeight;
            setScrollY(scrollY + (int)scrollAmount);
            return true;
        }
        return false;
    }

    //@Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        if (isMouseOver(mouseX, mouseY)) {
            setScrollY(scrollY - (int)(delta * 20));
            return true;
        }
        return false;
    }

    public void setScrollY(int newScrollY) {
        this.scrollY = Math.max(0, Math.min(newScrollY, contentHeight - height));
    }

    @Override
    public void addChild(UIWidget widget) {
        children.add(widget);
        updateContentHeight();
    }

    public void clearChildren() {
        children.clear();
        scrollY = 0;
        contentHeight = 0;
    }

    private void updateContentHeight() {
        contentHeight = children.stream()
                .mapToInt(w -> w.y + w.height)
                .max()
                .orElse(0);
    }

    //@Override
    public List<UIWidget> getChildren() {
        return children;
    }

}