package org.koshakz.warhelper.command;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.utils.ModConfigHandler;


public class ReloadConfigCommand {
    public static int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        if (ModConfigHandler.load()){
            context.getSource().sendSuccess(() -> Component.literal("§aКонфиг был успешно перезагружен!"),true);
        } else {
            context.getSource().sendFailure(Component.literal("§сПри перезагруске конфига произошла ошибка!"));
        }

        return 0;
    }
}
