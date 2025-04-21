package org.koshakz.warhelper.utils;


import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Mod.EventBusSubscriber
public class ClientTaskScheduler {
    private static final Map<UUID, ClientTask> tasks = new ConcurrentHashMap<>();
    private static final List<ClientTask> tasksToAdd = new CopyOnWriteArrayList<>();
    private static final List<UUID> tasksToRemove = new CopyOnWriteArrayList<>();

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.ClientTickEvent.Phase.END) {
            // Добавляем новые задачи
            for (ClientTask task : tasksToAdd) {
                tasks.put(task.id, task);
            }
            tasksToAdd.clear();

            // Удаляем завершенные задачи
            for (UUID id : tasksToRemove) {
                tasks.remove(id);
            }
            tasksToRemove.clear();

            // Обрабатываем тики
            for (ClientTask task : tasks.values()) {
                if (task.tick()) {
                    tasksToRemove.add(task.id);
                }
            }
        }
    }

    public static UUID schedule(Runnable task, int delayTicks) {
        ClientTask clientTask = new ClientTask(task, delayTicks);
        tasksToAdd.add(clientTask);
        return clientTask.id;
    }

    public static void cancel(UUID taskId) {
        tasksToRemove.add(taskId);
    }

    private static class ClientTask {
        private final UUID id = UUID.randomUUID();
        private final Runnable task;
        private int remainingTicks;

        public ClientTask(Runnable task, int delayTicks) {
            this.task = task;
            this.remainingTicks = delayTicks;
        }

        public boolean tick() {
            if (--remainingTicks <= 0) {
                task.run();
                return true;
            }
            return false;
        }
    }
}