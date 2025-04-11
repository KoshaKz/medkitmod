package org.koshakz.warhelper.gui.api;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import org.koshakz.warhelper.WarHelper;

public class UIImage extends UIWidget {

    private final ResourceLocation texture;

    public UIImage(int x, int y, int width, int height, String textureName) {
        super(x, y, width, height);
        this.texture = new ResourceLocation(WarHelper.MOD_ID, "textures/gui/" + textureName + ".png");
    }

    public UIImage(float percentX, float percentY , float percentWidth, float percentHeight, String textureName) {
        super(percentX, percentY, percentWidth, percentHeight);
        this.texture = new ResourceLocation(WarHelper.MOD_ID, "textures/gui/" + textureName + ".png");
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.blit(texture,
                x, y,
                0, 0,
                width, height,
                width, height);
    }
}
