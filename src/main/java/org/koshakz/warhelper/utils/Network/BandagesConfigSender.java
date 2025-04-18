package org.koshakz.warhelper.utils.Network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.koshakz.warhelper.item.custom.Bandages;
import org.koshakz.warhelper.utils.Network.Packets.onClient.BandagesConfigPacket;

@Mod.EventBusSubscriber
public class BandagesConfigSender {
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            NetworkHandler.sendPacketOnClient(player, new BandagesConfigPacket(Bandages.USE_DURATION));
        }
    }
}