package org.koshakz.warhelper.game;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.GameType;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.koshakz.warhelper.WarHelper;
import org.koshakz.warhelper.utils.Network.NetworkHandler;
import org.koshakz.warhelper.utils.Network.Packets.onClient.squad.SquadAction;
import org.koshakz.warhelper.utils.Network.Packets.onClient.squad.UpdateSquadPacket;
import org.koshakz.warhelper.utils.PlayerUtils;

import java.util.ArrayList;
import java.util.UUID;

public class GameCore {

    public static ArrayList<WarPlayer> players = new ArrayList<>();
    public static ArrayList<Squad> squads = new ArrayList<>();

    private static MinecraftServer server = ServerLifecycleHooks.getCurrentServer();


    public static void StatGame() {
        players.clear();
        squads.clear();
        addAllOnlinePlayers(server);

        players.forEach(player -> {
            WarHelper.devLog("Stated game");
            NetworkHandler.sendPacketOnClient(player.player, new UpdateSquadPacket(SquadAction.DELETE_ALL, "123", "345", "456",8, new String[0]));
            WarHelper.devLog("Stated game");
            PlayerUtils.sendPlayerTrigger(player.player, "OPEN_SELECT_TEAM");
            WarHelper.devLog("Stated game");
        });

        WarHelper.devLog("Stated game");
    }

    public static void CreateSuad(UUID ownerUUID, String name) {
        WarPlayer squadOwner = getPlayer(ownerUUID);
        //if (squadOwner.squad != null) return;
        Squad squad = new Squad(squadOwner, name);
        squadOwner.squad = squad;
        squads.add(squad);

        players.stream().filter(player -> player.team.equals(squadOwner.team))
                .forEach(player -> {
                    NetworkHandler.sendPacketOnClient(
                            player.player,
                            new UpdateSquadPacket(SquadAction.CREATE, name, "", squadOwner.player.getScoreboardName(),8, new String[] {player.player.getScoreboardName()})
                    );
                }
        );

    }

    public static void ChoseTeam(UUID uuid, Team team) {
        WarPlayer warPlayer = getPlayer(uuid);
        warPlayer.team = team;
        server.getScoreboard().addPlayerToTeam(warPlayer.player.getScoreboardName(), getTeam(team.toString()));

        if (team.equals(Team.SPECTATOR)) {
            warPlayer.player.setGameMode(GameType.SPECTATOR);
        }

        PlayerUtils.sendPlayerTrigger(warPlayer.player, "OPEN_RESPAWN");

    }

    public static void addAllOnlinePlayers(MinecraftServer server) {
        server.getPlayerList().getPlayers().forEach(serverPlayer -> {
                players.add(new WarPlayer(serverPlayer));
            });
    }

    public static PlayerTeam getTeam(String teamName) {
        return server.getScoreboard().getPlayerTeam(teamName);
    }


    public static WarPlayer getPlayer(UUID playerUUID) {
        return players.stream()
                .filter(p -> p.player.getUUID().equals(playerUUID))
                .findFirst()
                .orElse(null);

    }

}
