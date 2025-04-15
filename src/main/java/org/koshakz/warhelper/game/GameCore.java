package org.koshakz.warhelper.game;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.koshakz.warhelper.WarHelper;
import org.koshakz.warhelper.utils.Network.NetworkHandler;
import org.koshakz.warhelper.utils.PlayerUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class GameCore {

    public static ArrayList<WarPlayer> players = new ArrayList<>();
    public static ArrayList<Squad> squads = new ArrayList<>();

    private static MinecraftServer server = ServerLifecycleHooks.getCurrentServer();


    public static void StatGame() {
        addAllOnlinePlayers(server);
        WarHelper.devLog("da");
        players.forEach(player ->
                PlayerUtils.sendPlayerTrigger(player.player, "OPEN_SELECT_TEAM")
        );
    }

    public static void ChoseTeam(UUID uuid, Team team) {
            players.stream()
                    .filter(p -> p.player.getUUID().equals(uuid))
                    .findFirst()
                    .ifPresent(warPlayer -> {
                        ServerPlayer player = warPlayer.player;
                        WarHelper.devLog(warPlayer.player.getName().getString());
                        server.getScoreboard().addPlayerToTeam(warPlayer.player.getScoreboardName(), getTeam(team.toString()));

                        if (team.equals(Team.SPECTATOR)) {
                            WarHelper.devLog("spec");
                            warPlayer.player.gameMode.changeGameModeForPlayer(GameType.SPECTATOR);
                        }

                        PlayerUtils.sendPlayerTrigger(player, "OPEN_RESPAWN");
                    });
        }

    public static void addAllOnlinePlayers(MinecraftServer server) {
        server.getPlayerList().getPlayers().forEach(serverPlayer -> {
            // Если игрока ещё нет в коллекции
            boolean alreadyExists = players.stream()
                    .anyMatch(cp -> cp.player.getUUID().equals(serverPlayer.getUUID()));

            if (!alreadyExists) {
                // Создаём CustomPlayer (если у вас есть фабричный метод)
                WarPlayer customPlayer = new WarPlayer(serverPlayer);
                players.add(customPlayer);
            }
        });
    }

    public static PlayerTeam getTeam(String teamName) {
        return server.getScoreboard().getPlayerTeam(teamName);
    }




}
