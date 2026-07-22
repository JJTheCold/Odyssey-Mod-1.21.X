package net.thebastion.odyssey;


import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Odyssey.MODID);

    public static final Supplier<SoundEvent> HAN_D_PUNCH = registerSoundEvent("han_d_punch");
    public static final Supplier<SoundEvent> HAN_D_HIT = registerSoundEvent("han_d_hit");
    public static final Supplier<SoundEvent> HAN_D_AMBIENT = registerSoundEvent("han_d_ambient");
    public static final Supplier<SoundEvent> HAN_D_DEATH = registerSoundEvent("han_d_death");

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Odyssey.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}