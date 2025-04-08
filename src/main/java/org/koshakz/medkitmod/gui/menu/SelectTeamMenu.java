package org.koshakz.medkitmod.gui.menu;

import net.minecraft.network.chat.Component;
import org.koshakz.medkitmod.gui.api.UICustomMenu;
import org.koshakz.medkitmod.gui.api.UILabel;
import org.koshakz.medkitmod.gui.okna.TeamWidget;


public class SelectTeamMenu extends UICustomMenu {
    public SelectTeamMenu() {
        super(Component.literal("Select team menu"));
    }

    @Override
    protected void init() {
        super.init();
        // Инициализируем главное меню
        TeamWidget greenTeamWidget = new TeamWidget(0.05f, 0.4f, 1f, 1f, "green", "greentext");
        TeamWidget redTeamWidget = new TeamWidget(0.65f, 0.4f, 1f, 1f, "red", "redtext");


        UILabel title = new UILabel(
                0.45f, 0.1f,
                Component.literal("Выбор команды"), 0xFFFFFF
        );

        addChild(greenTeamWidget);
        addChild(redTeamWidget);
        addChild(title);
    }
}
