package org.koshakz.medkitmod.utils;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.koshakz.medkitmod.item.custom.Bandages;

@Mod.EventBusSubscriber
public class BandagesConfigSender {
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            // Генерируем или получаем 3 числа для передачи
            int number1 = Bandages.USE_DURATION; // Первое число
            int number2 = 100; // Второе число
            int number3 = 777; // Третье число

            // Отправляем пакет клиенту
            NetworkHandler.INSTANCE.sendTo(
                    new BandagesConfigPacket(number1, number2, number3),
                    player.connection.connection,
                    net.minecraftforge.network.NetworkDirection.PLAY_TO_CLIENT
            );
        }
    }
}