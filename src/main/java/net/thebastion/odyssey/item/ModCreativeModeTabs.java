package net.thebastion.odyssey.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Odyssey.MODID);

    public static final Supplier<CreativeModeTab> ODYSSEY_TAB =
            CREATIVE_MODE_TAB.register("tooltip.odyssey.hadean-greataxe", () ->
                    CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.RIGHTEOUS_BLADE.get()))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RIGHTEOUS_BLADE);
                        output.accept(ModItems.KNIGHTS_EDGE);
                        output.accept(ModItems.RIGHTEOUS_GUARD);

                        output.accept(ModItems.GUIDED_SCYTHE);
                        output.accept(ModItems.OCEAN_STAFF);

                        output.accept(ModItems.HADEAN_GREATAXE);
                        output.accept(ModItems.GROUNDBREAKER);

                        output.accept(ModItems.HONORED_METAL_SWORD);

                        output.accept(ModItems.CONQUEROR_HELMET);
                        output.accept(ModItems.CONQUEROR_CHESTPLATE);
                        output.accept(ModItems.CONQUEROR_LEGGINGS);
                        output.accept(ModItems.CONQUEROR_BOOTS);

                        output.accept(ModItems.ANCIENT_RECIPE_BOOK);
                        output.accept(ModItems.USED_ANCIENT_RECIPE_BOOK);
                        output.accept(ModItems.BURIED_SCHEMATIC);
                        output.accept(ModItems.DEPTH_FABRIC);
                        output.accept(ModItems.WROUGHT_FABRIC);
                        output.accept(ModItems.HONORED_METAL_FRAGMENT);
                        output.accept(ModItems.RIGHTEOUS_ALLOY);
                        output.accept(ModItems.HONORED_METAL);

                        output.accept(ModBlocks.GOLDLICHEN);
                        output.accept(ModBlocks.KRUXROCK);
                        output.accept(ModBlocks.HONORED_DEPOSIT);
                        output.accept(ModBlocks.POLISHED_KRUXROCK);
                        output.accept(ModBlocks.POLISHED_KRUXROCK_SLAB);
                        output.accept(ModBlocks.POLISHED_KRUXROCK_STAIRS);
                        output.accept(ModBlocks.POLISHED_KRUXROCK_WALL);
                        output.accept(ModBlocks.KRUXROCK_TILE);
                        output.accept(ModBlocks.KRUXROCK_TILE_SLAB);
                        output.accept(ModBlocks.KRUXROCK_TILE_STAIRS);
                        output.accept(ModBlocks.KRUXROCK_TILE_WALL);
                        output.accept(ModBlocks.KRUXROCK_BRICKS);
                        output.accept(ModBlocks.KRUXROCK_BRICK_SLAB);
                        output.accept(ModBlocks.KRUXROCK_BRICK_STAIRS);
                        output.accept(ModBlocks.KRUXROCK_BRICK_WALL);
                        output.accept(ModBlocks.KRUXROCK_PILLAR);
                        output.accept(ModBlocks.STUDDED_KRUXROCK_BRICKS);
                        output.accept(ModBlocks.STUDDED_KRUXROCK_PILLAR);
                        output.accept(ModBlocks.STUDDED_KRUXROCK_TILE);
                        output.accept(ModBlocks.BLOCK_OF_HONORED_METAL);
                        output.accept(ModBlocks.ANCIENTS_ALTAR);
                        output.accept(ModBlocks.PEDESTAL);
                        output.accept(ModBlocks.INSCRIBER);
                        output.accept(ModItems.BURIED_SPAWNER);
                        output.accept(ModItems.BURIED_LESSER_SPAWNER);
                    }).build());


    public static void register(IEventBus eventbus) {
        CREATIVE_MODE_TAB.register(eventbus);
    }
}
