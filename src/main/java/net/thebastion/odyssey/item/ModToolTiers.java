package net.thebastion.odyssey.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.common.Tags;
import net.thebastion.odyssey.util.ModTags;

public class ModToolTiers {
    public static final Tier RIGHTEOUS = new SimpleTier(ModTags.Blocks.NEEDS_HONORED_TOOL,
            4800, 12f, 5f, 28, () -> Ingredient.of(ModItems.RIGHTEOUS_ALLOY));

    public static final Tier HONORED = new SimpleTier(BlockTags.NEEDS_DIAMOND_TOOL,
            1600, 8f, 3f, 12, () -> Ingredient.of(ModItems.HONORED_METAL));
}
