package org.koshakz.warhelper.gui;

import net.minecraft.client.Minecraft;
import org.koshakz.warhelper.game.Team;
import org.koshakz.warhelper.utils.Network.NetworkHandler;
import org.koshakz.warhelper.utils.Network.Packets.ClientSelectTeamPacket;

public class GuiHandler {

    public static void SelectTeamClick(Team team){
        NetworkHandler.sendPacketOnServet(new ClientSelectTeamPacket(team));
        Minecraft.getInstance().screen.onClose();
    }



}
