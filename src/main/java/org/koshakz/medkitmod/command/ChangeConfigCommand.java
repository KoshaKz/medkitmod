package org.koshakz.medkitmod.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.RegisterCommandsEvent;
import org.koshakz.medkitmod.utils.ModConfigHandler;


public class ChangeConfigCommand {
    public static int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        if (ModConfigHandler.load()){
            context.getSource().sendSuccess(() -> Component.literal("§aКонфиг был успешно перезагружен!"),true);
        } else {
            context.getSource().sendFailure(Component.literal("§сПри перезагруске конфига произошла ошибка!"));
        }

        return 0;
    }

}

