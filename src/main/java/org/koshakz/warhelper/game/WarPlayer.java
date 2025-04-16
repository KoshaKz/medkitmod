package org.koshakz.warhelper.game;

import ca.weblite.objc.Client;
import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeConfig;

public class WarPlayer {

    public ForgeConfig.Client player2;
    public ServerPlayer player;
    public Team team = Team.SPECTATOR;
    public Squad squad;

    public WarPlayer(ServerPlayer player) {
        this.player = player;
    }

}
