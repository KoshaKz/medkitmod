package org.koshakz.warhelper.gui.widget.squadWIdgets;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.WarHelper;
import org.koshakz.warhelper.gui.api.*;

public class SquadWidget extends UIContainer {

    private final UIButton moreButton;
    private final SquadTextWidget squadTextWidget;
    private final UIButton button;
    private final UILabel squadCount;
    public boolean isOpen;
    private boolean animation;
    private int animatioTime = 1000;

    private long elapsed;
    private int newHeight;

    public SquadWidget(UIWidget parent, float x, float y, float width, float height, String name, String owner, String squadCountText, boolean isBackground) {
        super(parent, x, y, width, height);

        moreButton = new UIButton(
                this,
                0.01f,
                0.2f,
                0.15f,     // ширина: 15%
                0.6f, // высота: 60%
                "button_texture",
                () -> System.out.println("test_test")
        );

        squadTextWidget = new SquadTextWidget(
                this,
                0.18f, 0.0f, 0.4f, 1f,
                name,
                owner
        );


        button = new UIButton(
                this,
                0.5f, 0.25f, 0.3f, 0.5f,
                "test_test",
                this::StartAnimation
        );

        squadCount = new UILabel(
                this,
                1.0f - 0.05f - 0.1f, // X: 95% - 10% (ширина текста)
                0.5f - (0.6f / 2) + (0.6f / 2) - 0.1f, // Выравнивание по центру текста
                2.6f, 2.6f,
                Component.literal(squadCountText),
                0xFFFFFF,
                false
        );


        addChild(moreButton);
        addChild(squadTextWidget);
        addChild(button);
        addChild(squadCount);
        this.isBackgroundEnable = isBackground;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        if (animation) {
            int test = (int) (elapsed - System.currentTimeMillis());
            float test2 = test / (float) animatioTime;
            test2 = 1f - test2;
            //test2++;
            this.height = (int) ((newHeight / 2f) + (test2 * newHeight));
            if (test2 >= 1f) {
                animation = false;
            }
            WarHelper.devLog(test + " | " + test2);
        }
    }

    public void StartAnimation() {
        elapsed = System.currentTimeMillis() + animatioTime;
        isOpen = !isOpen;
        animation = true;
        if (isOpen) {
            newHeight = height * 2;
        } else {
            newHeight = height / 2;
        }

    }
}