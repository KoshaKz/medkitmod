package org.koshakz.warhelper.utils;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkDirection;
import org.koshakz.warhelper.utils.Network.NetworkHandler;
import org.koshakz.warhelper.utils.Network.Packets.TriggerPacket;

public class PlayerUtils {
    public static boolean HasTag(LivingEntity entity, String tag) {
        return entity.getTags().contains(tag);
    }


    public static void sendPlayerTrigger(Player player, String triggerId) {
        if (player instanceof ServerPlayer serverPlayer) {
            NetworkHandler.CHANNEL.sendTo(
                    new TriggerPacket(triggerId),
                    serverPlayer.connection.connection,
                    NetworkDirection.PLAY_TO_CLIENT
            );
        }
    }
}


