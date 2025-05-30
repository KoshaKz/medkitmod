package org.koshakz.warhelper.gui.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import org.koshakz.warhelper.WarHelper;

public class UIButton extends UIWidget {
    private ResourceLocation texture;
    private final Runnable onClick;
    private final SimpleSoundInstance soundClick;

    public UIButton(float percentX, float percentY, float percentWidth, float percentHeight,
                    String textureName, Runnable onClick) {
        super(percentX, percentY, percentWidth, percentHeight);//егор пидорас
        this.texture = new ResourceLocation(WarHelper.MOD_ID, "textures/gui/" + textureName + ".png");
        this.onClick = onClick;
        this.soundClick = SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F);
    }

    public UIButton(UIWidget parent, float percentX, float percentY, float percentWidth, float percentHeight,
                    String textureName, Runnable onClick) {
        super(parent, percentX, percentY, percentWidth, percentHeight);
        this.texture = new ResourceLocation(WarHelper.MOD_ID, "textures/gui/" + textureName + ".png");
        this.onClick = onClick;
        this.soundClick = SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F);
    }


    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {

    int textureYOffset = isMouseOver(mouseX, mouseY) ? height : 0;
        guiGraphics.blit(texture,
                x, y,
                0, textureYOffset,
                width, height,
                width, height * 2);

    }
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isMouseOver(mouseX, mouseY)) {
            Minecraft.getInstance().getSoundManager().play(soundClick);
            onClick.run();
            return true;
        }
        return false;
    }

    public void changeTexture(String textureName) {
        this.texture = new ResourceLocation(WarHelper.MOD_ID, "textures/gui/" + textureName + ".png");
    }
}