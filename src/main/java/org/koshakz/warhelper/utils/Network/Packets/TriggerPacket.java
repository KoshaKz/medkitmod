package org.koshakz.warhelper.utils.Network.Packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.koshakz.warhelper.utils.Network.ClientTriggerSystem;

import java.util.function.Supplier;

public class TriggerPacket extends Packet {
    private final String triggerId;

    public TriggerPacket(String triggerId) {
        this.triggerId = triggerId;
    }

    public static void encode(TriggerPacket packet, FriendlyByteBuf buffer) {
        buffer.writeUtf(packet.triggerId);
    }

    public static TriggerPacket decode(FriendlyByteBuf buffer) {
        return new TriggerPacket(buffer.readUtf());
    }

    public static void handle(TriggerPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ClientTriggerSystem.activate(packet.triggerId);
        });
        ctx.get().setPacketHandled(true);
    }
}