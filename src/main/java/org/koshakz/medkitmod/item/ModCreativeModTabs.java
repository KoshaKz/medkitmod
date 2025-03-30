package org.koshakz.medkitmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.koshakz.medkitmod.Medkitmod;
import org.koshakz.medkitmod.block.ModBlocks;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Medkitmod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> BANDAGE = CREATIVE_MODE_TABS.register("nuclearcore_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BANDAGE.get()))
                    .title(Component.translatable("creativetab.medkitmod.main"))
                    .displayItems((pParameters, pOutput) -> {

                        pOutput.accept(ModBlocks.TEST_BLOCK.get());
                        pOutput.accept(ModItems.BANDAGE.get());
                    })
                    .build());



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}