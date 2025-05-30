package org.koshakz.warhelper.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;

import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.koshakz.warhelper.WarHelper;
import org.koshakz.warhelper.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, WarHelper.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        //this.tag(BlockTags.GUARDED_BY_PIGLINS).add(ModBlocks.TEST_BLOCK.get());
        //this.tag(BlockTags.DIRT).add(ModBlocks.TEST_BLOCK.get());
        //this.tag(BlockTags.INFINIBURN_NETHER).add(ModBlocks.TEST_BLOCK.get());
        //this.tag(BlockTags.PORTALS).add(ModBlocks.TEST_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.TEST_BLOCK.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.TEST_BLOCK.get());

    }
}
