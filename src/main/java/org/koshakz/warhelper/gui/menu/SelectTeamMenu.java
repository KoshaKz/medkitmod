package org.koshakz.warhelper.gui.menu;

import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.gui.api.UIButton;
import org.koshakz.warhelper.gui.api.UICustomMenu;
import org.koshakz.warhelper.gui.api.UILabel;
import org.koshakz.warhelper.gui.okna.TeamWidget;


public class SelectTeamMenu extends UICustomMenu {
    public SelectTeamMenu() {
        super(Component.literal("Select team menu"));
    }

    @Override
    protected void init() {
        super.init();
        // Инициализируем главное меню
        TeamWidget greenTeamWidget = new TeamWidget(0.1f, 0.37f, 0.3f, 0.6f, "test_test", "greentext", "green");
        TeamWidget redTeamWidget = new TeamWidget(0.6f, 0.37f , 0.3f, 0.6f, "red", "redtext", "red");


        UILabel title = new UILabel(
                0.5f, 0.1f, 4.0f, 4.0f,
                Component.literal("Выбор команды"), 0xFFFFFF, true
        );

        addChild(new UIButton(
                (1f - 0.35f) / 2f,  // X позиция: (1 - ширина)/2 для центрирования
                0.9f,               // Y позиция: 90% от верха
                0.35f,              // ширина: 35% экрана
                0.08f,              // высота: 8% экрана
                "da",
                () -> {}
        ));
        addChild(greenTeamWidget);
        addChild(redTeamWidget);
        addChild(title);
    }
}
