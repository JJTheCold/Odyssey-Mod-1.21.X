package net.thebastion.odyssey.item.Items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class RecipeBook extends Item {
    public RecipeBook(Properties properties) {
        super(new Properties()
                .rarity(Rarity.EPIC)
                .fireResistant()
                .stacksTo(1)
        );
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return stack.getDamageValue() < stack.getMaxDamage();
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return itemStack.copy();
    }
}
