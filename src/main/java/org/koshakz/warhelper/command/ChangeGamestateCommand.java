package org.koshakz.warhelper.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.player.Player;
import org.koshakz.warhelper.game.GameCore;
import org.koshakz.warhelper.utils.PlayerUtils;

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
            switch (arg1) {
                case "start":
                    GameCore.StatGame();
                    break;
                case "1":
                    PlayerUtils.sendPlayerTrigger(player, "OPEN_SELECT_TEAM");
                    break;
                case "2":
                    PlayerUtils.sendPlayerTrigger(player, "OPEN_RESPAWN");
                    break;
                case "-1":
                    PlayerUtils.sendPlayerTrigger(player, "VO");
                    break;
            }
        }
        return Command.SINGLE_SUCCESS;
    }

}

