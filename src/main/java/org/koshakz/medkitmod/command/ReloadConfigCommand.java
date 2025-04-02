package org.koshakz.medkitmod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import org.koshakz.medkitmod.utils.ModConfigHandler;

import java.awt.*;

public class ReloadConfigCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("warhelper")
                .then(Commands.literal("reload").executes(ReloadConfigCommand::run)));
    }
    

    private static int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        if (ModConfigHandler.load()){
            context.getSource().sendSuccess(() -> Component.literal("§aКонфиг был успешно перезагружен!"),true);
        } else {
            context.getSource().sendFailure(Component.literal("§сПри перезагруске конфига произошла ошибка!"));
        }

        return 0;
    }
}
