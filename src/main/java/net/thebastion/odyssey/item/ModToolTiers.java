package net.thebastion.odyssey.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.common.Tags;

public class ModToolTiers {
    public static final Tier RIGHTEOUS = new SimpleTier(Tags.Blocks.NEEDS_NETHERITE_TOOL,
            2400, 10f, 5f, 24, () -> Ingredient.of(ModItems.RIGHTEOUS_ALLOY));

    public static final Tier HONORED = new SimpleTier(BlockTags.NEEDS_DIAMOND_TOOL,
            1200, 10f, 3f, 12, () -> Ingredient.of(ModItems.HONORED_METAL));
}
