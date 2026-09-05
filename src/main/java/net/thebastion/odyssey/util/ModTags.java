package net.thebastion.odyssey.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.thebastion.odyssey.Odyssey;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_HONORED_TOOL = createTag("needs_honored_metal_tool");
        public static final TagKey<Block> NEEDS_RIGHTEOUS_TOOL = createTag("needs_righteous_tool");
        public static final TagKey<Block> INCORRECT_FOR_HONORED_TOOL = createTag("needs_honored_metal_tool");
        public static final TagKey<Block> KRUX_REPLACEABLE = createTag("kruxrock-replaceable");
        public static final TagKey<Block> IN_STONE = createTag("in-stone");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Odyssey.MODID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");
        public static final TagKey<Item> ANCIENT_SCHEMATIC = createTag("ancient-schematic");
        public static final TagKey<Item> RECIPE_BOOK_FIX = createTag("recipe-book-fix");

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Odyssey.MODID, name));
        }
    }
}