package net.thebastion.odyssey.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.thebastion.odyssey.Odyssey;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> HONORED_PLACED_KEY = registerKey("honored_ore_placed");
    public static final ResourceKey<PlacedFeature> KRUX_PLACED_KEY = registerKey("kruxstone_placed");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, HONORED_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HONORED_DEPOSIT_KEY),
                ModNaturalBlockPlacement.commonOrePlacement(200, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        register(context, KRUX_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.KRUXROCK_PLACED_KEY),
                ModNaturalBlockPlacement.stonePlacement(20, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-5))));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Odyssey.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}