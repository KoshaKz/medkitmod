package org.koshakz.warhelper.gui.menu;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.koshakz.warhelper.gui.api.*;
import org.koshakz.warhelper.gui.okna.SquadSelectWidget;
import org.koshakz.warhelper.gui.okna.TestWidget;

public class MenuTest extends UICustomMenu {
    public MenuTest() {
        super(Component.literal("Select team menu"));
    }

    @Override
    protected void init() {
        super.init();
        // Инициализируем главное меню
        UIScrollableContainer scrollContainer = new UIScrollableContainer(0.25f, 0.1f,
                0.5f, 0.7f);

        // Добавляем элементы в список
        for (int i = 0; i < 20; i++) {
            UIButton button = new UIButton(
                    scrollContainer, 0.1f, 0.1f, 0.8f, 0.2f,
                    "test_test",
                    () -> System.out.println("Нажата кнопка")
            );

            scrollContainer.addChild(button);
        }
        addChild(scrollContainer);
    }
}
