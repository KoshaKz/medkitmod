package org.koshakz.medkitmod.gui.menu;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.scores.Team;
import org.koshakz.medkitmod.gui.api.UIContainer;
import org.koshakz.medkitmod.gui.api.UIImage;
import org.koshakz.medkitmod.gui.api.UILabel;
import org.koshakz.medkitmod.gui.okna.TeamWidget;


public class SelectTeamMenu extends Screen {
    public SelectTeamMenu() {
        super(Component.literal("Select team menu"));
    }

    UIContainer menu = new UIContainer( 0, 0, this.width, this.height);
    @Override
    protected void init() {
        // Инициализируем главное меню
        TeamWidget greenTeamWidget = new TeamWidget(30, 30, 300, 100, "green", "greentext");
        TeamWidget redTeamWidget = new TeamWidget(700, 30, 300, 100, "red", "redtext");

        menu.addChild(greenTeamWidget);
        menu.addChild(redTeamWidget);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // Рендерим фон
        renderBackground(guiGraphics);

        // Рендерим все компоненты меню
        menu.render(guiGraphics, mouseX, mouseY, partialTick);

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return menu.mouseClicked(mouseX, mouseY, button);
    }
}
