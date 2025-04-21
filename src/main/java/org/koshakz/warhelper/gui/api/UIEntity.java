package org.koshakz.warhelper.gui.api;

import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

public class UIEntity extends UIWidget { //Очень опасно
    private LivingEntity entity;
    private final float scalePercent;
    private final EntityType<? extends LivingEntity> entityType;

    public UIEntity(UIWidget parent, float percentX, float percentY, float percentWidth, float percentHeight,
                    float scale, EntityType<? extends LivingEntity> entityType) {
        super(parent, percentX, percentY, percentWidth, percentHeight);
        this.scalePercent = scale;
        this.entityType = entityType;
        initEntity(null);
    }

    public UIEntity(UIWidget parent, float percentX, float percentY, float percentWidth, float percentHeight,
                    float scale, Player originalPlayer) {
        super(parent, percentX, percentY, percentWidth, percentHeight);
        this.scalePercent = scale;
        this.entityType = EntityType.PLAYER;
        initEntity(originalPlayer);
    }

    private void initEntity(Player sourcePlayer) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;

        this.entity = (entityType == EntityType.PLAYER)
                ? createFakePlayer(sourcePlayer != null ? sourcePlayer : mc.player)
                : entityType.create(mc.level);

        if (entity != null) {
            entity.setPos(0, 0, 0);
            entity.setYBodyRot(180);
            entity.setYHeadRot(180);
        }
    }

    private boolean isSlim(Player original) {
        if (original instanceof AbstractClientPlayer acp) {
            // Метод getModelName() возвращает "slim" для моделей с узкими руками
            return "slim".equals(acp.getModelName());
        }
        return false;
    }

    private Player createFakePlayer(Player original) {
        if (original == null || Minecraft.getInstance().level == null) return null;
        
        final boolean slim = isSlim(original);
        GameProfile profile = original.getGameProfile();
        ClientLevel level = (ClientLevel) Minecraft.getInstance().level;

        return new AbstractClientPlayer(level, profile) {
            @Override public boolean isCustomNameVisible() {return false;}
            @Override public Component getDisplayName() {return Component.empty();}
            @Override public boolean shouldShowName() {return false;}
            @Override public boolean isModelPartShown(PlayerModelPart part) {return true;}
            @Override public String getModelName() {return slim ? "slim" : "default";}
        };
    }

    public void setArmor(ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
        if (entity instanceof Player player) {
            player.getInventory().armor.set(3, helmet);
            player.getInventory().armor.set(2, chestplate);
            player.getInventory().armor.set(1, leggings);
            player.getInventory().armor.set(0, boots);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (entity == null) return;

        float centerX = this.x + this.width / 2f;
        float centerY = this.y + this.height / 2f;
        int pixelScale = (int) (this.height * this.scalePercent);

        float angleX = (float) Math.atan((centerX - mouseX) / 40.0F);
        float angleY = (float) Math.atan((centerY - mouseY) / 40.0F);

        renderEntity(guiGraphics, (int) centerX, (int) centerY, pixelScale, angleX, angleY);
    }

    private void renderEntity(GuiGraphics gui, int x, int y, int scale, float angleX, float angleY) {
        if (entity == null) return;

        Quaternionf rotation = new Quaternionf()
                .rotateZ((float) Math.PI)
                .rotateX(angleY * 20.0F * ((float) Math.PI / 180F));

        float prevBodyRot = entity.yBodyRot;
        float prevYRot = entity.getYRot();
        float prevXRot = entity.getXRot();
        float prevHeadRot = entity.yHeadRot;
        float prevHeadRotO = entity.yHeadRotO;

        entity.yBodyRot = 180.0F + angleX * 20.0F;
        entity.setYRot(180.0F + angleX * 40.0F);
        entity.setXRot(-angleY * 20.0F);
        entity.yHeadRot = entity.getYRot();
        entity.yHeadRotO = entity.getYRot();

        renderEntityInternal(gui, x, y, scale, rotation);

        entity.yBodyRot = prevBodyRot;
        entity.setYRot(prevYRot);
        entity.setXRot(prevXRot);
        entity.yHeadRot = prevHeadRot;
        entity.yHeadRotO = prevHeadRotO;//фонарик паращит
    }

    private void renderEntityInternal(GuiGraphics gui, int x, int y, int scale, Quaternionf rotation) {
        gui.pose().pushPose();
        gui.pose().translate(x, y, 1000);
        gui.pose().mulPoseMatrix(new Matrix4f().scaling(scale, scale, -scale));
        gui.pose().mulPose(rotation);

        Lighting.setupForEntityInInventory();
        RenderSystem.enableDepthTest();

        EntityRenderDispatcher dispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        dispatcher.overrideCameraOrientation(rotation.conjugate());
        dispatcher.setRenderShadow(false);

        dispatcher.render(entity, 0, 0, 0, 0, 1, gui.pose(), gui.bufferSource(), 0xF000F0);
        gui.flush();

        dispatcher.setRenderShadow(true);
        gui.pose().popPose();
        Lighting.setupFor3DItems();
    }

    public void setEntity(LivingEntity entity) {
        this.entity = entity;
    }
}