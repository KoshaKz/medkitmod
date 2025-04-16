package org.koshakz.warhelper.utils.Network;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.koshakz.warhelper.WarHelper;
import org.koshakz.warhelper.utils.Network.Packets.*;
import org.koshakz.warhelper.utils.Network.Packets.onClient.BandagesConfigPacket;
import org.koshakz.warhelper.utils.Network.Packets.onClient.TriggerPacket;
import org.koshakz.warhelper.utils.Network.Packets.onClient.squad.UpdateSquadPacket;
import org.koshakz.warhelper.utils.Network.Packets.onServer.ClientButtonPacket;
import org.koshakz.warhelper.utils.Network.Packets.onServer.ClientSelectTeamPacket;
import org.koshakz.warhelper.utils.Network.Packets.onServer.CreateSquadPacket;

public class NetworkHandler {
    private static final String PROTOCOL_VERSION = "1";
    private static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(WarHelper.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int packetId = 0;


    public static void sendPacketOnServet(Packet packet) {
        CHANNEL.sendToServer(packet);
    }

    public static void sendPacketOnClient(Player player, Packet packet) {
        if (player instanceof ServerPlayer serverPlayer) {
            NetworkHandler.CHANNEL.sendTo(
                    packet,
                    serverPlayer.connection.connection,
                    NetworkDirection.PLAY_TO_CLIENT
            );
        }
    }

    public static void register() {
        CHANNEL.registerMessage(packetId++,
                BandagesConfigPacket.class,
                BandagesConfigPacket::encode,
                BandagesConfigPacket::decode,
                BandagesConfigPacket::handle
        );

        CHANNEL.registerMessage(
                packetId++,
                TriggerPacket.class,
                TriggerPacket::encode,
                TriggerPacket::decode,
                TriggerPacket::handle
        );

        // Добавляем новый пакет
        CHANNEL.registerMessage(
                packetId++,
                ClientButtonPacket.class,
                ClientButtonPacket::encode,
                ClientButtonPacket::decode,
                ClientButtonPacket::handle
        );

        CHANNEL.registerMessage(
                packetId++,
                ClientSelectTeamPacket.class,
                ClientSelectTeamPacket::encode,
                ClientSelectTeamPacket::decode,
                ClientSelectTeamPacket::handle
        );

        CHANNEL.registerMessage(
                packetId++,
                CreateSquadPacket.class,
                CreateSquadPacket::encode,
                CreateSquadPacket::decode,
                CreateSquadPacket::handle
        );

        CHANNEL.registerMessage(
                packetId++,
                UpdateSquadPacket.class,
                UpdateSquadPacket::encode,
                UpdateSquadPacket::decode,
                UpdateSquadPacket::handle
        );
    }
}