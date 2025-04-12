package org.koshakz.warhelper.gui.menu;

import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.gui.api.UIButton;
import org.koshakz.warhelper.gui.api.UICustomMenu;
import org.koshakz.warhelper.gui.api.UILabel;
import org.koshakz.warhelper.gui.okna.SquadSelectWidget;
import org.koshakz.warhelper.gui.okna.TeamWidget;


public class SquadMenu extends UICustomMenu {
    public SquadMenu() {
        super(Component.literal("Select team menu"));
    }

    @Override
    protected void init() {
        super.init();
        // Инициализируем главное меню
        SquadSelectWidget squadSelectWidget = new SquadSelectWidget(0.1f, 0.37f, 0.3f, 0.6f, "test_test", "greentext");


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
        addChild(squadSelectWidget);
        addChild(title);
    }
}
