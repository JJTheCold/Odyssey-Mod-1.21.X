package net.thebastion.odyssey.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.block.ModBlocks;
import net.thebastion.odyssey.block.entity.AncientsAltarEntity;
import net.thebastion.odyssey.block.entity.PedestalEntity;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Odyssey.MODID);

    public static final Supplier<BlockEntityType<AncientsAltarEntity>> ALTER_BE =
            BLOCK_ENTITIES.register("altar_be", () -> BlockEntityType.Builder.of(
                    AncientsAltarEntity::new, ModBlocks.ANCIENTS_ALTAR.get()).build(null));
    public static final Supplier<BlockEntityType<PedestalEntity>> PEDESTAL_BE =
            BLOCK_ENTITIES.register("pedestal_be", () -> BlockEntityType.Builder.of(
                    PedestalEntity::new, ModBlocks.PEDESTAL.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
