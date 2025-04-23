package org.koshakz.warhelper.utils.Network.Packets.onServer;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import org.koshakz.warhelper.WarHelper;
import org.koshakz.warhelper.game.GameCore;
import org.koshakz.warhelper.utils.Network.Packets.Packet;

import java.util.function.Supplier;

public class SquadJoinPacket extends Packet {
    private final String name;

    public SquadJoinPacket(String name) {
        this.name = name;
    }

    public static void encode(SquadJoinPacket packet, FriendlyByteBuf buffer) {
        buffer.writeUtf(packet.name);
    }

    public static SquadJoinPacket decode(FriendlyByteBuf buffer) {
        return new SquadJoinPacket(buffer.readUtf());
    }


    public static void handle(SquadJoinPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            GameCore.playerJoinSquad(player.getUUID(), packet.name);
            ctx.get().setPacketHandled(true);
        });
    }
}
