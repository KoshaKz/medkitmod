package org.koshakz.warhelper.gui.okna;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.Screen;
import org.koshakz.warhelper.gui.api.*;

public class TestWidget extends UIContainer {
    private final UIButton button1;
    private final UIButton button2;
    private final UITextField uiTextField;

    public TestWidget(Screen screen, int x, int y, int width, int height) {
        super(x, y, width, height);

        Font font = Minecraft.getInstance().font;

        this.uiTextField = new UITextField( 20,50,200, 20,font);


        // Создаем кнопки для чата
        this.button1 = new UIButton(
                10, 10, 100, 20,
                "da",
                () -> System.out.println("Кнопка 1 нажата")
        );

        this.button2 = new UIButton(
                120, 10, 100, 20,
                "test23",
                () -> System.out.println("Кнопка 2 нажата")
        );

        // Добавляем кнопки в контейнер


        this.addChild(uiTextField);
        this.addChild(button1);
        this.addChild(button2);
    }
}