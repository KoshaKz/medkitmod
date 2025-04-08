package org.koshakz.medkitmod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.koshakz.medkitmod.gui.menu.VoMenu;
import org.koshakz.medkitmod.utils.PlayerUtils;

public class ChangeGamestateCommand{
    public static ArgumentBuilder<CommandSourceStack, ?> register() {
        return Commands.literal("start")
                .then(Commands.argument("state", StringArgumentType.string())
                        .executes(context -> execute(
                                context.getSource(),
                                StringArgumentType.getString(context, "state")
                        ))
                );
    }



    private static int execute(CommandSourceStack source, String arg1) {
        for (Player player : source.getLevel().players()) {
            PlayerUtils.sendPlayerTrigger(player, "OPEN_SELECT_TEAM");
        }
        return Command.SINGLE_SUCCESS;
    }

}

