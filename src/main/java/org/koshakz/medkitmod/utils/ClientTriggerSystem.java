package org.koshakz.medkitmod.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import org.koshakz.medkitmod.gui.menu.SelectTeamMenu;

import java.util.HashMap;
import java.util.Map;

public class ClientTriggerSystem {
    private static final Map<String, Runnable> TRIGGERS = new HashMap<>();

    // Регистрация триггеров
    public static void registerTrigger(String id, Runnable action) {
        TRIGGERS.put(id, action);
    }

    // Активация триггера
    public static void activate(String triggerId) {
        Runnable action = TRIGGERS.get(triggerId);
        if (action != null) {
            Minecraft.getInstance().execute(action::run);
        }
    }

    // Инициализация (вызовите при загрузке клиента)
    public static void init() {
        registerTrigger("OPEN_SELECT_TEAM", () -> {
            Minecraft.getInstance().setScreen(new SelectTeamMenu());
        });
    }
}