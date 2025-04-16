package org.koshakz.warhelper.gui.widget;

import net.minecraft.client.Minecraft;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;
import org.koshakz.warhelper.gui.api.UIContainer;
import org.koshakz.warhelper.gui.api.UIEntity;
import org.koshakz.warhelper.gui.api.UIImage;

import static net.minecraftforge.common.crafting.CraftingHelper.getItemStack;

public class MapWidget extends UIContainer {
    private final UIEntity playerWidget;
    private final UIEntity playerWidget2;

    private ItemStack getItemStack(String itemId) {
        ResourceLocation location = new ResourceLocation(itemId);
        Item item = ForgeRegistries.ITEMS.getValue(location);

        if (item == null) {
            // Для отладки
            Minecraft.getInstance().player.sendSystemMessage(
                    Component.literal("Item not found: " + itemId)
            );
            return ItemStack.EMPTY;
        }

        return new ItemStack(item);
    }

    public MapWidget(float x, float y, float width, float height) {
        super(x, y, width, height);

        playerWidget = new UIEntity(
                this,
                0.5f, 0.5f,
                0.3f, 0.4f,
                0.5f,
                Minecraft.getInstance().player
        );

        playerWidget.setArmor(
                getItemStack("combatgear:ttsko_helmet"),
                getItemStack("combatgear:ttsko_chestplate"),
                ItemStack.EMPTY, // Пустые штаны
                getItemStack("combatgear:ttsko_boots")
        );

        playerWidget2 = new UIEntity(
                this,
                0.2f, 0.2f,
                0.3f, 0.4f,
                0.5f,
                Minecraft.getInstance().player
        );

        playerWidget2.setArmor(
                new ItemStack(Items.NETHERITE_HELMET),
                new ItemStack(Items.NETHERITE_CHESTPLATE),
                getItemStack("combatgear:ttsko_leggings"),
                new ItemStack(Items.NETHERITE_BOOTS)
        );

        addChild(playerWidget);
        addChild(playerWidget2);
        addChild(new UIImage(this, 0.05f, 0.05f, 0.9f, 0.9f, "map_texture"));
    }
}