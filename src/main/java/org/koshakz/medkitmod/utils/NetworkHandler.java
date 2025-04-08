package org.koshakz.medkitmod.utils;


import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.koshakz.medkitmod.Medkitmod;

public class NetworkHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Medkitmod.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int packetId = 0;

    public static void register() {
        CHANNEL.registerMessage(packetId++, BandagesConfigPacket.class,
                BandagesConfigPacket::encode,
                BandagesConfigPacket::decode,
                BandagesConfigPacket::handle);

        CHANNEL.registerMessage(
                packetId++, // ID пакета
                TriggerPacket.class,
                TriggerPacket::encode,
                TriggerPacket::decode,
                TriggerPacket::handle
        );
    }
}