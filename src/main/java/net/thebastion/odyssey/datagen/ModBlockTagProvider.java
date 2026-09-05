package net.thebastion.odyssey.datagen;

import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.block.ModBlocks;
import net.thebastion.odyssey.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Odyssey.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.KRUXROCK.get())
                .add(ModBlocks.KRUXROCK_BRICKS.get())
                .add(ModBlocks.KRUXROCK_PILLAR.get())
                .add(ModBlocks.KRUXROCK_TILE.get())
                .add(ModBlocks.BLOCK_OF_HONORED_METAL.get())
                .add(ModBlocks.STUDDED_KRUXROCK_PILLAR.get())
                .add(ModBlocks.STUDDED_KRUXROCK_BRICKS.get())
                .add(ModBlocks.STUDDED_KRUXROCK_TILE.get())
                .add(ModBlocks.HONORED_DEPOSIT.get())
                .add(ModBlocks.POLISHED_KRUXROCK.get()
                );

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.KRUXROCK.get())
                .add(ModBlocks.KRUXROCK_BRICKS.get())
                .add(ModBlocks.KRUXROCK_PILLAR.get())
                .add(ModBlocks.KRUXROCK_TILE.get())
                .add(ModBlocks.POLISHED_KRUXROCK.get()
                );

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BLOCK_OF_HONORED_METAL.get())
                .add(ModBlocks.STUDDED_KRUXROCK_PILLAR.get())
                .add(ModBlocks.STUDDED_KRUXROCK_BRICKS.get())
                .add(ModBlocks.HONORED_DEPOSIT.get())
                .add(ModBlocks.STUDDED_KRUXROCK_TILE.get()
                );

        tag(BlockTags.BASE_STONE_OVERWORLD)
                .add(ModBlocks.KRUXROCK.get()
                );

        tag(ModTags.Blocks.KRUX_REPLACEABLE)
                .add(ModBlocks.KRUXROCK.get()
                );

        tag(ModTags.Blocks.IN_STONE)
                .addTag(Tags.Blocks.STONES)
                .add(Blocks.STONE)
                .add(Blocks.DEEPSLATE);

        tag(BlockTags.WALLS)
                .add(ModBlocks.POLISHED_KRUXROCK_WALL.get())
                .add(ModBlocks.KRUXROCK_TILE_WALL.get())
                .add(ModBlocks.KRUXROCK_BRICK_WALL.get()
                );

        tag(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(ModTags.Blocks.NEEDS_HONORED_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_HONORED_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .remove(ModTags.Blocks.NEEDS_HONORED_TOOL);
    }
}