package org.koshakz.warhelper.gui.widget.squadWIdgets;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.WarHelper;
import org.koshakz.warhelper.gui.api.*;
import org.koshakz.warhelper.utils.ClientTaskScheduler;

public class SquadWidget extends UIContainer {

    private static final int OPENING_SPEED = 5;

    private final UIButton moreButton;
    private final SquadTextWidget squadTextWidget;
    private final UIButton button;
    private final UILabel squadCount;
    private final float bigHeight = 0.3f;
    private final float smallHeight = 0.15f;

    public boolean isOpen;

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
                () -> schedulePeriodic( 1)
        );

        squadCount = new UILabel(
                this,
                1.0f - 0.05f - 0.1f, // X: 95% - 10% (ширина текста)
                0.5f - (0.6f / 2) + (0.6f / 2) - 0.1f, // Выравнивание по центру текста
                2f, 2f,
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
    }

    private void schedulePeriodic(int intervalTicks) {
        this.isOpen = !isOpen;
        int TargetHeight = (int) (parent.height * (isOpen ? bigHeight : smallHeight));
        ClientTaskScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                if (TargetHeight > height) {

                    height = Math.min(TargetHeight, height + OPENING_SPEED);
                    children.forEach((widget) -> widget.percentY = (widget.y - widget.parent.y) / (float) height);

                    ClientTaskScheduler.schedule(this, intervalTicks);
                } else if (TargetHeight < height) {

                    height = Math.max(TargetHeight, height - OPENING_SPEED);
                    children.forEach((widget) -> widget.percentY = (widget.y - widget.parent.y) / (float) height);

                    ClientTaskScheduler.schedule(this, intervalTicks);
                }
                parent.update();
            }
        }, intervalTicks);
    }




}