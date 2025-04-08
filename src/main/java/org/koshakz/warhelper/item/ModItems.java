package org.koshakz.warhelper.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.koshakz.warhelper.WarHelper;
import org.koshakz.warhelper.item.custom.Bandages;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, WarHelper.MOD_ID);

    public static final RegistryObject<Item> BANDAGE = ITEMS.register("bandages", () -> new Bandages(new Item.Properties().stacksTo(8)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
