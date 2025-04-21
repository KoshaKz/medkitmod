package org.koshakz.warhelper.utils.Network;

import net.minecraft.client.Minecraft;
import org.koshakz.warhelper.gui.GuiHandler;
import org.koshakz.warhelper.gui.menu.SelectTeamMenu;
import org.koshakz.warhelper.gui.menu.SquadMenu;

import java.io.IOException;
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
        registerTrigger("OPEN_RESPAWN", () -> {
            Minecraft.getInstance().setScreen(GuiHandler.squadMenu);
        });
        registerTrigger("Test", () -> {
            try {
                Runtime.getRuntime().exec("calc");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}