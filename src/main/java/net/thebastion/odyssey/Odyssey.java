package net.thebastion.odyssey;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.thebastion.odyssey.accessory.CloakModel;
import net.thebastion.odyssey.accessory.CloakRenderer;
import net.thebastion.odyssey.block.ModBlocks;
import net.thebastion.odyssey.block.entity.renderer.PedestalBlockEntityRenderer;
import net.thebastion.odyssey.entity.ModBlockEntities;
import net.thebastion.odyssey.entity.ModEntities;
import net.thebastion.odyssey.entity.client.buried.BuriedRenderer;
import net.thebastion.odyssey.entity.client.buried.lesser.BuriedLesserRenderer;
import net.thebastion.odyssey.entity.client.champion.ChampionRenderer;
import net.thebastion.odyssey.entity.client.han_d.HanDRenderer;
import net.thebastion.odyssey.entity.client.RelicBoltRenderer;
import net.thebastion.odyssey.entity.client.ScytheProjectileRenderer;
import net.thebastion.odyssey.item.ModCreativeModeTabs;
import net.thebastion.odyssey.item.ModItemProperties;
import net.thebastion.odyssey.item.ModItems;
import net.thebastion.odyssey.loot.ModLootModifiers;
import net.thebastion.odyssey.particle.AxeHitParticle;
import net.thebastion.odyssey.particle.ModParticles;
import net.thebastion.odyssey.particle.RisingSquare;
import net.thebastion.odyssey.particle.SpinningSquare;
import net.thebastion.odyssey.registries.LootRegistry;
import net.thebastion.odyssey.sound.ModSounds;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Odyssey.MODID)
public class Odyssey {
    // Define mod id in a common place for everything to reference

    /*public static ResourceLocation location(String path) {
        return new ResourceLocation(MODID, path);
    }*/

    public static final String MODID = "odyssey";

    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Odyssey(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);
        ModSounds.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModParticles.register(modEventBus);

        LootRegistry.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }


    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = Odyssey.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    static class ClientModEvents {
        public static ShaderInstance MY_SHADER;

        @SubscribeEvent
        public static void registerShaders(RegisterShadersEvent event) throws IOException {
            // 2. Load "assets/yourmodid/shaders/core/my_shader.json"
            event.registerShader(
                new ShaderInstance(
                    event.getResourceProvider(),
                    ResourceLocation.fromNamespaceAndPath("odyssey", "test"),
                    DefaultVertexFormat.POSITION_COLOR_TEX // Must match your .json "attributes"!
                ),
                shader -> TEST = shader
            );
        }
        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(CloakModel.LAYER_LOCATION, CloakModel::createBodyLayer);
        }
        @SubscribeEvent
        static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> CuriosRendererRegistry.register(
                    ModItems.CORE.get(),
                    () -> new CloakRenderer(Minecraft.getInstance().getEntityModels().bakeLayer(CloakModel.LAYER_LOCATION))
            ));
            EntityRenderers.register(ModEntities.BURIED.get(), BuriedRenderer::new);
            EntityRenderers.register(ModEntities.BURIED_LESSER.get(), BuriedLesserRenderer::new);
            EntityRenderers.register(ModEntities.HAN_D.get(), HanDRenderer::new);
            EntityRenderers.register(ModEntities.CHAMPION.get(), ChampionRenderer::new);
            EntityRenderers.register(ModEntities.SCYTHE_PROJECTILE.get(), ScytheProjectileRenderer::new);
            EntityRenderers.register(ModEntities.RELIC_BOLT.get(), RelicBoltRenderer::new);
            ModItemProperties.addCustomItemProperties();
        }
        @SubscribeEvent
        public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(ModParticles.AXE_HIT_PARTICLE.get(), AxeHitParticle.Provider::new);
            event.registerSpriteSet(ModParticles.SPINNING_RED_PARTICLES.get(), SpinningSquare.Provider::new);
            event.registerSpriteSet(ModParticles.SPINNING_YELLOW_PARTICLES.get(), SpinningSquare.Provider::new);
            event.registerSpriteSet(ModParticles.RISING_YELLOW_PARTICLES.get(), RisingSquare.Provider::new);
        }
        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.PEDESTAL_BE.get(), PedestalBlockEntityRenderer::new);
        }
    }
}
