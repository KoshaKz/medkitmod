package org.koshakz.medkitmod.gui.okna;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.koshakz.medkitmod.gui.api.*;
import org.koshakz.medkitmod.gui.menu.VoMenu;

public class TeamWidget extends UIContainer {
    private final UIButton selectTeamButton;
    private final UIButton button2;
    private final UITextField uiTextField;

    public TeamWidget(Screen screen, int x, int y, int width, int height) {
        super(x, y, width, height);

        Font font = Minecraft.getInstance().font;

        this.uiTextField = new UITextField((VoMenu) screen, 20,50,200, 20,font);


        // Создаем кнопки для чата
        this.selectTeamButton = new UIButton(
                80, 300, 700, 400,
                "da",
                () -> System.out.println("Кнопка 1 нажата")
        ).withLabel(Component.literal("Отправить"));

        this.button2 = new UIButton(
                120, 10, 100, 20,
                "test23",
                () -> System.out.println("Кнопка 2 нажата")
        ).withLabel(Component.literal("Отмена"));

        // Добавляем кнопки в контейнер


        this.addChild(uiTextField);
        this.addChild(selectTeamButton);
    }
}