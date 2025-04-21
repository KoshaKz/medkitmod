package org.koshakz.warhelper.gui.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.koshakz.warhelper.WarHelper;
import org.lwjgl.glfw.GLFW;

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
        //super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        WarHelper.devLog("x: "+mouseX + " y: "+mouseY);
        return frame.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {

        return frame.mouseScrolled(mouseX, mouseY, delta);

    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        super.keyPressed(keyCode, scanCode, modifiers);
        return frame.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        super.charTyped(codePoint, modifiers);
        return frame.charTyped(codePoint, modifiers);
    }

    protected void addChild(UIWidget widget) {
        frame.addChild(widget);
    }
}
