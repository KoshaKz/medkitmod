package org.koshakz.warhelper.utils.Network.Packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ClientButtonPacket extends Packet{
    private final String buttonId;

    public ClientButtonPacket(String buttonId) {
        this.buttonId = buttonId;
    }

    public static void encode(ClientButtonPacket msg, FriendlyByteBuf buffer) {
        buffer.writeUtf(msg.buttonId);
    }

    public static ClientButtonPacket decode(FriendlyByteBuf buffer) {
        return new ClientButtonPacket(buffer.readUtf());
    }

    public static void handle(ClientButtonPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // Серверная обработка
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                System.out.println("Received button click: " + msg.buttonId + " from " + player.getName());
                // Здесь ваша логика обработки на сервере
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
