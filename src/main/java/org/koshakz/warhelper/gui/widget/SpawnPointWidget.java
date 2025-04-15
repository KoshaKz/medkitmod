package org.koshakz.warhelper.gui.widget;

import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.gui.api.UICheckbox;
import org.koshakz.warhelper.gui.api.UICheckboxGroup;
import org.koshakz.warhelper.gui.api.UIContainer;
import org.koshakz.warhelper.gui.api.UILabel;

public class SpawnPointWidget extends UIContainer {

    private final UICheckbox checkbox1;
    private final UICheckbox checkbox2;
    private final UICheckbox checkbox3;
    private final UICheckbox checkbox4;
    public SpawnPointWidget(float x, float y, float width, float height) {
        super(x, y, width, height);

        UILabel title = new UILabel(
                this, 0.01f, 0.05f, 2.5f, 2.5f,
                Component.literal("ВЫБОР ТОЧКИ ВОЗРАЖДЕНИЯ"), 0xFFFFFF, false
        );

        UICheckboxGroup group = new UICheckboxGroup();

        checkbox1 = new UICheckbox(
                this, 0f, 0.15f, 1f, 0.15f,
                "test_test", state -> System.out.println("Воин: " + state)
        );

        checkbox2 = new UICheckbox(
                this, 0f, 0.35f, 1f, 0.15f,
                "test_test", state -> System.out.println("Воин: " + state)
        );

        checkbox3 = new UICheckbox(
                this, 0f, 0.55f, 1f, 0.15f,
                "test_test", state -> System.out.println("Воин: " + state)
        );

        checkbox4 = new UICheckbox(
                this, 0f, 0.75f, 1f, 0.15f,
                "test_test", state -> System.out.println("Воин: " + state)
        );

        addChild(title);

        group.add(checkbox1);
        group.add(checkbox2);
        group.add(checkbox3);
        group.add(checkbox4);

        addChild(checkbox1);
        addChild(checkbox2);
        addChild(checkbox3);
        addChild(checkbox4);

    }
}