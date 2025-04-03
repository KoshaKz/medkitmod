package org.koshakz.medkitmod.command;

import net.minecraft.commands.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@net.minecraftforge.fml.common.Mod.EventBusSubscriber(modid = "medkitmod", bus = net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.FORGE)
public class ModCommands {
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
         event.getDispatcher().register(Commands.literal("warhelper")
                .then(Commands.literal("reload").executes(ReloadConfigCommand::run))
                .then(Commands.literal("penis").executes(ChangeConfigCommand::run)));
    }
}
