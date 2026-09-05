package net.thebastion.odyssey.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.block.ModBlocks;

public class  ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Odyssey.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        wallBlock(ModBlocks.KRUXROCK_TILE_WALL.get(), blockTexture(ModBlocks.KRUXROCK_TILE.get()));
        stairsBlock(ModBlocks.KRUXROCK_TILE_STAIRS.get(), blockTexture(ModBlocks.KRUXROCK_TILE.get()));
        slabBlock(ModBlocks.KRUXROCK_TILE_SLAB.get(), blockTexture(ModBlocks.KRUXROCK_TILE.get()), blockTexture(ModBlocks.KRUXROCK_TILE.get()));

        wallBlock(ModBlocks.KRUXROCK_BRICK_WALL.get(), blockTexture(ModBlocks.KRUXROCK_BRICKS.get()));
        stairsBlock(ModBlocks.KRUXROCK_BRICK_STAIRS.get(), blockTexture(ModBlocks.KRUXROCK_BRICKS.get()));
        slabBlock(ModBlocks.KRUXROCK_BRICK_SLAB.get(), blockTexture(ModBlocks.KRUXROCK_BRICKS.get()), blockTexture(ModBlocks.KRUXROCK_BRICKS.get()));

        wallBlock(ModBlocks.POLISHED_KRUXROCK_WALL.get(), blockTexture(ModBlocks.POLISHED_KRUXROCK.get()));
        stairsBlock(ModBlocks.POLISHED_KRUXROCK_STAIRS.get(), blockTexture(ModBlocks.POLISHED_KRUXROCK.get()));
        slabBlock(ModBlocks.POLISHED_KRUXROCK_SLAB.get(), blockTexture(ModBlocks.POLISHED_KRUXROCK.get()), blockTexture(ModBlocks.POLISHED_KRUXROCK.get()));

        blockItem(ModBlocks.KRUXROCK_TILE_STAIRS);
        blockItem(ModBlocks.KRUXROCK_TILE_WALL);
        blockItem(ModBlocks.POLISHED_KRUXROCK_STAIRS);
        blockItem(ModBlocks.POLISHED_KRUXROCK_WALL);
        blockItem(ModBlocks.KRUXROCK_BRICK_STAIRS);
        blockItem(ModBlocks.KRUXROCK_BRICK_WALL);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("odyssey:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("odyssey:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
