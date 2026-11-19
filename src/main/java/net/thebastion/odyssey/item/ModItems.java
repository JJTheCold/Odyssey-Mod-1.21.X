package net.thebastion.odyssey.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.entity.ModEntities;
import net.thebastion.odyssey.item.custom.*;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Odyssey.MODID);

    public static final DeferredItem<Item> CORE = ITEMS.register("core",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.EPIC)
                    .fireResistant()
            ));
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
    public static final DeferredItem<Item> USED_ANCIENT_RECIPE_BOOK = ITEMS.register("empty-ancients-manuscript",
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
    public static final DeferredItem<Item> ANCIENT_RECIPE_BOOK = ITEMS.register("ancients-manuscript",
            () -> new RecipeBook(new Item.Properties()));

    public static final DeferredItem<Item> DEPTH_FABRIC = ITEMS.register("depth-fabric",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WROUGHT_FABRIC = ITEMS.register("wrought-fabric",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<SwordItem> KNIGHTS_EDGE = ITEMS.register("knights-edge",
            () -> new SwordItem(ModToolTiers.HONORED, new Item.Properties()
                    .fireResistant()
                    .rarity(Rarity.RARE)
                    .component(DataComponents.TOOL, SwordItem.createToolProperties())

                    .attributes(SwordItem.createAttributes
                            (ModToolTiers.HONORED, 2, -2.2f))
            ));

    public static final DeferredItem<RighteousGuard> RIGHTEOUS_GUARD = ITEMS.register("righteous-guard",
            () -> new RighteousGuard(new Item.Properties()
                    .durability(3200)
                    .stacksTo(1)
                    .fireResistant()
                    .rarity(Rarity.EPIC)
            ));

    public static final DeferredItem<SwordItem> RIGHTEOUS_BLADE = ITEMS.register("righteous-blade",
            () -> new RighteousWeapon(ModToolTiers.RIGHTEOUS, new Item.Properties()
                    .fireResistant()
                    .rarity(Rarity.EPIC)
                    .component(DataComponents.TOOL, SwordItem.createToolProperties())

                    .attributes(SwordItem.createAttributes
                            (ModToolTiers.RIGHTEOUS, 3, -2.2f))
            ));

    public static final DeferredItem<PickaxeItem> GROUNDBREAKER = ITEMS.register("ground-breaker",
            () -> new GroundBreaker(ModToolTiers.RIGHTEOUS, new Item.Properties()
                    .fireResistant()
                    .rarity(Rarity.EPIC)
                    .durability(7200)
                    .attributes(SwordItem.createAttributes
                            (ModToolTiers.RIGHTEOUS, 2, -3f))
            ));
    public static final DeferredItem<AxeItem> HADEAN_GREATAXE = ITEMS.register("hadean-greataxe",
            () -> new HadeanWeapon(ModToolTiers.RIGHTEOUS, new Item.Properties()
                    .fireResistant()
                    .rarity(Rarity.EPIC)

                    .attributes(AxeItem.createAttributes
                            (ModToolTiers.RIGHTEOUS, 10, -3.2f))
            ));
    public static final DeferredItem<Item> GUIDED_SCYTHE = ITEMS.register("guided-scythe",
            () -> new GuidedWeapon(ModToolTiers.RIGHTEOUS,
                    new Item.Properties()
                            .fireResistant()
                            .rarity(Rarity.EPIC)
                            .durability(0)

                            .attributes(SwordItem.createAttributes
                                    (ModToolTiers.RIGHTEOUS, 5, -2.8f)
                            )

            ));
    public static final DeferredItem<Item> OCEAN_STAFF = ITEMS.register("staff-of-the-deep",
            () -> new SwordItem(ModToolTiers.RIGHTEOUS,
                    new Item.Properties()
                            .fireResistant()
                            .rarity(Rarity.EPIC)
                            .attributes(SwordItem.createAttributes
                                    (ModToolTiers.RIGHTEOUS, 2, -3.5f)
                            )

            ));
    public static final DeferredItem<SwordItem> HONORED_METAL_SWORD = ITEMS.register("honored-metal-sword",
            () -> new SwordItem(ModToolTiers.HONORED, new Item.Properties()
                    .fireResistant()
                    .component(DataComponents.TOOL, SwordItem.createToolProperties())

                    .attributes(SwordItem.createAttributes
                            (Tiers.DIAMOND, 3, -2.2f))
            ));

    public static final DeferredItem<Item> EYE_OF_THE_STORM = ITEMS.register("eye-of-the-storm",
            () -> new Item(new Item.Properties()
                    .rarity(Rarity.RARE)
            ));

    public static final DeferredItem<ArmorItem> CONQUEROR_HELMET = ITEMS.register("conqueror-helmet",
            () -> new ArmorItem(ModArmorMaterials.GUARDIAN_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(19))));

    public static final DeferredItem<ArmorItem> CONQUEROR_CHESTPLATE = ITEMS.register("conqueror-chestplate",
            () -> new ArmorItem(ModArmorMaterials.GUARDIAN_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(19))));

    public static final DeferredItem<ArmorItem> CONQUEROR_LEGGINGS = ITEMS.register("conqueror-leggings",
            () -> new ArmorItem(ModArmorMaterials.GUARDIAN_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(19))));

    public static final DeferredItem<ArmorItem> CONQUEROR_BOOTS = ITEMS.register("conqueror-boots",
            () -> new ArmorItem(ModArmorMaterials.GUARDIAN_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(19))));

    public static final DeferredItem<Item> HAN_D_SPAWNER = ITEMS.register("han-d-spawner",
            () -> new DeferredSpawnEggItem(ModEntities.HAN_D, 0x9b4b3c, 0x96b2a9,
            new Item.Properties()));

    public static final DeferredItem<Item> BURIED_SPAWNER = ITEMS.register("buried-spawner",
            () -> new DeferredSpawnEggItem(ModEntities.BURIED, 0x585250, 0xdcdcdc,
                    new Item.Properties()));

    public static final DeferredItem<Item> BURIED_LESSER_SPAWNER = ITEMS.register("buried-lesser-spawner",
            () -> new DeferredSpawnEggItem(ModEntities.BURIED_LESSER, 0x585250, 0x7f7f7f,
                    new Item.Properties()));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
