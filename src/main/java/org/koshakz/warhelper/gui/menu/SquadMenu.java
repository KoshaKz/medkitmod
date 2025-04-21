package org.koshakz.warhelper.gui.menu;

import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.gui.api.*;
import org.koshakz.warhelper.gui.widget.*;
import org.koshakz.warhelper.gui.widget.squadWIdgets.SquadListWidget;
import org.koshakz.warhelper.gui.widget.squadWIdgets.SquadSelectionWidget;

public class SquadMenu extends UICustomMenu {
    public SquadMenu() {
        super(Component.literal("Squad Setup"));
    }

    public SquadSelectionWidget squadSelectionWidget;

    @Override
    protected void init() {
        super.init();

        // Squad widget (без изменений)
        squadSelectionWidget = new SquadSelectionWidget(0.02f, 0.1f, 0.23f, 0.75f, "squad_create");
        squadSelectionWidget.isBackgroundEnable = true;

        // Class widget (новые координаты X)
        ClassSelectionWidget classSelectionWidget = new ClassSelectionWidget(0.2675f, 0.1f, 0.23f, 0.3f);
        classSelectionWidget.isBackgroundEnable = true;

        // Spawn widget (новые координаты X и Y)
        SpawnPointWidget spawnPointWidget = new SpawnPointWidget(0.2675f, 0.425f, 0.23f, 0.3f);
        spawnPointWidget.isBackgroundEnable = true;

        // Map widget (увеличен, выровнен по высоте с squad)
        MapWidget mapWidget = new MapWidget(0.515f, 0.1f, 0.485f, 0.75f);
        mapWidget.isBackgroundEnable = true;

        addChild(squadSelectionWidget);
        addChild(classSelectionWidget);
        addChild(spawnPointWidget);
        addChild(mapWidget);
    }
}