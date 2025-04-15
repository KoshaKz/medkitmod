package org.koshakz.warhelper.utils;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.koshakz.warhelper.utils.Network.NetworkHandler;
import org.koshakz.warhelper.utils.Network.Packets.onClient.TriggerPacket;

public class PlayerUtils {
    public static boolean HasTag(LivingEntity entity, String tag) {
        return entity.getTags().contains(tag);
    }


    public static void sendPlayerTrigger(Player player, String triggerId) {
       NetworkHandler.sendPacketOnClient(player, new TriggerPacket(triggerId));
    }
}


