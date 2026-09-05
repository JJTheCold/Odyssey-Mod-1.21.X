package net.thebastion.odyssey.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Odyssey.MODID);

    public static final DeferredBlock<Block> BLOCK_OF_HONORED_METAL = registerBlock("block-of-honored-metal",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(5f).requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK)
                    .mapColor(MapColor.COLOR_BROWN)
            ));

    public static final DeferredBlock<Block> KRUXROCK = registerBlock("kruxrock",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.ANCIENT_DEBRIS)
                    .mapColor(MapColor.COLOR_BROWN)
            ));
    public static final DeferredBlock<Block> HONORED_DEPOSIT = registerBlock("honored-deposit",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(5f).requiresCorrectToolForDrops().sound(SoundType.ANCIENT_DEBRIS)
                    .mapColor(MapColor.TERRACOTTA_WHITE)
            ));
    public static final DeferredBlock<Block> KRUXROCK_TILE = registerBlock("kruxrock-tile",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.ANCIENT_DEBRIS)
                    .mapColor(MapColor.COLOR_BROWN)
            ));
    public static final DeferredBlock<Block> KRUXROCK_BRICKS = registerBlock("kruxrock-bricks",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.ANCIENT_DEBRIS)
                    .mapColor(MapColor.COLOR_BROWN)
            ));
    public static final DeferredBlock<RotatedPillarBlock> KRUXROCK_PILLAR = registerBlock("kruxrock-pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.ANCIENT_DEBRIS)
                    .mapColor(MapColor.COLOR_BROWN)
            ));
    public static final DeferredBlock<Block> POLISHED_KRUXROCK = registerBlock("polished-kruxrock",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.ANCIENT_DEBRIS)
                    .mapColor(MapColor.COLOR_BROWN)
            ));
    public static final DeferredBlock<RotatedPillarBlock> STUDDED_KRUXROCK_PILLAR = registerBlock("studded-kruxrock-pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.ANCIENT_DEBRIS)
                    .mapColor(MapColor.TERRACOTTA_WHITE)
            ));
    public static final DeferredBlock<Block> STUDDED_KRUXROCK_TILE = registerBlock("studded-kruxrock-tile",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.ANCIENT_DEBRIS)
                    .mapColor(MapColor.TERRACOTTA_WHITE)
            ));

    public static final DeferredBlock<Block> STUDDED_KRUXROCK_BRICKS = registerBlock("studded-kruxrock-bricks",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3f).requiresCorrectToolForDrops().sound(SoundType.ANCIENT_DEBRIS)
                    .mapColor(MapColor.TERRACOTTA_WHITE)
            ));


    public static final DeferredBlock<WallBlock> KRUXROCK_TILE_WALL = registerBlock("kruxrock-tile-wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.ANCIENT_DEBRIS)
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(2f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<StairBlock> KRUXROCK_TILE_STAIRS = registerBlock("kruxrock-tile-stairs",
            () -> new StairBlock(ModBlocks.KRUXROCK_TILE.get().defaultBlockState(), BlockBehaviour.Properties.of()
                    .sound(SoundType.ANCIENT_DEBRIS)
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(2f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<SlabBlock> KRUXROCK_TILE_SLAB = registerBlock("kruxrock-tile-slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.ANCIENT_DEBRIS)
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(2f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<WallBlock> KRUXROCK_BRICK_WALL = registerBlock("kruxrock-brick-wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.ANCIENT_DEBRIS)
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(2f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<StairBlock> KRUXROCK_BRICK_STAIRS = registerBlock("kruxrock-brick-stairs",
            () -> new StairBlock(ModBlocks.KRUXROCK_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of()
                    .sound(SoundType.ANCIENT_DEBRIS)
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(2f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<SlabBlock> KRUXROCK_BRICK_SLAB = registerBlock("kruxrock-brick-slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.ANCIENT_DEBRIS)
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(2f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<WallBlock> POLISHED_KRUXROCK_WALL = registerBlock("polished-kruxrock-wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.ANCIENT_DEBRIS)
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(2f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<StairBlock> POLISHED_KRUXROCK_STAIRS = registerBlock("polished-kruxrock-stairs",
            () -> new StairBlock(ModBlocks.POLISHED_KRUXROCK.get().defaultBlockState(), BlockBehaviour.Properties.of()
                    .sound(SoundType.ANCIENT_DEBRIS)
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(2f).requiresCorrectToolForDrops()));
    public static final DeferredBlock<SlabBlock> POLISHED_KRUXROCK_SLAB = registerBlock("polished-kruxrock-slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.ANCIENT_DEBRIS)
                    .mapColor(MapColor.COLOR_BROWN)
                    .strength(2f).requiresCorrectToolForDrops()));

    public static final DeferredBlock<GlowLichenBlock> GOLDLICHEN = registerBlock("goldlichen",
            () -> new GlowLichenBlock(BlockBehaviour.Properties.of()
                    .strength(0.2f)
                    .lightLevel(GlowLichenBlock.emission(10))
                    .replaceable()
                    .noCollission()
                    .ignitedByLava()
                    .pushReaction(PushReaction.DESTROY)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GLOW_LICHEN)
                    .mapColor(MapColor.COLOR_YELLOW)
            ));

    private static <T extends Block> DeferredBlock<T> registerBlock (String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
