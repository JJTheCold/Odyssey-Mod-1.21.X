package net.thebastion.odyssey.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.block.ModBlocks;
import net.thebastion.odyssey.util.ModTags;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> HONORED_DEPOSIT_KEY = registerKey("honored-deposit");
    public static final ResourceKey<ConfiguredFeature<?, ?>> KRUXROCK_PLACED_KEY = registerKey("kruxrock-deposit");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest kruxReplaceables = new TagMatchTest(ModTags.Blocks.KRUX_REPLACEABLE );
        RuleTest replacesStones = new TagMatchTest(ModTags.Blocks.IN_STONE);

        List<OreConfiguration.TargetBlockState> honoredDeposits = List.of(
                OreConfiguration.target(kruxReplaceables, ModBlocks.HONORED_DEPOSIT.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> kruxrockDeposits = List.of(
                OreConfiguration.target(replacesStones, ModBlocks.KRUXROCK.get().defaultBlockState()));

        register(context, HONORED_DEPOSIT_KEY, Feature.ORE, new OreConfiguration(honoredDeposits, 4));
        register(context, KRUXROCK_PLACED_KEY, Feature.ORE, new OreConfiguration(kruxrockDeposits, 20));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Odyssey.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>>
    void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}