package org.koshakz.medkitmod.item.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import org.jetbrains.annotations.NotNull;
import org.koshakz.medkitmod.utils.PlayerUtils;

import java.util.List;


public class Bandages extends Item {

    public static int USE_DURATION;
    public static int PROGRESS_BAR_LEN;
    public static int HEAL_OTHER_OFFSET;
    public static double HEAL_RANGE;
    public static int HEAL_MEDIC_OFFSET;
    public Bandages(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return USE_DURATION;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
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
    public void releaseUsing(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity entity, int timeCharged) {
        if (!(entity instanceof ServerPlayer player)) return;

        player.sendSystemMessage(net.minecraft.network.chat.Component.literal("Нет"));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        if (player.isShiftKeyDown() && getEntityPlayerIsLookingAt(player, HEAL_RANGE) == null) {
            return InteractionResultHolder.fail(player.getItemInHand(hand)) ;
        }
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    @Override
    public void onUseTick(@NotNull Level level, @NotNull LivingEntity user, @NotNull ItemStack stack, int remainingUseTicks) {
        if (!(user instanceof ServerPlayer player)) return;

        if (remainingUseTicks == USE_DURATION) {
            setUseWithShift(player, player.isShiftKeyDown());
        }

        if (player.isShiftKeyDown() != getUseWithShift(player)) {
            sendActionBar(player,"");
            player.stopUsingItem();
            return;
        }

        if (player.isShiftKeyDown()) {
            if (PlayerUtils.HasTag(player, "medic")) {
                MedicOtherHeal(player, stack, remainingUseTicks); // Усиленное лечение для медиков
            } else {
                OtherHeal(player, stack, remainingUseTicks); // Обычное лечение для всех
            }
        } else {
            SelfHeal(player, stack, remainingUseTicks); // Всегда обычная скорость
        }
    }

    private static void SelfHeal(ServerPlayer player, ItemStack stack, int remainingTicks) {
        if (remainingTicks == 1) {
            player.heal(10);
            stack.setCount(stack.getCount() - 1);
            sendActionBar(player, "");
            return;
        }
        renderProgressBar(player, (float) remainingTicks / USE_DURATION);
    }

    private static void MedicOtherHeal(ServerPlayer player, ItemStack stack, int remainingTicks) {
        LivingEntity entity = getEntityPlayerIsLookingAt(player, HEAL_RANGE);
        if (entity == null) {
            sendActionBar(player,"");
            player.stopUsingItem();
            return;
        }
        if (remainingTicks == HEAL_MEDIC_OFFSET) {
            entity.heal(10);
            stack.setCount(stack.getCount() - 1);
            player.stopUsingItem();
            sendActionBar(player, "");
            return;
        }
        renderProgressBar(player, (float) (remainingTicks - HEAL_MEDIC_OFFSET) / (float) (USE_DURATION - HEAL_MEDIC_OFFSET));

    }

    private static void OtherHeal(ServerPlayer player, ItemStack stack, int remainingTicks) {
        LivingEntity entity = getEntityPlayerIsLookingAt(player, HEAL_RANGE);
        if (entity == null) {
            sendActionBar(player,"");
            player.stopUsingItem();
            return;
        }
        if (remainingTicks == HEAL_OTHER_OFFSET) {
            entity.heal(10);
            stack.setCount(stack.getCount() - 1);
            player.stopUsingItem();
            sendActionBar(player, "");
            return;
        }
        renderProgressBar(player, (float) (remainingTicks - HEAL_OTHER_OFFSET) / (float) (USE_DURATION - HEAL_OTHER_OFFSET));

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
