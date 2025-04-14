package org.koshakz.warhelper.gui.okna;

import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.gui.api.UIButton;
import org.koshakz.warhelper.gui.api.UIContainer;
import org.koshakz.warhelper.gui.api.UILabel;
import org.koshakz.warhelper.gui.api.UIWidget;

public class SquadWidget extends UIContainer {

    private final UILabel squadName;
    private final UILabel squadCount;
    private final UIButton button;

    public SquadWidget(UIWidget parent, float x, float y, float width, float height) {
        super(parent, x, y, width, height);

        // Название отряда — слева
        // Название отряда (слева)
        squadName = new UILabel(
                this,
                0.03f,        // X: 3% от левого края
                0.5f - (0.2f / 2),  // Y: центр (50%) - половина высоты кнопки (20% / 2 = 10%)
                2.6f, 2.6f,       // Размер текста: 4% (ширина и высота шрифта)
                Component.literal("Отряд 123123"),
                0xFFFFFF,
                false
        );

// Кнопка (центр)
        button = new UIButton(
                this,
                0.5f, // X: центрирование по горизонтали
                0.5f - (0.2f / 2),  // Y: центр (50%) - половина высоты кнопки (20% / 2 = 10%)
                0.25f,               // ширина: 25%
                0.2f,                // высота: 20%
                "button_texture",
                () -> System.out.println("хуйхуй")
        );

// Счетчик "1/x" (справа)
        squadCount = new UILabel(
                this,
                0.78f,         // X: 78% от левого края
                0.5f - (0.2f / 2),  // Y: центр (50%) - половина высоты кнопки (20% / 2 = 10%)
                2.6f, 2.6f,        // Размер текста: 4%
                Component.literal("1/x"),
                0xFFFFFF,
                false
        );

        addChild(squadName);
        addChild(button);
        addChild(squadCount);
    }
}