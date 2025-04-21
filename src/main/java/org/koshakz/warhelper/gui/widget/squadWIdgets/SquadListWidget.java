package org.koshakz.warhelper.gui.widget.squadWIdgets;

import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.gui.api.*;

public class SquadListWidget extends UIContainer {

public SquadListWidget(UIWidget parent, float x, float y, float width, float height, String[] texts) {
        super(parent, x, y, width, height);

        for (int i = 0; i < texts.length; i++) {
            addChild(new UILabel(
                    this,
                    0.15f,
                    i * 0.07f,
                    2.1f,
                    2.1f,
                    Component.literal(texts[i]),
                    0xFFFFFF,
                    false
            ));
        }
    }
}