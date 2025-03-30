package org.koshakz.medkitmod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.io.Console;

public class Bandage extends Item {
    private static final int USE_DURATION = 100;
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
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {
        pPlayer.sendSystemMessage(Component.literal("da "+ pPlayer + " " + pInteractionTarget.getName()));
        return InteractionResult.SUCCESS;
    }

    @Override
    public void onUseTick(Level level, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (level.isClientSide) return;
        if (!(user instanceof Player player)) return;

        int usedTicks = getUseDuration(stack) - remainingUseTicks;
        if (usedTicks == USE_DURATION) {
            if (!level.isClientSide) {
                HitResult hitResult = getPlayerPOVHitResult(level, player, 64);
                if (hitResult instanceof EntityHitResult entityHit && entityHit.getEntity() instanceof Player targetPlayer) {
                    targetPlayer.heal(6.0F); // Лечит 3 сердца
                    level.playSound(null, targetPlayer.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1.0F, 1.0F);
                    player.displayClientMessage(Component.literal("Вы вылечили игрока: " + targetPlayer.getName().getString()), true);
                } else {
                    player.displayClientMessage(Component.literal("Нет игрока в прицеле"), true);
                }
            }
        }
    }

    private HitResult getPlayerPOVHitResult(Level level, Player player, double distance) {
        Vec3 eyePos = player.getEyePosition(1.0F);
        Vec3 lookVec = player.getViewVector(1.0F).scale(distance);
        Vec3 end = eyePos.add(lookVec);
        return player.pick(distance, 1.0F, false);
    }
}
