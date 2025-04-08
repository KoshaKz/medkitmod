package org.koshakz.warhelper.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.koshakz.warhelper.WarHelper;
import org.koshakz.warhelper.block.ModBlocks;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WarHelper.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MAIN_CREATIVE_TAB = CREATIVE_MODE_TABS.register("warhelper_main_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BANDAGE.get()))
                    .title(Component.translatable("creativetab.warhelper.main"))
                    .displayItems((pParameters, creativeTab) -> {

                        creativeTab.accept(ModBlocks.TEST_BLOCK.get());
                        creativeTab.accept(ModItems.BANDAGE.get());
                    })
                    .build());



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}