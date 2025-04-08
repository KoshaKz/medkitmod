package org.koshakz.warhelper.command;

import net.minecraft.commands.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@net.minecraftforge.fml.common.Mod.EventBusSubscriber(modid = "warhelper", bus = net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.FORGE)
public class ModCommands {
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
         event.getDispatcher().register(Commands.literal("warhelper").requires(source -> source.hasPermission(2))
                 .then(Commands.literal("reload").executes(ReloadConfigCommand::run))
                 .then(ChangeConfigCommand.register()));
                 //.then(ChangeGamestateCommand.register()));
    }
}
