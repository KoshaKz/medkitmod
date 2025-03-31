package org.koshakz.medkitmod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.io.Console;
import java.util.List;

public class Bandage extends Item {
    private static final int USE_DURATION = 60;
    public Bandage(Properties pProperties) {
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


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResultHolder.consume(player.getItemInHand(hand));
    }

    @Override
    public void onUseTick(Level level, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (user instanceof ServerPlayer player) {
            LivingEntity entity = getEntityPlayerIsLookingAt(player, 1);
            if (entity == null) {
                sendActionBar(player,"");
                player.stopUsingItem();
                return;
            }
            if (remainingUseTicks == 1) {
                entity.heal(10);
                stack.setCount(stack.getCount() - 1);
            }
            sendActionBar(player, "Progress: " + (int) ((USE_DURATION - remainingUseTicks) / (float) USE_DURATION * 100) + "%");
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
        Component component = Component.literal(message);
        ClientboundSetActionBarTextPacket packet = new ClientboundSetActionBarTextPacket(component);
        player.connection.send(packet);
    }
}
