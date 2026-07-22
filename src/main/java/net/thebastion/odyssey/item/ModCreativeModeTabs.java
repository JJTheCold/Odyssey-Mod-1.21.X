package net.thebastion.odyssey.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thebastion.odyssey.Odyssey;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Odyssey.MODID);

    public static final Supplier<CreativeModeTab> ODYSSEY_TAB =
            CREATIVE_MODE_TAB.register("odyssey", () ->
                    CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.RIGHTEOUS_BLADE.get()))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RIGHTEOUS_BLADE);
                        output.accept(ModItems.RIGHTEOUS_GUARD);
                        output.accept(ModItems.HONORED_METAL_SWORD);
                        output.accept(ModItems.ANCIENT_RECIPE_BOOK);
                        output.accept(ModItems.USED_ANCIENT_RECIPE_BOOK);
                        output.accept(ModItems.BURIED_SCHEMATIC);
                        output.accept(ModItems.IMBUED_FABRIC);
                        output.accept(ModItems.HONORED_METAL_FRAGMENT);
                        output.accept(ModItems.RIGHTEOUS_ALLOY);
                        output.accept(ModItems.HONORED_METAL);
                    }).build());


    public static void register(IEventBus eventbus) {
        CREATIVE_MODE_TAB.register(eventbus);
    }
}
