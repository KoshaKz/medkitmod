package org.koshakz.warhelper.game;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.koshakz.warhelper.WarHelper;
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
                    .ifPresent(player -> {
                        // Логика изменения команды
                        player.team = team; // или ваша кастомная реализация
                        player.player.addTag(team.toString());
                        WarHelper.LOGGER.info("Changed team for player " + player + " to " + team);
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




}
