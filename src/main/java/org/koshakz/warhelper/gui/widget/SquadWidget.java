package org.koshakz.warhelper.gui.widget;

import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.gui.api.*;

public class SquadWidget extends UIContainer {

    private final UIButton moreButton;
    private final SquadTextWidget squadTextWidget;
    private final UIButton button;
    private final UILabel squadCount;

    public SquadWidget(UIWidget parent, float x, float y, float width, float height) {
        super(parent, x, y, width, height);

        moreButton = new UIButton(
                this,
                0.01f,
                0.5f - (0.6f / 2),
                0.15f,     // ширина: 15%
                0.6f, // высота: 60%
                "button_texture",
                () -> System.out.println("test_test")
        );

        squadTextWidget = new SquadTextWidget(
                this,
                0.18f,
                0.0f,
                0.4f,
                1f);
        squadTextWidget.isBackgroundEnable = false;

        button = new UIButton(
                this,
                0.5f, // X: центрирование по горизонтали
                0.5f - (0.5f / 2),  // Y: центр (50%) - половина высоты кнопки (20% / 2 = 10%)
                0.3f,               // ширина: 25%
                0.5f,                // высота: 20%
                "test_test",
                () -> System.out.println("123")
        );

        squadCount = new UILabel(
                this,
                1.0f - 0.05f - 0.1f, // X: 95% - 10% (ширина текста)
                0.5f - (0.6f / 2) + (0.6f / 2) - 0.1f, // Выравнивание по центру текста
                2.6f, 2.6f,
                Component.literal("1/x"),
                0xFFFFFF,
                false
        );

        addChild(moreButton);
        addChild(squadTextWidget);
        addChild(button);
        addChild(squadCount);
    }
}