package org.koshakz.warhelper.gui.menu;

import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.gui.api.*;
import org.koshakz.warhelper.gui.widget.*;

public class SquadMenu extends UICustomMenu {
    public SquadMenu() {
        super(Component.literal("Squad Setup"));
    }

    public SquadSelectionWidget squadSelectionWidget;

    @Override
    protected void init() {
        super.init();

        squadSelectionWidget = new SquadSelectionWidget(0.02f, 0.1f, 0.23f, 0.75f, "squad_create");
        squadSelectionWidget.isBackgroundEnable = true;

        ClassSelectionWidget classSelectionWidget = new  ClassSelectionWidget(0.285f, 0.1f, 0.23f, 0.3f);
        classSelectionWidget.isBackgroundEnable = true;

        SpawnPointWidget spawnPointWidget = new SpawnPointWidget(0.285f, 0.45f, 0.23f, 0.3f);
        spawnPointWidget.isBackgroundEnable = true;

        MapWidget mapWidget = new MapWidget(0.55f, 0.125f, 0.4f, 0.75f);
        mapWidget.isBackgroundEnable = true;

        addChild(squadSelectionWidget);
        addChild(classSelectionWidget);
        addChild(spawnPointWidget);
        addChild(mapWidget);
    }
}