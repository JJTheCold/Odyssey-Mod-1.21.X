package net.thebastion.odyssey.registries;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.loot.AppendLootModifier;
import net.thebastion.odyssey.loot.ReplaceLootModifier;

import java.util.function.Supplier;


public class LootRegistry {
    public static final DeferredRegister<LootItemFunctionType<?>> LOOT_FUNCTIONS = DeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, Odyssey.MODID);
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Odyssey.MODID);

    public static void register(IEventBus eventBus) {
        LOOT_FUNCTIONS.register(eventBus);
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> APPEND_LOOT_MODIFIER = LOOT_MODIFIER_SERIALIZERS.register("append_loot", AppendLootModifier.CODEC);
    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> REPLACE_LOOT_MODIFIER = LOOT_MODIFIER_SERIALIZERS.register("replace_loot", ReplaceLootModifier.CODEC);
}