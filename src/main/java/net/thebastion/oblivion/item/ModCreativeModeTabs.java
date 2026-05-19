package net.thebastion.oblivion.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thebastion.oblivion.Oblivion;
import net.thebastion.oblivion.block.ModBlocks;
import net.thebastion.oblivion.block.custom.CobaltCrucible;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Oblivion.MODID);

    public static final Supplier<CreativeModeTab> OBLIVION_TAB = CREATIVE_MODE_TAB.register("creativetab.oblivion.oblivion-tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.FORGE_READY_COBALT.get()))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RAW_COBALT);
                        output.accept(ModItems.COBALT_INGOT);
                        output.accept(ModItems.FORGE_READY_COBALT);

                        output.accept(ModBlocks.COBALT_ORE);
                        output.accept(ModBlocks.DEEPSLATE_COBALT_ORE);
                        output.accept(ModBlocks.RAW_COBALT_BLOCK);
                        output.accept(ModBlocks.COBALT_BLOCK);
                        output.accept(ModBlocks.COBALT_CRUCIBLE);
                    }).build());


    public static void register(IEventBus eventbus) {
        CREATIVE_MODE_TAB.register(eventbus);
    }
}
