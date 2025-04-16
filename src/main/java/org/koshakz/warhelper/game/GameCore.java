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
        WarHelper.devLog("da");
        players.forEach(player ->
                PlayerUtils.sendPlayerTrigger(player.player, "OPEN_SELECT_TEAM")
        );
    }

    public static void CreateSuad(UUID ownerUUID, String name) {
        WarHelper.devLog("Server Create Da 1 " + name);
        WarPlayer squadOwner = getPlayer(ownerUUID);
        squads.add(
                new Squad(squadOwner, name)
        );
        WarHelper.devLog("Server Create " + name);
        players.stream().filter(player -> player.team.equals(squadOwner.team))
                .forEach(player -> {
                    NetworkHandler.sendPacketOnClient(
                            player.player,
                            new UpdateSquadPacket(SquadAction.CREATE, name, "123", squadOwner.player.getScoreboardName(),8, new String[] {player.player.getScoreboardName()})
                    );
                }
        );
        WarHelper.devLog("Server Da 3 " + name);
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
