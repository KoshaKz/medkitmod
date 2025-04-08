package org.koshakz.medkitmod.gui.okna;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.koshakz.medkitmod.gui.api.*;
import org.koshakz.medkitmod.gui.menu.VoMenu;

public class TeamWidget extends UIContainer {
    private final UIButton selectTeamButton;
    private final UILabel uiLabel;

    public TeamWidget(float x, float y, float width, float height, String teamTexture, String teamName) {
        super(x, y, width, height);
        this.uiLabel = new UILabel(0.1f, 0.35f, Component.literal(teamName), 0xFFFFFF);

        this.selectTeamButton = new UIButton(
                0, 0, 0.5f, 0.3f,
                teamTexture,
                () -> System.out.println("Кнопка 1 нажата"));

        this.addChild(uiLabel);
        this.addChild(selectTeamButton);
    }
}