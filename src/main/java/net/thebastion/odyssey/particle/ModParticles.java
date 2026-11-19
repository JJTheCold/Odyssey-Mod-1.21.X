package net.thebastion.odyssey.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thebastion.odyssey.Odyssey;

import java.util.function.Supplier;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, Odyssey.MODID);

    public static final Supplier<SimpleParticleType> AXE_HIT_PARTICLE =
            PARTICLE_TYPES.register("axe-on-hit-explosion", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> SPINNING_RED_PARTICLES =
            PARTICLE_TYPES.register("red-square", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> SPINNING_YELLOW_PARTICLES =
            PARTICLE_TYPES.register("yellow-square", () -> new SimpleParticleType(true));
    public static final Supplier<SimpleParticleType> RISING_YELLOW_PARTICLES =
            PARTICLE_TYPES.register("rising-yellow-square", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}