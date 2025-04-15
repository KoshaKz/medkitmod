package org.koshakz.warhelper.gui.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import org.koshakz.warhelper.WarHelper;

import java.util.function.Consumer;

public class UICheckbox extends UIWidget {
    private ResourceLocation texture;
    private boolean checked;
    private final Consumer<Boolean> onToggle;
    private final SimpleSoundInstance soundClick;
    private UICheckboxGroup group;

    public UICheckbox(float percentX, float percentY, float percentWidth, float percentHeight,
                      String textureName, Consumer<Boolean> onToggle) {
        super(percentX, percentY, percentWidth, percentHeight);
        this.texture = new ResourceLocation(WarHelper.MOD_ID, "textures/gui/" + textureName + ".png");
        this.onToggle = onToggle;
        this.soundClick = SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F);
    }

    public UICheckbox(UIWidget parent, float percentX, float percentY, float percentWidth, float percentHeight,
                      String textureName, Consumer<Boolean> onToggle) {
        super(parent, percentX, percentY, percentWidth, percentHeight);
        this.texture = new ResourceLocation(WarHelper.MOD_ID, "textures/gui/" + textureName + ".png");
        this.onToggle = onToggle;
        this.soundClick = SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        boolean isHovered = isMouseOver(mouseX, mouseY);
        int textureYOffset = (checked ? 2 * height : 0) + (isHovered ? height : 0);

        guiGraphics.blit(texture,
                x, y,
                0, textureYOffset,
                width, height,
                width, 4 * height);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isMouseOver(mouseX, mouseY)) {
            if (group != null) {
                // Если чекбокс в группе, разрешаем выбор только если не выбран
                if (!checked) {
                    Minecraft.getInstance().getSoundManager().play(soundClick);
                    setChecked(true);
                    onToggle.accept(true);
                    return true;
                }
                return false; // Игнорируем клик, если уже выбран
            } else {
                // Обычное поведение для чекбоксов вне группы
                Minecraft.getInstance().getSoundManager().play(soundClick);
                boolean newChecked = !checked;
                setChecked(newChecked);
                onToggle.accept(newChecked);
                return true;
            }
        }
        return false;
    }

    public void setChecked(boolean checked) {
        boolean old = this.checked;
        this.checked = checked;
        // Уведомляем группу при активации
        if (checked && group != null && old != checked) {
            group.onCheckboxSelected(this);
        }
    }

    public boolean isChecked() {
        return checked;
    }

    public void changeTexture(String textureName) {
        this.texture = new ResourceLocation(WarHelper.MOD_ID, "textures/gui/" + textureName + ".png");
    }

    public void setGroup(UICheckboxGroup group) {
        this.group = group;
    }

    public UICheckboxGroup getGroup() {
        return group;
    }
}