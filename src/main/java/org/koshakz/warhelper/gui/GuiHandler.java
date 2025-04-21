package org.koshakz.warhelper.gui;

import org.koshakz.warhelper.WarHelper;
import org.koshakz.warhelper.game.ClientSquad;
import org.koshakz.warhelper.game.Team;
import org.koshakz.warhelper.gui.menu.SquadMenu;
import org.koshakz.warhelper.utils.Network.NetworkHandler;
import org.koshakz.warhelper.utils.Network.Packets.onClient.squad.UpdateSquadPacket;
import org.koshakz.warhelper.utils.Network.Packets.onServer.ClientSelectTeamPacket;

import java.util.ArrayList;
import java.util.Arrays;

public class GuiHandler {


    public static SquadMenu squadMenu = new SquadMenu();

    public static ArrayList<ClientSquad> squads = new ArrayList<>();

    public static void SelectTeamClick(Team team){
        NetworkHandler.sendPacketOnServet(new ClientSelectTeamPacket(team));
        //Minecraft.getInstance().screen.onClose();
    }

    public static void onUpdateSquad(UpdateSquadPacket packet) {
        switch (packet.squadAction) {
            case CREATE -> squads.add(
                    new ClientSquad(packet.name, packet.owner, packet.members, packet.maxMembers)
            );
            case DELETE -> squads.remove(getSquad(packet.name));
            case DELETE_ALL -> squads.clear();
            case UPDATE -> {
                ClientSquad clientSquad = getSquad(packet.name);

                clientSquad.name = packet.newName;
                clientSquad.owner = packet.owner;
                clientSquad.members = packet.members;
                clientSquad.maxMembers = packet.maxMembers;
            }
            case JOIN -> {

            }


        }
        updateSquadUI();
    }


    public static ClientSquad getSquad(String name) {
        return squads.stream()
                .filter(s -> s.name.equals(name))
                .findFirst()
                .orElse(null);

    }


    public static void updateSquadUI() {
        squadMenu.squadSelectionWidget.RenderSquads();
    }

    public static void ExpandButton(String name) {
        WarHelper.devLog(getSquad(name).isOpen + "");
        getSquad(name).isOpen = true;
    }



}
