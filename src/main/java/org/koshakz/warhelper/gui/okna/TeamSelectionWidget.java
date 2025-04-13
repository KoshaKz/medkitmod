package org.koshakz.warhelper.gui.okna;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.gui.api.UIButton;
import org.koshakz.warhelper.gui.api.UIContainer;
import org.koshakz.warhelper.gui.api.UILabel;
import org.koshakz.warhelper.gui.api.UITextField;

public class TeamSelectionWidget extends UIContainer {
    private final UITextField nameField;
    private final UIButton createButton;

    public TeamSelectionWidget(float x, float y, float width, float height) {
        super(x, y, width, height);

        UILabel title = new UILabel(
                this, 0.01f, 0.01f, 1.3f, 1.3f,
                Component.literal("ОТРЯДЫ"), 0xFFFFFF, false
        );

        this.nameField = new UITextField(
                this,
                0.05f,      // x: 5% слева
                0.9225f,    // y: 25% сверху
                0.5667f,    // ширина: ~56.67%
                0.05f,      // высота: 5%
                Minecraft.getInstance().font
        );

        this.createButton = new UIButton(
                this,
                0.6667f,    // x: ~66.67% слева
                0.9225f,    // y: 25% сверху
                0.2833f,    // ширина: ~28.33%
                0.05f,      // высота: 5%
                "qwasdf",
                () -> System.out.println("Создание отряда")
        );

        addChild(title);
        addChild(nameField);
        addChild(createButton);
    }
}
