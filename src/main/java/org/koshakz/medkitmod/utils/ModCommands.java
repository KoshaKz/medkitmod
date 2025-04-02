package org.koshakz.medkitmod.utils;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class ModCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("getmaxvalue")
                .requires(source -> source.hasPermission(2))
                .executes(context -> {
                    int maxValue = ModConfigHandler.MAX_VALUE.get();
                    context.getSource().sendSuccess(
                            () -> Component.literal("MaxValue: " + maxValue),
                            false
                    );
                    return 1;
                })
        );
    }
}
