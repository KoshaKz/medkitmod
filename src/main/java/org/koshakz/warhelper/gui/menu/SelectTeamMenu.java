package org.koshakz.warhelper.gui.menu;

import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.game.Team;
import org.koshakz.warhelper.gui.GuiHandler;
import org.koshakz.warhelper.gui.api.UIButton;
import org.koshakz.warhelper.gui.api.UICustomMenu;
import org.koshakz.warhelper.gui.api.UILabel;
import org.koshakz.warhelper.gui.widget.TeamWidget;
import org.koshakz.warhelper.utils.Network.NetworkHandler;
import org.koshakz.warhelper.utils.Network.Packets.ClientSelectTeamPacket;


public class SelectTeamMenu extends UICustomMenu {
    public SelectTeamMenu() {
        super(Component.literal("Select team menu"));
    }

    @Override
    protected void init() {
        super.init();
        // Инициализируем главное меню
        TeamWidget greenTeamWidget = new TeamWidget(0.1f, 0.37f, 0.3f, 0.6f, "test_test", "greentext", Team.GREEN);
        TeamWidget redTeamWidget = new TeamWidget(0.6f, 0.37f , 0.3f, 0.6f, "red", "redtext", Team.RED);


        UILabel title = new UILabel(
                0.5f, 0.1f, 4.0f, 4.0f,
                Component.literal("Выбор команды"), 0xFFFFFF, true
        );

        addChild(new UIButton(
                .325f, .9f,.35f,.08f,
                "da",
                () -> GuiHandler.SelectTeamClick(Team.SPECTATOR)
        ));
        addChild(greenTeamWidget);
        addChild(redTeamWidget);
        addChild(title);
    }
}
