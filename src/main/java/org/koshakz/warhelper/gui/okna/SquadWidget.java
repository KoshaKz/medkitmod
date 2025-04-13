package org.koshakz.warhelper.gui.okna;

import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.gui.api.UIButton;
import org.koshakz.warhelper.gui.api.UIContainer;
import org.koshakz.warhelper.gui.api.UILabel;
import org.koshakz.warhelper.gui.api.UIWidget;

public class SquadWidget extends UIContainer {
    private final UIButton button;
    private final UILabel text;
    private final UILabel amount;

    public SquadWidget(UIWidget parent, float x, float y, float width, float height) {
        super(parent, x, y, width, height);

        button = new UIButton(this,0f, 0f, 1f, 1f, "1", () -> System.out.println("Создание отряда"));

        text = new UILabel(this, 0.1f, 0.01f, 1f, 1f,
                Component.literal("test"), 0xFFFFFF, false);

        amount = new UILabel(this, 0.01f, 0.01f, 1f, 1f,
                Component.literal("test"), 0xFFFFFF, false);

        addChild(amount);
        addChild(button);
        addChild(text);

    }
}
