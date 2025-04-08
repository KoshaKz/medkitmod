package org.koshakz.warhelper.gui.api;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class UICustomMenu extends Screen {

    protected final UIContainer frame;

    public UICustomMenu(Component pTitle) {
        super(pTitle);
        this.frame = new UIContainer();
    }

    @Override
    protected void init() {
        this.frame.removeChildren(); //шкул шутинг
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(guiGraphics);
        frame.render(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return frame.mouseClicked(mouseX, mouseY, button);
    }

    protected void addChild(UIWidget widget) {
        frame.addChild(widget);
    }
}
