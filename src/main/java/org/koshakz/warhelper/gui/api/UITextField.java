package org.koshakz.warhelper.gui.api;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.WarHelper;

public class UITextField extends UIWidget {
    public final EditBox editBox;
    private boolean isFocused = false;

    public UITextField(int x, int y, int width, int height, Font font) {
        super(x, y, width, height);
        this.editBox = new EditBox(font, x, y, width, height, Component.empty());
        this.editBox.setBordered(true);
        this.editBox.setMaxLength(100);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        editBox.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isMouseOver(mouseX, mouseY)) {
            setFocused(true);
            return editBox.mouseClicked(mouseX, mouseY, button);
        }
        setFocused(false);
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (editBox != null && editBox.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        if (editBox != null && editBox.charTyped(codePoint, modifiers)) {
            return true;
        }
        return super.charTyped(codePoint, modifiers);
    }

    public void setFocused(boolean focused) {
        this.isFocused = focused;
        editBox.setFocused(focused);
    }

    // API методы
    public String getText() {
        return editBox.getValue();
    }

    public void setText(String text) {
        editBox.setValue(text);
    }

    public void setPlaceholder(String placeholder) {
        editBox.setHint(Component.literal(placeholder));
    }

    public void setMaxLength(int length) {
        editBox.setMaxLength(length);
    }

    public void setTextColor(int color) {
        editBox.setTextColor(color);
    }

    public void setBordered(boolean bordered) {
        editBox.setBordered(bordered);
    }
}