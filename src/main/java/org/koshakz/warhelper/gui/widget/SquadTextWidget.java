package org.koshakz.warhelper.gui.widget;

import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.gui.api.*;

public class SquadTextWidget extends UIContainer {
    private final UILabel squadName;
    private final UILabel squadLeaderName;

    public SquadTextWidget(UIWidget parent, float x, float y, float width, float height) {
        super(parent, x, y, width, height);

        float elementSpacing = 0.05f; // Увеличенный отступ между элементами
        float textScale = 0.01f;      // Коэффициент ширины текста
        float verticalOffset = 0.15f; // Большое смещение от центра

        // Название отряда (значительно выше центра)
        squadName = new UILabel(
                this,
                0.0f,
                0.52f - verticalOffset - (2.6f * textScale / 2), // Y: 35%
                2.6f, 2.6f,
                Component.literal("Отряд 1"),
                0xFFFFFF,
                false
        );

        // Имя лидера (значительно ниже центра)
        squadLeaderName = new UILabel(
                this,
                2.6f * textScale + elementSpacing,
                0.46f + verticalOffset - (2.2f * textScale / 2), // Y: 65%
                2.2f, 2.2f,
                Component.literal("nickname"),
                0xCCCCCC,
                false
        );

        addChild(squadName);
        addChild(squadLeaderName);
    }
}
