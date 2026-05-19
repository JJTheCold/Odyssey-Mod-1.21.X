package net.thebastion.oblivion.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thebastion.oblivion.Oblivion;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Oblivion.MODID);

    public static final DeferredItem<Item> RAW_COBALT = ITEMS.register("raw-cobalt",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FORGE_READY_COBALT = ITEMS.register("forge-cobalt",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> COBALT_INGOT = ITEMS.register("cobalt-ingot",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
