package org.koshakz.warhelper.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;


public class ChangeConfigCommand {
    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("change")
                .then(Commands.argument("name", StringArgumentType.string())
                        .then(Commands.argument("value", StringArgumentType.string())
                                .executes(context -> execute(
                                        context.getSource(),
                                        StringArgumentType.getString(context, "name"),
                                        StringArgumentType.getString(context, "value")
                                ))
                        )
                );
    }



    private static int execute(CommandSourceStack source, String arg1, String arg2) {
        return Command.SINGLE_SUCCESS;
    }

}

