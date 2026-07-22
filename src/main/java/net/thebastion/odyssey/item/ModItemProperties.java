package net.thebastion.odyssey.item;


import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        makeCustomShield(ModItems.RIGHTEOUS_GUARD.get());
    }

    private static void makeCustomShield(Item item) {
        ItemProperties.register(
                item,
                ResourceLocation.withDefaultNamespace("blocking"),
                (p_174575_, p_174576_, p_174577_, p_174578_) -> p_174577_ != null && p_174577_.isUsingItem() && p_174577_.getUseItem() == p_174575_ ? 1.0F : 0.0F
        );
    }
}