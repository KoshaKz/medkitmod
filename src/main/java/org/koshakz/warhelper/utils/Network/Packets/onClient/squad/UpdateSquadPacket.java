package org.koshakz.warhelper.utils.Network.Packets.onClient.squad;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.koshakz.warhelper.item.custom.Bandages;
import org.koshakz.warhelper.utils.Network.Packets.Packet;
import org.koshakz.warhelper.utils.Network.Packets.onClient.BandagesConfigPacket;

import java.util.function.Supplier;

public class UpdateSquadPacket extends Packet {

    public final Action action;
    public final String name;

    public UpdateSquadPacket(Action action, String name) {
        this.action = action;
        this.name = name;
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




enum Action {
    ADD,
    DELETE,
    UPDATE
}
