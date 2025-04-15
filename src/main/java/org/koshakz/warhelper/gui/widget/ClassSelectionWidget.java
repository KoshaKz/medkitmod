package org.koshakz.warhelper.gui.widget;

import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.gui.api.UICheckbox;
import org.koshakz.warhelper.gui.api.UICheckboxGroup;
import org.koshakz.warhelper.gui.api.UIContainer;
import org.koshakz.warhelper.gui.api.UILabel;

public class ClassSelectionWidget extends UIContainer {
    public ClassSelectionWidget(float x, float y, float width, float height) {
        super(x, y, width, height);

        UILabel title = new UILabel(
                this, 0.01f, 0.05f, 2.5f, 2.5f,
                Component.literal("ВЫБОР КЛАССА"), 0xFFFFFF, false
        );

        UICheckboxGroup group = new UICheckboxGroup();

        UICheckbox checkbox = new UICheckbox(
                this, 0.5f, 0.5f, 0.1f, 0.1f,
                "test_test",
                state -> System.out.println("Checkbox state: " + state)
        );

        UICheckbox checkbox2 = new UICheckbox(
                this, 0.1f, 0.1f, 0.1f, 0.1f,
                "test_test",
                state -> System.out.println("Checkbox state: " + state)
        );

        UICheckbox checkbox3 = new UICheckbox(
                this, 0.2f, 0.2f, 0.1f, 0.1f,
                "test_test",
                state -> System.out.println("Checkbox state: " + state)
        );


        addChild(title);
        group.add(checkbox);
        group.add(checkbox2);
        group.add(checkbox3);
        addChild(checkbox);
        addChild(checkbox2);
        addChild(checkbox3);
        // Добавьте кнопки классов при необходимости
    }
}
