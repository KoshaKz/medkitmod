package org.koshakz.medkitmod.utils;

import net.minecraft.world.entity.LivingEntity;

public class PlayerUtils {
    public static boolean HasTag(LivingEntity entity, String tag) {
        return entity.getTags().contains(tag);
    }
}
