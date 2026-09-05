package net.thebastion.odyssey.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.entity.custom.*;

import java.util.function.Supplier;

public class ModEntities {

    ///ENTITY REGISTRIES///

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Odyssey.MODID);

    public static final Supplier<EntityType<HanD>> HAN_D =
            ENTITY_TYPES.register("han-d", () -> EntityType.Builder.of(HanD::new, MobCategory.MISC)
                    .sized(1.2f, 3f).build("han-d"));

    public static final Supplier<EntityType<Buried>> BURIED =
            ENTITY_TYPES.register("buried", () -> EntityType.Builder.of(Buried::new, MobCategory.MISC)
                    .sized(0.75f, 1.9f).build("buried"));

    public static final Supplier<EntityType<BuriedLesser>> BURIED_LESSER =
            ENTITY_TYPES.register("buried-lesser", () -> EntityType.Builder.of(BuriedLesser::new, MobCategory.MISC)
                    .sized(0.75f, 2f).build("buried-lesser"));

    public static final Supplier<EntityType<Champion>> CHAMPION =
            ENTITY_TYPES.register("champion", () -> EntityType.Builder.of(Champion::new, MobCategory.MISC)
                    .sized(1.5f, 4.5f).build("champion"));

    public static final Supplier<EntityType<ScytheProjectile>> SCYTHE_PROJECTILE =
            ENTITY_TYPES.register("scythe-projectile", () -> EntityType.Builder.<ScytheProjectile>of(ScytheProjectile::new, MobCategory.MISC)
                    .sized(3f, 1f).build("scythe-projectile"));

    public static final Supplier<EntityType<RelicBolt>> RELIC_BOLT =
            ENTITY_TYPES.register("relic-bolt", () -> EntityType.Builder.<RelicBolt>of(RelicBolt::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("relic-bolt"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

    ///CAMERA SHAKE by someone, I can't remember///

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
