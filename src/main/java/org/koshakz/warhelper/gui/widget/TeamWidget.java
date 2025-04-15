package org.koshakz.warhelper.gui.widget;

import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.game.Team;
import org.koshakz.warhelper.gui.GuiHandler;
import org.koshakz.warhelper.gui.api.*;

public class TeamWidget extends UIContainer {
    private final UIButton selectTeamButton;
    private final UILabel uiLabel;

    public TeamWidget(float x, float y, float width, float height, String teamTexture, String teamName, Team team) {
        super(x, y, width, height);
        uiLabel = new UILabel(this, .5f, .6f, 2f, 2f, Component.literal(teamName), 0xFFFFFF, true);

        selectTeamButton = new UIButton(
                this,
                0f, 0f, 1f, 0.5f,
                teamTexture,
                () -> GuiHandler.SelectTeamClick(team)
        );

        addChild(uiLabel);
        addChild(selectTeamButton);
    }
}