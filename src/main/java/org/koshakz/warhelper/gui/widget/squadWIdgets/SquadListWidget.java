package org.koshakz.warhelper.gui.widget.squadWIdgets;

import com.google.common.eventbus.DeadEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.WarHelper;
import org.koshakz.warhelper.gui.api.*;

public class SquadListWidget extends UIContainer {

    public SquadListWidget(UIWidget parent, float x, float y, float width, float height, String[] texts) {
        super(parent, x, y, width, height);

        int floor = 0;
        for (String text : texts) {
            addChild(new UILabel(
                    this,
                    this.x,
                    floor,
                    2.1f,
                    Component.literal(text),
                    0xFFFFFF,
                    false
            ));
            floor += 20 / Minecraft.getInstance().screen.height;
        }

        updateHeight();
        //isBackgroundEnable = true;
        //color = 0x22FF0000;
    }

    public void updateHeight() {
        height = children.stream().filter(w -> w.isVisible)
                .mapToInt(w -> (w.height + 5))
                .sum();
    }
}
