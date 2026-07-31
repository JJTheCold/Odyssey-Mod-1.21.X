package net.thebastion.odyssey.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.thebastion.odyssey.entity.ModEntities;
import net.thebastion.odyssey.item.ModItems;

import javax.annotation.Nullable;

public class ScytheProjectile extends AbstractArrow {
    private static final EntityDataAccessor<Byte> ID_LOYALTY = SynchedEntityData.defineId(ScytheProjectile.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Boolean> ID_FOIL = SynchedEntityData.defineId(ScytheProjectile.class, EntityDataSerializers.BOOLEAN);
    public Level level;
    public int ScytheReturnTicks;
    private int life;
    private boolean dealtDamage;
    private final java.util.Set<java.util.UUID> piercedEntities = new java.util.HashSet<>();

    public ScytheProjectile(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
        this.entityData.set(ID_LOYALTY, this.getLoyaltyFromItem(new ItemStack(ModItems.GUIDED_SCYTHE.get())));
        this.entityData.set(ID_FOIL, new ItemStack(ModItems.GUIDED_SCYTHE.get()).hasFoil());
    }

    public ScytheProjectile(LivingEntity shooter, Level level, ItemStack itemStack) {
        super(ModEntities.SCYTHE_PROJECTILE.get(), shooter, level, itemStack, null);
        this.entityData.set(ID_LOYALTY, this.getLoyaltyFromItem(itemStack));
        this.entityData.set(ID_FOIL, itemStack.hasFoil());
    }
    private boolean isAcceptableReturnOwner() {
        Entity entity = this.getOwner();
        return entity != null && entity.isAlive() && (!(entity instanceof ServerPlayer) || !entity.isSpectator());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("DealtDamage", this.dealtDamage);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.dealtDamage = compound.getBoolean("DealtDamage");
        this.entityData.set(ID_LOYALTY, this.getLoyaltyFromItem(this.getPickupItemStackOrigin()));
    }
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ID_LOYALTY, (byte)0);
        builder.define(ID_FOIL, false);
    }

    @Override
    public ItemStack getWeaponItem() {
        return this.getPickupItemStackOrigin();
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT_GROUND;
    }
    private byte getLoyaltyFromItem(ItemStack stack) {
        return this.level() instanceof ServerLevel serverlevel
                ? (byte)Mth.clamp(EnchantmentHelper.getTridentReturnToOwnerAcceleration(serverlevel, stack, this), 0, 127)
                : 0;
    }
    @Override
    protected void onHitEntity(EntityHitResult result) {
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        this.setNoPhysics(true);
        dealtDamage = false;
        this.piercedEntities.clear();
    }

    @Nullable
    @Override
    protected EntityHitResult findHitEntity(Vec3 startVec, Vec3 endVec) {
        return null;
    }
    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        super.shoot(x, y, z, velocity, inaccuracy);
        this.life = 0;
    }

    @Override
    public void load(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
    }

    @Override
    public void tick() {
        life++;
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }
        Entity entity = this.getOwner();
        if (this.isAcceptableReturnOwner() && this.isNoPhysics() && entity != null || dealtDamage && entity != null || life == 10 && entity != null) {
            Vec3 vec3 = entity.getEyePosition().subtract(this.position());
            this.setPosRaw(this.getX(), this.getY() + vec3.y * 0.015, this.getZ());
            this.setNoPhysics(true);
            if (this.level().isClientSide) {
                this.yOld = this.getY();
            }

            this.setDeltaMovement(this.getDeltaMovement().scale(0.65).add(vec3.normalize()));
            if (this.ScytheReturnTicks == 0) {
                this.playSound(SoundEvents.TRIDENT_RETURN, 18.0F, 0.5F);
                this.piercedEntities.clear();
            }
            this.ScytheReturnTicks++;
        }
        if (!this.level().isClientSide) {
            float hitboxInflationH = 0.1F;
            float hitboxInflationV = 0.3F;

            java.util.List<Entity> list = this.level().getEntitiesOfClass(
                    Entity.class,
                    this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(hitboxInflationH, hitboxInflationV, hitboxInflationH),
                    target -> this.canHitEntity(target) && target != this.getOwner()
            );

            for (Entity target : list) {
                if (!this.piercedEntities.contains(target.getUUID())) {
                    this.piercedEntities.add(target.getUUID());

                    DamageSource damagesource = this.damageSources().thrown(this, this.getOwner());
                    target.hurt(damagesource, 4.0F);

                    if (target instanceof LivingEntity livingentity) {
                        this.doKnockback(livingentity, damagesource);
                        this.doPostHurtEffects(livingentity);
                        livingentity.invulnerableTime = 10;
                    }

                    this.playSound(SoundEvents.TRIDENT_HIT, 1.0F, 1.0F);
                }
            }
        }
        super.tick();
    }

    protected float getWaterInertia() {
        return 1F;
    }

    @Override
    protected double getDefaultGravity() {
        return 0;
    }

    @Override
    public void tickDespawn() {
        if (this.pickup != Pickup.ALLOWED) {
            super.tickDespawn();
        }
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.GUIDED_SCYTHE.get());
    }
}
