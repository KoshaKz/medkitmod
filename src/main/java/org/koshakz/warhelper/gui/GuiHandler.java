package org.koshakz.warhelper.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.koshakz.warhelper.WarHelper;
import org.koshakz.warhelper.game.ClientSquad;
import org.koshakz.warhelper.game.Team;
import org.koshakz.warhelper.gui.menu.SquadMenu;
import org.koshakz.warhelper.utils.Network.NetworkHandler;
import org.koshakz.warhelper.utils.Network.Packets.onClient.squad.UpdateSquadPacket;
import org.koshakz.warhelper.utils.Network.Packets.onServer.ClientSelectTeamPacket;
import org.koshakz.warhelper.utils.Network.Packets.onServer.CreateSquadPacket;
import org.koshakz.warhelper.utils.Network.Packets.onServer.SquadJoinPacket;

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
        if (!Screen.hasShiftDown()) {
            squads.stream().filter((squad) -> !squad.name.equals(name)).forEach(
                    (squad) -> {
                        squad.isOpen = false;
                        squadMenu.squadSelectionWidget.getSquadWidget(squad.name).isOpen = false;
                        squadMenu.squadSelectionWidget.getSquadWidget(squad.name).startAnimation();
                    }
            );


        }
        getSquad(name).switchOpenOption();
        squadMenu.squadSelectionWidget.getSquadWidget(name).switchOpenOption();
        squadMenu.squadSelectionWidget.getSquadWidget(name).startAnimation();

        WarHelper.devLog(getSquad(name).isOpen + "");

    }

    public static void CreateSquadButton(String name) {
        if (name.isEmpty()) return;
        if (getSquad(name) != null) return;
        NetworkHandler.sendPacketOnServet(new CreateSquadPacket(name));
    }

    public static void JoinSquadButton(String name) {
        NetworkHandler.sendPacketOnServet(new SquadJoinPacket(name));
    }



}
