package org.koshakz.medkitmod.gui.menu;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.scores.Team;
import org.koshakz.medkitmod.gui.api.UIContainer;
import org.koshakz.medkitmod.gui.api.UICustomMenu;
import org.koshakz.medkitmod.gui.api.UIImage;
import org.koshakz.medkitmod.gui.api.UILabel;
import org.koshakz.medkitmod.gui.okna.TeamWidget;


public class SelectTeamMenu extends UICustomMenu {
    public SelectTeamMenu() {
        super(Component.literal("Select team menu"));
    }

    UIContainer menu = new UIContainer( 0, 0, this.width, this.height);
    @Override
    protected void init() {
        // Инициализируем главное меню
        TeamWidget greenTeamWidget = new TeamWidget(0.05f, 0.3f, 300, 100, "green", "greentext");
        TeamWidget redTeamWidget = new TeamWidget(getPercentX(0.5f), getPercentY(0.3f), 300, 100, "red", "redtext");

        menu.addChild(greenTeamWidget);
        menu.addChild(redTeamWidget);
    }
}
