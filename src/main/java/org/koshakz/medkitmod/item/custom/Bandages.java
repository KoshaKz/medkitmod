package org.koshakz.medkitmod.item.custom;

import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class Bandages extends Item {
    private static final int USE_DURATION = 60;
    private static final int PROGRESS_BAR_LEN = 20;
    private static final int HEAL_OTHER_OFFSET = 21;
    public Bandages(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return USE_DURATION;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    private static boolean getUseWithShift(Player player) {
        CompoundTag tag = player.getPersistentData();
        return tag.getBoolean("useBandagesWithShift");
    }

    private static void setUseWithShift(Player player, boolean value) {
        CompoundTag tag = player.getPersistentData();
        tag.putBoolean("useBandagesWithShift", value);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (player.isShiftKeyDown() && getEntityPlayerIsLookingAt(player, 1.5) == null) {
            return InteractionResultHolder.fail(player.getItemInHand(hand)) ;
        }
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    @Override
    public void onUseTick(Level level, LivingEntity user, ItemStack stack, int remainingUseTicks) {

        if (!(user instanceof ServerPlayer player)) return; //Проверка что действия прозходят на сервере

        if (remainingUseTicks == USE_DURATION) {            //Сохраняет что игрок будет лечить себя/других
            setUseWithShift(player, player.isShiftKeyDown());
        }

        if (player.isShiftKeyDown() != getUseWithShift(player)) { //Отменяем использование если игрок поменял действие
            sendActionBar(player,"");
            player.stopUsingItem();
            return;
        }

        if (player.isShiftKeyDown()) {                             // Main пиздец
            LivingEntity entity = getEntityPlayerIsLookingAt(player, 1.5);
            if (entity == null) {
                sendActionBar(player,"");
                player.stopUsingItem();
                return;
            }
            if (remainingUseTicks == HEAL_OTHER_OFFSET) {
                entity.heal(10);
                stack.setCount(stack.getCount() - 1);
                player.stopUsingItem();
            }
            renderProgressBar(player, (float) (remainingUseTicks - HEAL_OTHER_OFFSET) / (float) (USE_DURATION - HEAL_OTHER_OFFSET));

        } else {

            if (remainingUseTicks == 1) {
                player.heal(10);
                stack.setCount(stack.getCount() - 1);
            }
            renderProgressBar(player, (float) remainingUseTicks / USE_DURATION);
        }
    }

    public static LivingEntity getEntityPlayerIsLookingAt(Player player, double distance) {
        Vec3 eyePosition = player.getEyePosition(1.0F);
        Vec3 lookVector = player.getLookAngle();
        Vec3 reachVector = eyePosition.add(lookVector.scale(distance));
        Level level = player.level();

        AABB aabb = player.getBoundingBox().expandTowards(lookVector.scale(distance)).inflate(1.0D, 1.0D, 1.0D);
        List<Entity> entities = level.getEntities(player, aabb, e -> !e.isSpectator() && e instanceof LivingEntity);

        LivingEntity closestEntity = null;
        double closestDistance = distance * distance;

        for (Entity entity : entities) {
            AABB entityAABB = entity.getBoundingBox().inflate(0.3);
            var optionalHit = entityAABB.clip(eyePosition, reachVector);

            if (optionalHit.isPresent()) {
                double distToHit = eyePosition.distanceToSqr(optionalHit.get());
                if (distToHit < closestDistance) {
                    closestDistance = distToHit;
                    closestEntity = (LivingEntity) entity;
                }
            }
        }

        return closestEntity;
    }

    public static void sendActionBar(ServerPlayer player, String message) {
        player.connection.send(new ClientboundSetActionBarTextPacket(Component.literal(message)));
    }

    private static void renderProgressBar(ServerPlayer player, float percent) {
        int da = (int) (PROGRESS_BAR_LEN * percent);
        sendActionBar(player, "§7[§a" + "|".repeat(PROGRESS_BAR_LEN - da) + "§7" + "|".repeat(da) + "§7]");
    }
}
