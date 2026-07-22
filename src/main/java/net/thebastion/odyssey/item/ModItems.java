package net.thebastion.odyssey.item;

import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.entity.ModEntities;
import net.thebastion.odyssey.item.Items.RecipeBook;
import net.thebastion.odyssey.item.Items.RighteousGuard;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Odyssey.MODID);
    //Odyssey

    public static final DeferredItem<Item> RIGHTEOUS_ALLOY = ITEMS.register("righteous-alloy",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.EPIC)
                    .fireResistant()
            ));
    public static final DeferredItem<Item> HONORED_METAL = ITEMS.register("honored-metal",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.RARE)
            ));
    public static final DeferredItem<Item> HONORED_METAL_FRAGMENT = ITEMS.register("honored-metal-fragment",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.RARE)
            ));
    public static final DeferredItem<Item> USED_ANCIENT_RECIPE_BOOK = ITEMS.register("empty-ancient-recipe-book",
            () -> new Item(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.RARE)
                    .fireResistant()
            ));
    public static final DeferredItem<Item> BURIED_SCHEMATIC = ITEMS.register("buried-schematic",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.UNCOMMON)
                    .fireResistant()
            ));
    public static final DeferredItem<Item> ANCIENT_RECIPE_BOOK = ITEMS.register("ancient-recipe-book",
            () -> new RecipeBook(new Item.Properties()));

    public static final DeferredItem<Item> IMBUED_FABRIC = ITEMS.register("imbued-fabric",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<RighteousGuard> RIGHTEOUS_GUARD = ITEMS.register("righteous-guard",
            () -> new RighteousGuard(new Item.Properties()
                    .durability(2400)
                    .fireResistant()
                    .rarity(Rarity.EPIC)
            ));
    public static final DeferredItem<SwordItem> RIGHTEOUS_BLADE = ITEMS.register("righteous-blade",
            () -> new SwordItem(ModToolTiers.RIGHTEOUS, new Item.Properties()
                    .fireResistant()
                    .rarity(Rarity.EPIC)

                    .attributes(SwordItem.createAttributes
                            (ModToolTiers.RIGHTEOUS, 3, -2.2f))
            ));
    public static final DeferredItem<SwordItem> HONORED_METAL_SWORD = ITEMS.register("honored-metal-sword",
            () -> new SwordItem(ModToolTiers.HONORED, new Item.Properties()
                    .fireResistant()
                    .rarity(Rarity.RARE)

                    .attributes(SwordItem.createAttributes
                            (Tiers.DIAMOND, 3, -2.2f))
            ));
/*/
    public static final DeferredItem<ArmorItem> COBALT_HELMET = ITEMS.register("cobalt-helmet",
            () -> new ArmorItem(ModArmorMaterials.COBALT_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(19))));
    public static final DeferredItem<ArmorItem> COBALT_CHESTPLATE = ITEMS.register("cobalt-chestplate",
            () -> new ArmorItem(ModArmorMaterials.COBALT_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(19))));
    public static final DeferredItem<ArmorItem> COBALT_LEGGINGS = ITEMS.register("cobalt-leggings",
            () -> new ArmorItem(ModArmorMaterials.COBALT_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(19))));
    public static final DeferredItem<ArmorItem> COBALT_BOOTS = ITEMS.register("cobalt-boots",
            () -> new ArmorItem(ModArmorMaterials.COBALT_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(19))));
/*/
    public static final DeferredItem<Item> HAN_D_SPAWNER = ITEMS.register("han-d-spawner",
            () -> new DeferredSpawnEggItem(ModEntities.HAN_D, 0x9b4b3c, 0x96b2a9,
            new Item.Properties()));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
