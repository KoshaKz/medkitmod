package org.koshakz.warhelper.utils.Network.Packets.onServer;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import org.koshakz.warhelper.game.GameCore;
import org.koshakz.warhelper.utils.Network.Packets.Packet;

import java.util.function.Supplier;

public class CreateSquadPacket extends Packet {
    private final String name;

    public CreateSquadPacket(String name) {
        this.name = name;
    }

    public static void encode(CreateSquadPacket packet, FriendlyByteBuf buffer) {
        buffer.writeUtf(packet.name);
    }

    public static CreateSquadPacket decode(FriendlyByteBuf buffer) {
        return new CreateSquadPacket(buffer.readUtf());
    }


    public static void handle(CreateSquadPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            GameCore.CreateSuad(player.getUUID(), packet.name);
            ctx.get().setPacketHandled(true);
        });
    }
}
