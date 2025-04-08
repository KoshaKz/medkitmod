package org.koshakz.medkitmod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.RegisterCommandsEvent;
import org.koshakz.medkitmod.gui.menu.VoMenu;
import org.koshakz.medkitmod.utils.ModConfigHandler;


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
        try {
            ServerPlayer player = source.getPlayerOrException();
            player.sendSystemMessage(Component.literal("Подкоманда выполнена! arg1: " + arg1 + ", arg2: " + arg2));
            Minecraft minecraft = Minecraft.getInstance();
            // Проверяем что игрок - это мы, и мы в мире (не в меню
            // Открываем меню один раз при входе в мир
            if (minecraft.screen == null) {
                minecraft.setScreen(new VoMenu());
            }
        } catch (Exception e) {
            source.sendFailure(Component.literal("Эту команду может выполнить только игрок!"));
        }
        return Command.SINGLE_SUCCESS;
    }

}

