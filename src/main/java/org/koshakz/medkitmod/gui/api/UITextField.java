package org.koshakz.medkitmod.gui.api;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import org.koshakz.medkitmod.gui.menu.VoMenu;

public class UITextField extends UIWidget {
    private final VoMenu screen;
    public final EditBox editBox;
    private boolean isFocused = false;

    public UITextField(VoMenu screen, int x, int y, int width, int height, Font font) {
        super(x, y, width, height);
        this.screen = screen;
        this.editBox = new EditBox(font, x, y, width, height, Component.empty());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        editBox.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isMouseOver(mouseX, mouseY)) {
            editBox.mouseClicked(mouseX, mouseY, button);
            setFocused(true);
            return true;
        }
        setFocused(false);
        return false;
    }

    public void setFocused(boolean focused) {
        this.isFocused = focused;
        editBox.setFocused(focused);
    }

    public String getValue() {
        return editBox.getValue();
    }

    public void setValue(String text) {
        editBox.setValue(text);
    }

    public void setTextColor(int color) {
        editBox.setTextColor(color);
    }

    public void setMaxLength(int length) {
        editBox.setMaxLength(length);
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
        editBox.setX(x);
        editBox.setY(y);
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        editBox.setWidth(width);
    }
}