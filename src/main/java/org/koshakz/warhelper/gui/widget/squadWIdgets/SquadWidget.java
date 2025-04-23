package org.koshakz.warhelper.gui.widget.squadWIdgets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.koshakz.warhelper.WarHelper;
import org.koshakz.warhelper.gui.GuiHandler;
import org.koshakz.warhelper.gui.api.*;
import org.koshakz.warhelper.utils.ClientTaskScheduler;

public class SquadWidget extends UIContainer {

    private static int OPENING_SPEED;

    private final UIButton moreButton;
    private final SquadTextWidget squadTextWidget;
    private final UIButton button;
    private final UILabel squadCount;
    private final SquadListWidget squadListWidget;
    private float bigHeight;
    private float smallHeight;

    public String name;
    public boolean isOpen;

    public SquadWidget(UIWidget parent, float x, float y, float width, float height, String name, String owner, String squadCountText, String[] playerList, boolean isOpen) {
        super(parent, x, y, width, height);

        this.isOpen = isOpen;

        OPENING_SPEED = Math.round(0.05f * Minecraft.getInstance().screen.height);

        moreButton = new UIButton(
                this,
                0.01f,
                .2f,
                0.15f,     // ширина: 15%
                0.6f, // высота: 60%
                "button_texture",
                () -> moreButtonClick(name)
        );

        squadTextWidget = new SquadTextWidget(
                this,
                0.18f, 0.0f, 0.4f, 1f,
                name,
                owner
        );


        button = new UIButton(
                this,
                0.5f, .25f, 0.3f, 0.5f,
                "test_test",
                () -> GuiHandler.JoinSquadButton(name)
        );

        squadCount = new UILabel(
                this,
                .85f, .4f,2f, 2f,
                Component.literal(squadCountText),
                0xFFFFFF,
                false
        );

        WarHelper.devLog("Parent "+this.y + " " + this.height);

        squadListWidget = new SquadListWidget(
                this,
                0f, 1f, 1f, 1f,
                playerList

        );

        smallHeight = height;
        bigHeight = smallHeight + (squadListWidget.height / (float) parent.height);

        WarHelper.devLog("bigHeigh "+ bigHeight);

        this.height = (int) (parent.height * (isOpen ? bigHeight : smallHeight));




        addChild(moreButton);
        addChild(squadTextWidget);
        addChild(button);
        addChild(squadCount);
        addChild(squadListWidget);
        children.forEach((widget) -> widget.percentY = (widget.y - widget.parent.y) / (float) this.height);

        this.name = name;
        this.isOpen = isOpen;

        this.isBackgroundEnable = true;
        //schedulePeriodic(1);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.enableScissor(x, y, x + width, y + height);

        super.render(guiGraphics, mouseX, mouseY, partialTick);

        guiGraphics.disableScissor();
    }

    private void moreButtonClick(String name) {
        GuiHandler.ExpandButton(name);
    }

    public void startAnimation() {
        int TargetHeight = (int) (parent.height * (isOpen ? bigHeight : smallHeight));
        ClientTaskScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                if (TargetHeight > height) {

                    height = Math.min(TargetHeight, height + OPENING_SPEED);
                    children.forEach((widget) -> widget.percentY = (widget.y - widget.parent.y) / (float) height);

                    ClientTaskScheduler.schedule(this, 1);
                } else if (TargetHeight < height) {

                    height = Math.max(TargetHeight, height - OPENING_SPEED);
                    children.forEach((widget) -> widget.percentY = (widget.y - widget.parent.y) / (float) height);

                    ClientTaskScheduler.schedule(this, 1);
                }
                parent.update();
            }
        }, 1);
    }

    public void switchOpenOption() {
        isOpen = ! isOpen;
    }




}