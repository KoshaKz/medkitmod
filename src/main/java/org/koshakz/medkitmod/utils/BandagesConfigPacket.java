package org.koshakz.medkitmod.utils;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.koshakz.medkitmod.item.custom.Bandages;

import java.util.function.Supplier;

public class BandagesConfigPacket {
    private final int number1;
    private final int number2;
    private final int number3;

    public BandagesConfigPacket(int number1, int number2, int number3) {
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
    }

    public static void encode(BandagesConfigPacket packet, FriendlyByteBuf buffer) {
        buffer.writeInt(packet.number1);
        buffer.writeInt(packet.number2);
        buffer.writeInt(packet.number3);
    }

    public static BandagesConfigPacket decode(FriendlyByteBuf buffer) {
        return new BandagesConfigPacket(buffer.readInt(), buffer.readInt(), buffer.readInt());
    }

    public static void handle(BandagesConfigPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // Клиентский код обработки пакета
            System.out.println("Received numbers: " + packet.number1 + ", " + packet.number2 + ", " + packet.number3);
            Bandages.USE_DURATION = packet.number1;
            // Здесь можно добавить логику обработки чисел на клиенте
        });
        ctx.get().setPacketHandled(true);
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }

    public int getNumber3() {
        return number3;
    }
}