package org.koshakz.warhelper.gui.widget;

import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.gui.api.UIContainer;
import org.koshakz.warhelper.gui.api.UILabel;

public class ClassSelectionWidget extends UIContainer {
    public ClassSelectionWidget(float x, float y, float width, float height) {
        super(x, y, width, height);

        UILabel title = new UILabel(
                this, 0.01f, 0.05f, 2.5f, 2.5f,
                Component.literal("ВЫБОР КЛАССА"), 0xFFFFFF, false
        );

        addChild(title);
        // Добавьте кнопки классов при необходимости
    }
}
