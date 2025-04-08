package org.koshakz.medkitmod.gui.menu;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.koshakz.medkitmod.gui.api.*;
import org.koshakz.medkitmod.gui.okna.TestWidget;

public class VoMenu extends Screen {

    UIContainer menu = new UIContainer( 0, 0, this.width, this.height);

    public VoMenu() {
        super(Component.literal("Custom UI Screen"));
    }

    @Override
    protected void init() {
        // Инициализируем главное меню
        TestWidget testWidget = new TestWidget(this,
                width/2 - 150, height/2 - 50,
                300, 100
        );


        // Добавляем чат в меню
        //menu.addChild(testWidget);

        // Можно добавить другие элементы
        UILabel title = new UILabel(
                width/2 - 50, 20,
                Component.literal("Мое меню"), 0xFFFFFF
        );
        //menu.addChild(title);

        UIImage image = new UIImage(0, 0, this.width, this.height, "int");
        menu.addChild(image);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // Рендерим фон
        renderBackground(guiGraphics);

        // Рендерим все компоненты меню
        menu.render(guiGraphics, mouseX, mouseY, partialTick);

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return menu.mouseClicked(mouseX, mouseY, button);
    }
}