package org.koshakz.warhelper.gui.widget;

import net.minecraft.client.Minecraft;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.koshakz.warhelper.gui.api.UIContainer;
import org.koshakz.warhelper.gui.api.UIEntity;
import org.koshakz.warhelper.gui.api.UIImage;

public class MapWidget extends UIContainer {
    private final UIEntity playerWidget;
    private final UIEntity playerWidget2;

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
                new ItemStack(Items.DIAMOND_HELMET),
                new ItemStack(Items.DIAMOND_CHESTPLATE),
                new ItemStack(Items.DIAMOND_LEGGINGS),
                new ItemStack(Items.DIAMOND_BOOTS)
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
                new ItemStack(Items.NETHERITE_LEGGINGS),
                new ItemStack(Items.NETHERITE_BOOTS)
        );

        addChild(playerWidget);
        addChild(playerWidget2);
        addChild(new UIImage(this, 0.05f, 0.05f, 0.9f, 0.9f, "map_texture"));
    }
}