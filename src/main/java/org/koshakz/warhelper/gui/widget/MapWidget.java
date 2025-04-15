package org.koshakz.warhelper.gui.widget;

import org.koshakz.warhelper.gui.api.UIContainer;
import org.koshakz.warhelper.gui.api.UIImage;

public class MapWidget extends UIContainer {
    public MapWidget(float x, float y, float width, float height) {
        super(x, y, width, height);

        addChild(new UIImage(this, 0.05f, 0.05f, 0.9f, 0.9f, "map_texture"));
    }
}