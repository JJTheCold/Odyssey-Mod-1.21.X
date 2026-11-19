package net.thebastion.odyssey.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.thebastion.odyssey.block.ModBlocks;
import net.thebastion.odyssey.item.ModItems;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.KRUXROCK.get());
        dropSelf(ModBlocks.KRUXROCK_TILE.get());
        dropSelf(ModBlocks.KRUXROCK_PILLAR.get());
        dropSelf(ModBlocks.KRUXROCK_BRICKS.get());
        dropSelf(ModBlocks.POLISHED_KRUXROCK.get());
        dropSelf(ModBlocks.STUDDED_KRUXROCK_TILE.get());
        dropSelf(ModBlocks.STUDDED_KRUXROCK_PILLAR.get());
        dropSelf(ModBlocks.STUDDED_KRUXROCK_BRICKS.get());
        dropSelf(ModBlocks.BLOCK_OF_HONORED_METAL.get());
        dropSelf(ModBlocks.GOLDLICHEN.get());

        dropSelf(ModBlocks.POLISHED_KRUXROCK_STAIRS.get());
        dropSelf(ModBlocks.KRUXROCK_BRICK_STAIRS.get());
        dropSelf(ModBlocks.KRUXROCK_TILE_STAIRS.get());
        dropSelf(ModBlocks.POLISHED_KRUXROCK_SLAB.get());
        dropSelf(ModBlocks.KRUXROCK_BRICK_SLAB.get());
        dropSelf(ModBlocks.KRUXROCK_TILE_SLAB.get());
        dropSelf(ModBlocks.POLISHED_KRUXROCK_WALL.get());
        dropSelf(ModBlocks.KRUXROCK_BRICK_WALL.get());
        dropSelf(ModBlocks.KRUXROCK_TILE_WALL.get());
        dropSelf(ModBlocks.ANCIENTS_ALTAR.get());
        dropSelf(ModBlocks.PEDESTAL.get());
        dropSelf(ModBlocks.INSCRIBER.get());

        add(ModBlocks.HONORED_DEPOSIT.get(),
                block -> createMultipleOreDrops(ModBlocks.HONORED_DEPOSIT.get(), ModItems.HONORED_METAL_FRAGMENT.get(), 2, 5));
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
