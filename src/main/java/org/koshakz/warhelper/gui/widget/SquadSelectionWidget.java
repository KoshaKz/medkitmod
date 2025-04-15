package org.koshakz.warhelper.gui.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.gui.api.*;
import org.koshakz.warhelper.utils.Network.NetworkHandler;
import org.koshakz.warhelper.utils.Network.Packets.onServer.ClientButtonPacket;

public class SquadSelectionWidget extends UIContainer {
    private final UITextField nameField;
    private final UIButton createButton;
    public final UIScrollableContainer squadsWidgets;

    public SquadSelectionWidget(float x, float y, float width, float height, String packet) {
        super(x, y, width, height);

        UILabel title = new UILabel(
                this, 0.02f, 0.02f, 2.5f, 2.5f,
                Component.literal("ОТРЯДЫ"), 0xFFFFFF, false
        );

        nameField = new UITextField(
                this,
                0.05f,      // x: 5% слева
                0.9225f,    // y: 25% сверху
                0.5667f,    // ширина: ~56.67%
                0.05f,      // высота: 5%
                Minecraft.getInstance().font
        );

        nameField.setMaxLength(16);
        nameField.setPlaceholder("Имя..");

        createButton = new UIButton(
                this,
                0.6667f,    // x: ~66.67% слева
                0.9225f,    // y: 25% сверху
                0.2833f,    // ширина: ~28.33%
                0.05f,      // высота: 5%
                "qwasdf",
                () -> NetworkHandler.sendPacketOnServet(new ClientButtonPacket(packet)));

        squadsWidgets = new UIScrollableContainer(this, 0f, 0.06f, 1f, 0.85f);

        SquadWidget squadWidget = new SquadWidget(squadsWidgets, 0f, 0.1f, 1f,0.15f);
        squadWidget.isBackgroundEnable = true;

        squadsWidgets.addChild(squadWidget);
        squadsWidgets.addChild(squadWidget);
        squadsWidgets.addChild(squadWidget);
        squadsWidgets.addChild(squadWidget);
        squadsWidgets.addChild(squadWidget);
        squadsWidgets.addChild(squadWidget);
        squadsWidgets.addChild(squadWidget);

        addChild(title);
        addChild(nameField);
        addChild(createButton);
        addChild(squadsWidgets);
    }
}
