package org.koshakz.medkitmod.utils;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import org.koshakz.medkitmod.item.custom.Bandages;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModConfigHandler {
    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec COMMON_CONFIG;

    // Пример переменной конфига

    public static ForgeConfigSpec.IntValue USE_DURATION_CONFIG;
    public static ForgeConfigSpec.IntValue PROGRESS_BAR_LEN_CONFIG;
    public static ForgeConfigSpec.IntValue HEAL_OTHER_OFFSET_CONFIG;
    public static ForgeConfigSpec.DoubleValue HEAL_RANGE_CONFIG;
    public static ForgeConfigSpec.IntValue HEAL_MEDIC_OFFSET_CONFIG;


    static {
        USE_DURATION_CONFIG = COMMON_BUILDER
                .comment("Длительность использования в тиках")
                .defineInRange("UseDuration", 60, 0, Integer.MAX_VALUE);

        PROGRESS_BAR_LEN_CONFIG = COMMON_BUILDER
                .comment("Длина полоски прогресса в палочках (|||||)")
                .defineInRange("ProgressBarLen", 40, 0, Integer.MAX_VALUE);

        HEAL_OTHER_OFFSET_CONFIG = COMMON_BUILDER
                .comment("На сколько быстрее лечить других в тиках")
                .defineInRange("HealOtherOffset", 20, 0, Integer.MAX_VALUE);

        HEAL_MEDIC_OFFSET_CONFIG = COMMON_BUILDER
                .comment("На сколько быстрее лечит медик других в тиках (HealOtherOffset + это значение)")
                .defineInRange("MedicHealOffset", 20, 0, Integer.MAX_VALUE);

        HEAL_RANGE_CONFIG = COMMON_BUILDER
                .comment("Максимальная дистанция лечения в блоках")
                .defineInRange("HealRange", 1.5F, 0, Float.MAX_VALUE);

        //ENABLE_FEATURE = COMMON_BUILDER
        //        .comment("Включить ли эту фичу?")
        //        .define("enableFeature", true);

        //MAX_VALUE = COMMON_BUILDER
        //        .comment("Максимальное значение")
        //        .defineInRange("maxValue", 10, 1, 100);

        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    //public static void loadConfig(ForgeConfigSpec config, Path path) {
    //    final CommentedFileConfig fileConfig = CommentedFileConfig.builder(path).sync().autosave().build();
    //    fileConfig.load();
    //    config.setConfig(fileConfig);
    //}
    public static boolean load(){
        if (USE_DURATION_CONFIG.get() <= HEAL_OTHER_OFFSET_CONFIG.get()){
            return false;
        }
        if (USE_DURATION_CONFIG.get() <= HEAL_MEDIC_OFFSET_CONFIG.get() + HEAL_OTHER_OFFSET_CONFIG.get()){
            return false;
        }

        Bandages.USE_DURATION = USE_DURATION_CONFIG.get();
        Bandages.PROGRESS_BAR_LEN = PROGRESS_BAR_LEN_CONFIG.get();
        Bandages.HEAL_OTHER_OFFSET = HEAL_OTHER_OFFSET_CONFIG.get() + 1;
        Bandages.HEAL_RANGE = HEAL_RANGE_CONFIG.get();
        Bandages.HEAL_MEDIC_OFFSET = HEAL_MEDIC_OFFSET_CONFIG.get() + HEAL_OTHER_OFFSET_CONFIG.get() + 1;

        return true;
    }

    public static void change(String name, int value) {
        COMMON_CONFIG.getSpec().valueMap().get(name);
    }
}