package net.thebastion.oblivion.block;

import it.unimi.dsi.fastutil.objects.Object2BooleanLinkedOpenCustomHashMap;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thebastion.oblivion.Oblivion;
import net.thebastion.oblivion.block.custom.CobaltCrucible;
import net.thebastion.oblivion.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Oblivion.MODID);

    public static final DeferredBlock<Block> COBALT_BLOCK = registerBlock("cobalt-block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(5f).requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK)
            ));

    public static final DeferredBlock<Block> RAW_COBALT_BLOCK = registerBlock("raw-cobalt-block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.ANCIENT_DEBRIS)
            ));

    public static final DeferredBlock<Block> COBALT_CRUCIBLE = registerBlock("cobalt-crucible",
            () -> new CobaltCrucible(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK)
            ));

    public static final DeferredBlock<Block> COBALT_ORE = registerBlock("cobalt-ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 6),
                    BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)
            ));

    public static final DeferredBlock<Block> DEEPSLATE_COBALT_ORE = registerBlock("deepslate-cobalt-ore",
            () -> new DropExperienceBlock(UniformInt.of(4, 8),
                    BlockBehaviour.Properties.of()
                            .strength(5f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)
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
