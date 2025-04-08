package org.koshakz.medkitmod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import org.koshakz.medkitmod.gui.menu.SelectTeamMenu;
import org.koshakz.medkitmod.gui.menu.VoMenu;

@Mod.EventBusSubscriber(modid = "medkitmod", value = Dist.CLIENT)
public class WorldJoinHandler {


    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        // Проверяем только на клиенте и при завершении тика
        if (event.phase != TickEvent.Phase.END || event.side != LogicalSide.CLIENT) return;

        Player player = event.player;
        Minecraft minecraft = Minecraft.getInstance();

        // Проверяем что игрок - это мы, и мы в мире (не в меню)
        if (player != minecraft.player || minecraft.level == null) {// Сброс при выходе из мира
            return;
        }

        // Открываем меню один раз при входе в мир
        if (minecraft.screen == null) {
            minecraft.setScreen(new SelectTeamMenu());
        }
    }
}