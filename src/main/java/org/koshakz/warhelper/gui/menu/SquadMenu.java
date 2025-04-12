package org.koshakz.warhelper.gui.menu;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.gui.api.*;


public class SquadMenu extends UICustomMenu {
    public SquadMenu() {
        super(Component.literal("Создание отряда"));
    }

    @Override
    protected void init() {
        super.init();

        // Левый блок - создание отряда (уже по ширине)
        UIContainer squadPanel = new UIContainer(
                0.05f, 0.15f, 0.2f, 0.7f);

        UILabel squadTitle = new UILabel(
                squadPanel, 0.5f, 0.05f, 1.2f, 1.2f,  // Уменьшил масштаб текста
                Component.literal("Создание отряда"), 0xFFFFFF, true
        );

        UITextField squadNameField = new UITextField(
                0.1f, 0.2f, 0.8f, 0.1f,  // Немного увеличил высоту поля
                Minecraft.getInstance().font
        );
        squadNameField.editBox.setHint(Component.literal("Вводительность"));

        UIButton createButton = new UIButton(
                squadPanel, 0.25f, 0.35f, 0.5f, 0.12f,  // Чуть больше кнопка
                "green_button",
                () -> System.out.println("Создание отряда")
        );
        UILabel createLabel = new UILabel(
                createButton, 0.5f, 0.5f, 1.0f, 1.0f,
                Component.literal("СОЗДАТЬ"), 0xFFFFFF, true
        );

        // Центральный блок (уже по ширине)
        UIContainer centerPanel = new UIContainer(
                0.3f, 0.15f, 0.25f, 0.7f
        );

        // Блок выбора класса (верх центра)
        UIImage classPanel = new UIImage(
                0.1f, 0.05f, 0.8f, 0.4f,
                "subpanel_background"
        );
        UILabel classTitle = new UILabel(
                classPanel, 0.5f, 0.1f, 1.2f, 1.2f,
                Component.literal("ВЫБОР КЛАССА"), 0xFFFFFF, true
        );

        // Блок выбора точки возрождения (низ центра)
        UIImage respawnPanel = new UIImage(
                0.1f, 0.55f, 0.8f, 0.4f,
                "subpanel_background"
        );
        UILabel respawnTitle = new UILabel(
                respawnPanel, 0.5f, 0.1f, 1.2f, 1.2f,
                Component.literal("ТОЧКА ВОЗРОЖДЕНИЯ"), 0xFFFFFF, true
        );

        // Правая панель - квадратная карта
        float mapSize = 0.5f; // Размер квадратной карты (50% высоты экрана)
        float mapX = 0.7f - (mapSize - 0.3f)/2; // Центрирование по горизонтали
        UIImage mapPanel = new UIImage(
                mapX, 0.25f, mapSize, mapSize,  // Квадратные пропорции
                "map_background"
        );
        UILabel mapTitle = new UILabel(
                mapPanel, 0.5f, 0.05f, 1.2f, 1.2f,
                Component.literal("КАРТА"), 0xFFFFFF, true
        );

        // Добавление элементов
        addChild(squadPanel);
        squadPanel.addChild(squadTitle);
        squadPanel.addChild(squadNameField);
        squadPanel.addChild(createButton);

        addChild(centerPanel);
        centerPanel.addChild(classPanel);
        classPanel.addChild(classTitle);
        centerPanel.addChild(respawnPanel);
        respawnPanel.addChild(respawnTitle);

        addChild(mapPanel);
        mapPanel.addChild(mapTitle);
    }
}