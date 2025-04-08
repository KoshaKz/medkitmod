package org.koshakz.medkitmod.gui.api;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.koshakz.medkitmod.Medkitmod;

public class UIButton extends UIWidget {
    private final ResourceLocation texture;
    private Component label;
    private final Runnable onClick;

    public UIButton(int x, int y, int width, int height,
                    ResourceLocation texture, Runnable onClick) {
        super(x, y, width, height);
        this.texture = texture;
        this.onClick = onClick;
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
            Medkitmod.LOGGER.info("test! b");
            onClick.run();
            return true;
        }
        return false;
    }

    public UIButton withLabel(Component label) {
        this.label = label;
        return this;
    }
}