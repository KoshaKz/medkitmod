package org.koshakz.medkitmod.utils;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import org.koshakz.medkitmod.item.custom.Bandages;

import java.nio.file.Path;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModConfigHandler {
    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec COMMON_CONFIG;

    // Пример переменной конфига
    public static ForgeConfigSpec.BooleanValue ENABLE_FEATURE;
    public static ForgeConfigSpec.IntValue MAX_VALUE;
    public static ForgeConfigSpec.IntValue USE_DURATION_CONFIG;
    public static ForgeConfigSpec.IntValue PROGRESS_BAR_LEN_CONFIG;
    public static ForgeConfigSpec.IntValue HEAL_OTHER_OFFSET_CONFIG;
    public static ForgeConfigSpec.DoubleValue HEAL_RANGE_CONFIG;


    static {
        USE_DURATION_CONFIG = COMMON_BUILDER
                .comment("уххухй")
                .defineInRange("UseDuration", 60, 0, Integer.MAX_VALUE);

        PROGRESS_BAR_LEN_CONFIG = COMMON_BUILDER
                .comment("dsaf")
                .defineInRange("ProgressBarLen", 20, 0, Integer.MAX_VALUE);

        HEAL_OTHER_OFFSET_CONFIG = COMMON_BUILDER
                .comment("dsaf")
                .defineInRange("HealOtherOffset", 20, 0, Integer.MAX_VALUE);

        HEAL_RANGE_CONFIG = COMMON_BUILDER
                .comment("123")
                .defineInRange("HealRange", 1.5F, 0, Float.MAX_VALUE);

        //ENABLE_FEATURE = COMMON_BUILDER
        //        .comment("Включить ли эту фичу?")
        //        .define("enableFeature", true);

        //MAX_VALUE = COMMON_BUILDER
        //        .comment("Максимальное значение")
        //        .defineInRange("maxValue", 10, 1, 100);

        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    public static void loadConfig(ForgeConfigSpec config, Path path) {
        final CommentedFileConfig fileConfig = CommentedFileConfig.builder(path).sync().autosave().build();
        fileConfig.load();
        config.setConfig(fileConfig);
    }
    public static boolean load(){
        Bandages.USE_DURATION = USE_DURATION_CONFIG.get();
        Bandages.PROGRESS_BAR_LEN = USE_DURATION_CONFIG.get();
        Bandages.HEAL_OTHER_OFFSET = USE_DURATION_CONFIG.get() + 1;
        Bandages.HEAL_RANGE = HEAL_RANGE_CONFIG.get();

        return true;
    }
}