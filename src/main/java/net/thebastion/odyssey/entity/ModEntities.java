package net.thebastion.odyssey.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.entity.custom.HanD;

import java.util.function.Supplier;

public class ModEntities {

    ///ENTITY REGISTRIES///

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Odyssey.MODID);

    public static final Supplier<EntityType<HanD>> HAN_D =
            ENTITY_TYPES.register("han-d", () -> EntityType.Builder.of(HanD::new, MobCategory.MISC)
                    .sized(1.2f, 3f).build("han-d"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

    ///CAMERA SHAKE///

    /*public static final DeferredHolder<EntityType<?>, EntityType<CameraShake>> CAMERA_SHAKE = register("camera_shake",
            EntityType.Builder.<CameraShake>of(CameraShake::new, MobCategory.MISC)
                    .fireImmune()
                    .noSummon()
                    .sized(1.0F, 1.0F)
                    .updateInterval(Integer.MAX_VALUE));

    private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String p_20635_, EntityType.Builder<T> p_20636_) {
        return ENTITY_TYPES.register(p_20635_, () -> p_20636_.build(Oblivion.location(p_20635_).toString()));
    }/*/
}
