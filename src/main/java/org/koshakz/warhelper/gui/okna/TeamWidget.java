package org.koshakz.warhelper.gui.okna;

import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.gui.api.*;

public class TeamWidget extends UIContainer {
    private final UIButton selectTeamButton;
    private final UILabel uiLabel;

    public TeamWidget(float x, float y, float width, float height, String teamTexture, String teamName) {
        super(x, y, width, height);
        this.uiLabel = new UILabel(this, .5f, .8f, 1f, 1f, Component.literal(teamName), 0xFFFFFF, true);

        this.selectTeamButton = new UIButton(
                this, 0f, 0f, 1f, 0.5f,
                teamTexture,
                () -> System.out.println("Кнопка 1 нажата"));

        this.addChild(uiLabel);
        this.addChild(selectTeamButton);
    }
}