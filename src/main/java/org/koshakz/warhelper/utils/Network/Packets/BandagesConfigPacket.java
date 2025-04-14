package org.koshakz.warhelper.utils.Network.Packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.koshakz.warhelper.item.custom.Bandages;

import java.util.function.Supplier;

public class BandagesConfigPacket extends Packet {

    public final int USE_DURATION;

    public BandagesConfigPacket(final int USE_DURATION) {
        this.USE_DURATION = USE_DURATION;
    }

    public static void encode(BandagesConfigPacket packet, FriendlyByteBuf buffer) {
        buffer.writeInt(packet.USE_DURATION);
    }

    public static BandagesConfigPacket decode(FriendlyByteBuf buffer) {
        return new BandagesConfigPacket(buffer.readInt());
    }

    public static void handle(BandagesConfigPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Bandages.USE_DURATION = packet.USE_DURATION;
        });
        ctx.get().setPacketHandled(true);
    }
}