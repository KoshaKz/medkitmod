package org.koshakz.medkitmod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Bandage extends Item {
    private static final int USE_DURATION = 100;
    public Bandage(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public void onUseTick(Level level, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (level.isClientSide) return;
        if (!(user instanceof Player player)) return;
        player.sendSystemMessage(Component.literal("пиздец"));
        int usedTicks = getUseDuration(stack) - remainingUseTicks;
        if (usedTicks == USE_DURATION && !level.isClientSide) {
            Player target = (Player) user;
            //Player target = findPlayerInSight(player, 4.5D); // 4.5 блока перед собой

            target.heal(6.0F);
            level.playSound(null, target.blockPosition(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1.0F, 1.0F);
            player.displayClientMessage(Component.literal("Вы вылечили игрока: " + target.getName().getString()), true);

        }
    }
}
