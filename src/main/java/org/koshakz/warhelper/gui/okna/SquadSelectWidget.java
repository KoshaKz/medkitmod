package org.koshakz.warhelper.gui.okna;

import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.gui.api.*;

public class SquadSelectWidget extends UIContainer {

    public SquadSelectWidget(float x, float y, float width, float height, String teamTexture, String teamName) {
        super(x, y, width, height);

        UILabel uiLabel = new UILabel(this,
                .5f, .6f, 2f, 2f,
                Component.literal(teamName),
                0xFFFFFF,
                true);

        UIButton selectTeamButton = new UIButton(
                this,
                0f, 0f, 1f, 0.5f,
                teamTexture,
                () -> System.out.println("Кнопка 1 нажата"));

        this.addChild(uiLabel);
        this.addChild(selectTeamButton);
    }
}