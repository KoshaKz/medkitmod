package org.koshakz.warhelper.utils.Network.Packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import org.koshakz.warhelper.game.GameCore;
import org.koshakz.warhelper.game.Team;

import java.util.function.Supplier;

public class ClientSelectTeamPacket extends Packet {
    private final Team team;

    public ClientSelectTeamPacket(Team team) {
        this.team = team;
    }

    public static void encode(ClientSelectTeamPacket packet, FriendlyByteBuf buffer) {
        buffer.writeEnum(packet.team);
    }

    public static ClientSelectTeamPacket decode(FriendlyByteBuf buffer) {
        return new ClientSelectTeamPacket(buffer.readEnum(Team.class));
    }


    public static void handle(ClientSelectTeamPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // Серверная обработка
            ServerPlayer player = ctx.get().getSender();
            GameCore.ChoseTeam(player.getName().toString(), packet.team);
            ctx.get().setPacketHandled(true);
        });
    }
}
