package org.koshakz.warhelper.game;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.koshakz.warhelper.utils.PlayerUtils;

import java.util.ArrayList;

public class GameCore {

    public static ArrayList<WarPlayer> players = new ArrayList<>();
    public static ArrayList<Squad> squads = new ArrayList<>();

    private static MinecraftServer server = ServerLifecycleHooks.getCurrentServer();


    public static void StatGame() {
        PlayerList playerList = server.getPlayerList();
        players.forEach(player ->
                PlayerUtils.sendPlayerTrigger(player, "OPEN_TEAM")
        );
    }

    public static void ChoseTeam(String name, Team team) {
        WarPlayer player = players.stream().filter(p -> p.getName().toString().equals(name)).findFirst().orElse(null);
        if (player == null) return;
        player.team = team;

        player.addTag(team.toString());
    }


    
}
