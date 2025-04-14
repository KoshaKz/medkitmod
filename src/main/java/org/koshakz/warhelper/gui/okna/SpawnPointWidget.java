package org.koshakz.warhelper.gui.okna;

import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.gui.api.UIButton;
import org.koshakz.warhelper.gui.api.UIContainer;
import org.koshakz.warhelper.gui.api.UILabel;

public class SpawnPointWidget extends UIContainer {
    public SpawnPointWidget(float x, float y, float width, float height) {
        super(x, y, width, height);

        UILabel title = new UILabel(
                this, 0.01f, 0.05f, 2.5f, 2.5f,
                Component.literal("ВЫБРАТЬ ТОЧКУ ВОЗРОЖДЕНИЯ"), 0xFFFFFF, false
        );

        isBackgroundEnable = true;

        UIButton penis = new UIButton(
                this,
                0.6f,
                0.9f,
                0.2f,
                0.05f,
                "qwasdf",
                () -> System.out.println("хуйхуй")
        );


        addChild(title);
    }
}
