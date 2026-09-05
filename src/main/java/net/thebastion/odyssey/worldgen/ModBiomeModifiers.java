package net.thebastion.odyssey.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.thebastion.odyssey.Odyssey;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_HONORED_DEPOSIT = registerKey("add-honored-deposit");
    public static final ResourceKey<BiomeModifier> ADD_KRUXROCK = registerKey("add-kruxrock");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_KRUXROCK, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.OLD_GROWTH_PINE_TAIGA),
                        biomes.getOrThrow(Biomes.OLD_GROWTH_SPRUCE_TAIGA),
                        biomes.getOrThrow(Biomes.TAIGA)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.KRUX_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

         context.register(ADD_HONORED_DEPOSIT, new BiomeModifiers.AddFeaturesBiomeModifier(
                 HolderSet.direct(
                         biomes.getOrThrow(Biomes.PLAINS),
                         biomes.getOrThrow(Biomes.OLD_GROWTH_PINE_TAIGA),
                         biomes.getOrThrow(Biomes.OLD_GROWTH_SPRUCE_TAIGA),
                         biomes.getOrThrow(Biomes.TAIGA)
                 ),
                 HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.HONORED_PLACED_KEY)),
                 GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(Odyssey.MODID, name));
    }
}