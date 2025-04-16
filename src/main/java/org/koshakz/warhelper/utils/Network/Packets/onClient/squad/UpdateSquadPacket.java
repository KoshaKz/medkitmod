package org.koshakz.warhelper.utils.Network.Packets.onClient.squad;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.koshakz.warhelper.gui.GuiHandler;
import org.koshakz.warhelper.utils.Network.Packets.Packet;
import org.koshakz.warhelper.utils.Network.Packets.onClient.BandagesConfigPacket;

import java.util.function.Supplier;

public class UpdateSquadPacket extends Packet {

    public final SquadAction squadAction;
    public final String name;

    public final String newName;
    public final int maxMembers;
    public final String[] members;
    public final String owner;

    public UpdateSquadPacket(SquadAction squadAction, String name, String newName, String owner, int maxMembers, String[] members) {
        this.squadAction = squadAction;
        this.name = name;
        this.newName = newName;
        this.owner = owner;
        this.maxMembers = maxMembers;
        this.members = members;



    }

    public static void encode(UpdateSquadPacket packet, FriendlyByteBuf buffer) {
        buffer.writeEnum(packet.squadAction);
        buffer.writeUtf(packet.name);
        buffer.writeUtf(packet.newName);
        buffer.writeUtf(packet.owner);
        buffer.writeInt(packet.maxMembers);
        buffer.writeByte(packet.members.length);
        for (String member : packet.members) {
            buffer.writeUtf(member);
        }
    }

    public static UpdateSquadPacket decode(FriendlyByteBuf buffer) {
        return new UpdateSquadPacket(
                buffer.readEnum(SquadAction.class),
                buffer.readUtf(),
                buffer.readUtf(),
                buffer.readUtf(),
                buffer.readInt(),
                getArray(buffer, buffer.readByte()));
    }

    public static void handle(UpdateSquadPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            GuiHandler.onUpdateSquad(packet);
        });
        ctx.get().setPacketHandled(true);
    }

    public static String[] getArray(FriendlyByteBuf buffer, byte len) {
        String[] strings = new String[len];
        for (byte i = 0; i < len; i++) {
            strings[i] = buffer.readUtf();
        }

        return strings;
    }
}




