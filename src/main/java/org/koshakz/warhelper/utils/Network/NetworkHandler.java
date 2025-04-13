package org.koshakz.warhelper.utils.Network;


import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.koshakz.warhelper.WarHelper;
import org.koshakz.warhelper.utils.Network.Packets.BandagesConfigPacket;
import org.koshakz.warhelper.utils.Network.Packets.TriggerPacket;

public class NetworkHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(WarHelper.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int packetId = 0;

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
    }
}