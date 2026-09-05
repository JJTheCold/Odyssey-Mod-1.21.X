package net.thebastion.odyssey.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.item.ModItems;
import net.thebastion.odyssey.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Odyssey.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.ANCIENT_SCHEMATIC)
                .add(ModItems.BURIED_SCHEMATIC.get())
                .add(ModItems.ANCIENT_RECIPE_BOOK.get());

        tag(ModTags.Items.RECIPE_BOOK_FIX)
                .add(ModItems.ANCIENT_RECIPE_BOOK.get());
    }
}