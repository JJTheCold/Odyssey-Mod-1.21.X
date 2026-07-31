package net.thebastion.odyssey.entity.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.thebastion.odyssey.entity.ModEntities;
import net.thebastion.odyssey.item.ModItems;

public class RelicBolt extends AbstractArrow {
    public int life;
    public Level level;
    public int pierceCount;

    public RelicBolt(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public RelicBolt(LivingEntity shooter, Level level) {
        super(ModEntities.RELIC_BOLT.get(), shooter, level, new ItemStack(ModItems.GUIDED_SCYTHE.get()), null);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return null;
    }

    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        super.shoot(x, y, z, velocity, inaccuracy);
        this.life = 0;
        this.pierceCount = 0;
    }

    public void tick() {
        life++;
        if (life == 20) {
            this.discard();
        }
        this.level().addParticle(
                ParticleTypes.FIREWORK,
                this.getX(),
                this.getY(),
                this.getZ(),
                0,
                0,
                0
        );
        if (pierceCount == 3) {
            this.discard();
        }
        super.tick();
    }

    @Override
    public void playerTouch(Player entity) {
        if (!this.level().isClientSide && (this.inGround || this.isNoPhysics()) && this.shakeTime <= 0) {
            if (this.tryPickup(entity)) {
                this.discard();
            }
        }
    }

    protected boolean tryPickup(Player player) {
        return switch (this.pickup) {
            case DISALLOWED -> true;
            case ALLOWED -> false;
            case CREATIVE_ONLY -> player.hasInfiniteMaterials();
        };
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.AMETHYST_CLUSTER_BREAK;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 4);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
        }
    }

    public Vec3 getMovementToShoot(double x, double y, double z, float velocity, float inaccuracy) {
        return new Vec3(x, y, z)
                .normalize()
                .add(
                        this.random.triangle(0.0, 0 * (double)inaccuracy),
                        this.random.triangle(0.0, 0 * (double)inaccuracy),
                        this.random.triangle(0.0, 0 * (double)inaccuracy)
                )
                .scale((double)velocity);
    }

    @Override
    protected double getDefaultGravity() {
        return 0;
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        this.discard();
    }
}