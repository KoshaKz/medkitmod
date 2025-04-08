package org.koshakz.medkitmod.gui.menu;

import net.minecraft.network.chat.Component;
import org.koshakz.medkitmod.gui.api.UICustomMenu;
import org.koshakz.medkitmod.gui.okna.TeamWidget;


public class SelectTeamMenu extends UICustomMenu {
    public SelectTeamMenu() {
        super(Component.literal("Select team menu"));
    }

    @Override
    protected void init() {
        // Инициализируем главное меню
        TeamWidget greenTeamWidget = new TeamWidget(0.05f, 0.3f, 300, 100, "green", "greentext");
        TeamWidget redTeamWidget = new TeamWidget(0.7f, 0.3f, 300, 100, "red", "redtext");

        this.frame.addChild(greenTeamWidget);
        this.frame.addChild(redTeamWidget);
    }
}
