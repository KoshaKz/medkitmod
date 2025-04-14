package org.koshakz.warhelper.gui.okna;

import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.game.Team;
import org.koshakz.warhelper.gui.api.*;
import org.koshakz.warhelper.utils.Network.NetworkHandler;
import org.koshakz.warhelper.utils.Network.Packets.ClientButtonPacket;
import org.koshakz.warhelper.utils.Network.Packets.ClientSelectTeamPacket;

public class TeamWidget extends UIContainer {
    private final UIButton selectTeamButton;
    private final UILabel uiLabel;

    public TeamWidget(float x, float y, float width, float height, String teamTexture, String teamName, Team team) {
        super(x, y, width, height);
        this.uiLabel = new UILabel(this, .5f, .6f, 2f, 2f, Component.literal(teamName), 0xFFFFFF, true);

        this.selectTeamButton = new UIButton(
                this, 0f, 0f, 1f, 0.5f,
                teamTexture,
                () -> NetworkHandler.sendPacketOnServet(new ClientSelectTeamPacket(team)));

        this.addChild(uiLabel);
        this.addChild(selectTeamButton);
    }
}