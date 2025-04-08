package org.koshakz.medkitmod.gui.api;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class UIImage extends UIWidget {

    private final ResourceLocation texture;

    public UIImage(int x, int y, int width, int height, ResourceLocation texture) {
        super(x, y, width, height);
        this.texture = texture;
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
