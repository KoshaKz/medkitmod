package org.koshakz.warhelper.game;

import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class WarPlayer {

    public ServerPlayer player;
    public Team team = Team.SPECTATOR;

    public WarPlayer(ServerPlayer player) {
        this.player = player;
    }

}
