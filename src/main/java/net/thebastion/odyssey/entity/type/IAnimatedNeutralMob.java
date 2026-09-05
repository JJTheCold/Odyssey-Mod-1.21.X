package net.thebastion.odyssey.entity.type;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.thebastion.odyssey.entity.ai.navigation.EntityRotationPatcher;
import net.thebastion.odyssey.entity.ai.navigation.ModPathNavigation;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;


//I MUGGED LEGENDARY MONSTERS FOR THEIR ANIMATED MONSTER CODE
public class IAnimatedNeutralMob extends AbstractGolem implements NeutralMob {
    float totalDamageTaken = 0;
    int hurtCD = 0;
    private static final int HURT_COOLDOWN = 30;
    private boolean loadedFromNBT = false;
    public int hurtCount = 0;
    @javax.annotation.Nullable
    private UUID persistentAngerTarget;

    protected IAnimatedNeutralMob(EntityType<? extends AbstractGolem> entityType, Level level) {
        super(entityType, level);
    }


    public static void applyEffectTo(LivingEntity livingEntity, Holder<MobEffect> mobEffect, int timeInSeconds, int amplifier) {
        int i = timeInSeconds * 20;
        livingEntity.addEffect(new MobEffectInstance(mobEffect, i, amplifier));
    }

    public void sendBasicHotBarMessage(String message, Player player) {
        Component messageComponent =
                Component.translatable(message);
        if (player instanceof ServerPlayer serverPlayer)
            serverPlayer.connection.send(new ClientboundSetActionBarTextPacket(messageComponent));
    }

    public void sendAdvancedHotBarMessage(String message, ChatFormatting chatFormatting, float PlayerRange) {
        List<Player> list = level().getEntitiesOfClass(Player.class, getBoundingBox().inflate(PlayerRange));
        for (Player player : list) {
            Component messageComponent =
                    Component.translatable(message).withStyle(chatFormatting);
            if (player instanceof ServerPlayer serverPlayer)
                serverPlayer.connection.send(new ClientboundSetActionBarTextPacket(messageComponent));
        }
    }

    public double damageCap() {
        return 21;
    }

    public double baseHealth() {
        return 20;
    }

    public boolean getInvulnerabilityConfig() {
        return true;
    }

    public void addDamage(float amount, DamageSource source) {
        float maxAllowed = (float) damageCap();
        float clamped = Math.min(amount, maxAllowed);

        if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) clamped = amount;

        if (clamped <= 0.0F) return;

        float finalDamage = this.getDamageAfterArmorAbsorb(source, clamped);
        finalDamage = this.getDamageAfterMagicAbsorb(source, finalDamage);
        if (finalDamage <= 0.0F) return;

        totalDamageTaken += finalDamage;
        super.setHealth(getHealth());

        if (getInvulnerabilityConfig()) hurtCD = HURT_COOLDOWN;
        if (getHealth() <= 0.0F) {
            this.die(source);
        }
    }

    public void advancedDash(LivingEntity livingEntity, float vec, float offset, float Vscale) {
        float f = Mth.cos(livingEntity.yBodyRot * ((float) Math.PI / 180F));
        float f1 = Mth.sin(livingEntity.yBodyRot * ((float) Math.PI / 180F));
        double theta = (livingEntity.yBodyRot) * (Math.PI / 180);
        theta += Math.PI / 2;
        double vecX = Math.cos(theta);
        double vecZ = Math.sin(theta);
        Vec3 rollPos = new Vec3(livingEntity.getX() + vec * vecX + f * offset, getY(), livingEntity.getZ() + vec * vecZ + f1 * offset);
        Vec3 sub = position().subtract(rollPos);
        Vec3 finalPos = sub.scale(Vscale);
        setDeltaMovement(finalPos.x, getDeltaMovement().y, finalPos.z);
    }

    @Override
    protected boolean canRide(Entity pVehicle) {
        return false;
    }

    public static final EntityDataAccessor<Integer> ATTACK_STATE = SynchedEntityData.defineId(IAnimatedNeutralMob.class, EntityDataSerializers.INT);
    protected boolean dropAfterDeathAnim = false;
    private int killDataRecentlyHit;
    private DamageSource killDataCause;
    private Player killDataAttackingPlayer;
    public int attackTicks;
    public int attackDelayTicks;
    public int attackCooldown;
    public int customDeathTime;

    public int attackDelayTicksValue() {
        return 0;
    }

    public int getAttackDelayTicks() {
        return attackDelayTicks;
    }

    public void applyStackingEffect(LivingEntity entity, Holder<MobEffect> effect, int bonusLevel, int maxLevel, int duration) {

        MobEffectInstance effectInstance = entity.getEffect(effect);

        if (entity.hasEffect(effect) && effectInstance != null) {

            int effectLevel = effectInstance.getAmplifier();
            if (effectLevel < maxLevel) {
                entity.addEffect(new MobEffectInstance(effect, duration, effectLevel + bonusLevel));
            }
        } else if (!(entity.hasEffect(effect) && effectInstance != null)) {
            entity.addEffect(new MobEffectInstance(effect, duration, 0));
        }
    }

    protected BodyRotationControl createBodyControl() {
        return new EntityRotationPatcher(this);
    }

    public boolean targetIsNotNull() {
        return this.getTarget() != null;
    }

    protected PathNavigation createNavigation(Level worldIn) {
        return new ModPathNavigation(this, worldIn);
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id <= 0) {
            this.attackTicks = 0;
        } else if (id <= 1) {
            this.attackDelayTicks = 0;
        } else {
            super.handleEntityEvent(id);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        compound.putInt("attackTicks", attackTicks);
        //  compound.putInt("attackState", getAttackState());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        // setAttackState(compound.getInt("attackState"));
    }

    public LivingEntity target() {
        return getTarget();
    }

    public double yaw() {
        return Math.toRadians(-yBodyRot + 90);
    }

    public double pitch() {
        return Math.toRadians(-getXRot() + 90);
    }

    public boolean isTargetCheesing(float minHeight, float maxHeight) {
        return targetIsNotNull() && (target().getY() - getY() >= maxHeight || target().getY() - getY() <= minHeight);
    }

    public void launch(LivingEntity entity, boolean huge) {
        double deltaX = entity.getX() - this.getX();
        double deltaZ = entity.getZ() - this.getZ();
        double distanceSquared = Math.max(deltaX * deltaX + deltaZ * deltaZ, 0.0001);
        float multiplier = huge ? 2.0F : 0.5F;
        entity.push(deltaX / distanceSquared * (double) multiplier, huge ? 0.5 : 0.2, deltaZ / distanceSquared * (double) multiplier);
    }

    public void launch(LivingEntity entity, float Vxz, float Vy) {
        double deltaX = entity.getX() - this.getX();
        double deltaZ = entity.getZ() - this.getZ();
        double distanceSquared = Math.max(deltaX * deltaX + deltaZ * deltaZ, 0.0001);
        float multiplier = Vxz;
        entity.push(deltaX / distanceSquared * (double) multiplier, Vy, deltaZ / distanceSquared * (double) multiplier);
    }

    public void calculatedDash(float Multiplier) {
        LivingEntity target = this.getTarget();
        if (target != null) {
            this.setDeltaMovement((target.getX() - this.getX()) * Multiplier, 0, (target.getZ() - this.getZ()) * Multiplier);
        }
    }

    public void calculatedDashToPositon(float Multiplier, Vec3 position) {
        LivingEntity target = this.getTarget();
        if (target != null) {
            this.setDeltaMovement((position.x - this.getX()) * Multiplier, 0, (position.z - this.getZ()) * Multiplier);
        }
    }

    public void calculatedDashToScaledVec(float scale, Vec3 position) {
        LivingEntity target = this.getTarget();
        if (target != null) {
            Vec3 start = position().subtract(position);
            Vec3 end = start.scale(scale);
            this.setDeltaMovement((position.x - end.x), 0, (position.z - end.x));
        }
    }

    public void basicDash(float a1, float a2, float minD, boolean cap) {
        if (this.onGround()) {

            LivingEntity target = this.getTarget();

            if (this.getTarget() != null) {
                assert target != null;
                double distanceToTarget =
                        this.distanceToSqr(target.getX(), target.getY(), target.getZ());
                if (distanceToTarget > minD && cap) {

                    Vec3 vector3d = this.getDeltaMovement();
                    float f = this.getYRot() * (float) (Math.PI / 180.0);
                    Vec3 vector3d1 = new Vec3((double) (-Mth.sin(f)), this.getDeltaMovement().y, (double) Mth.cos(f)).scale(a1).add(vector3d.scale(a2));
                    this.setDeltaMovement(vector3d1.x, this.getDeltaMovement().y, vector3d1.z);
                } else {
                    Vec3 vector3d = this.getDeltaMovement();
                    float f = this.getYRot() * (float) (Math.PI / 180.0);
                    Vec3 vector3d1 = new Vec3((double) (-Mth.sin(f)), this.getDeltaMovement().y, (double) Mth.cos(f)).scale(a1).add(vector3d.scale(a2));
                    this.setDeltaMovement(vector3d1.x, this.getDeltaMovement().y, vector3d1.z);
                }
            }
        }
    }

    public void basicDash(float a, float minD, boolean cap) {
        if (this.onGround()) {

            LivingEntity target = this.getTarget();

            if (this.getTarget() != null) {
                assert target != null;
                double distanceToTarget =
                        this.distanceToSqr(target.getX(), target.getY(), target.getZ());
                if (distanceToTarget > minD && cap) {

                    Vec3 vector3d = this.getDeltaMovement();
                    float f = this.getYRot() * (float) (Math.PI / 180.0);
                    Vec3 vector3d1 = new Vec3((double) (-Mth.sin(f)), this.getDeltaMovement().y, (double) Mth.cos(f)).scale(a).add(vector3d.scale(a));
                    this.setDeltaMovement(vector3d1.x, this.getDeltaMovement().y, vector3d1.z);
                } else {
                    Vec3 vector3d = this.getDeltaMovement();
                    float f = this.getYRot() * (float) (Math.PI / 180.0);
                    Vec3 vector3d1 = new Vec3((double) (-Mth.sin(f)), this.getDeltaMovement().y, (double) Mth.cos(f)).scale(a).add(vector3d.scale(a));
                    this.setDeltaMovement(vector3d1.x, this.getDeltaMovement().y, vector3d1.z);
                }
            }
        }
    }

    protected boolean shouldDespawnInPeaceful() {
        return false;
    }


    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ATTACK_STATE, 0);
    }

    public int getAttackState() {
        return this.entityData.get(ATTACK_STATE);
    }

    public void setAttackState(int input) {
        this.attackTicks = 0;
        this.attackDelayTicks = attackDelayTicksValue();
        this.entityData.set(ATTACK_STATE, input);
        this.level().broadcastEntityEvent(this, (byte) -input);
    }


    public void tick() {
        super.tick();

        if (this.getAttackState() > 0) {
            ++this.attackTicks;
        }
        if (this.attackCooldown > 0) {
            --this.attackCooldown;
        }
        if (this.attackDelayTicks > 0) {
            --this.attackDelayTicks;
        }

    }


    public int getAttackTicks() {
        return this.attackTicks;
    }

    public double getAngleBetweenEntities(Entity first, Entity second) {
        return Math.atan2(second.getZ() - first.getZ(), second.getX() - first.getX()) * 57.29577951308232 + 90.0;
    }

    public static void disableShield(LivingEntity livingEntity, int ticks) {
        ((Player) livingEntity).getCooldowns().addCooldown(livingEntity.getUseItem().getItem(), ticks);
        livingEntity.stopUsingItem();
        livingEntity.level().broadcastEntityEvent(livingEntity, (byte) 30);
    }

    protected void repelEntities(float x, float y, float z, float radius) {
        List<LivingEntity> nearbyEntities = this.getEntityLivingBaseNearby((double) x, (double) y, (double) z, (double) radius);
        Iterator var6 = nearbyEntities.iterator();

        while (var6.hasNext()) {
            Entity entity = (Entity) var6.next();
            if (entity.isPickable() && !entity.noPhysics) {
                double angle = (this.getAngleBetweenEntities(this, entity) + 90.0) * Math.PI / 180.0;
                entity.setDeltaMovement(-0.1 * Math.cos(angle), entity.getDeltaMovement().y, -0.1 * Math.sin(angle));
            }
        }

    }

    public boolean canBePushedByEntity(Entity entity) {
        return !isVehicle();
    }

    @Override
    public void push(double pX, double pY, double pZ) {
        if (!isVehicle()) {
            super.push(pX, pY, pZ);
        } else {
        }

    }

    public void push(Entity entityIn) {
        if (!isVehicle()) {
            if (!this.isSleeping() && !this.isPassengerOfSameVehicle(entityIn) && !entityIn.noPhysics && !this.noPhysics) {
                double d0 = entityIn.getX() - this.getX();
                double d1 = entityIn.getZ() - this.getZ();
                double d2 = Mth.absMax(d0, d1);
                if (d2 >= 0.009999999776482582) {
                    d2 = (double) Mth.sqrt((float) d2);
                    d0 /= d2;
                    d1 /= d2;
                    double d3 = 1.0 / d2;
                    if (d3 > 1.0) {
                        d3 = 1.0;
                    }

                    d0 *= d3;
                    d1 *= d3;
                    d0 *= 0.05000000074505806;
                    d1 *= 0.05000000074505806;
                    if (!this.isVehicle() && this.canBePushedByEntity(entityIn)) {
                        this.push(-d0, 0.0, -d1);
                    }

                    if (!entityIn.isVehicle()) {
                        entityIn.push(d0, 0.0, d1);
                    }
                }
            }
        } else {

        }

    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {

        if (this.isVehicle()) return false;
        return super.hurt(pSource, pAmount);
    }

    public List<LivingEntity> getEntityLivingBaseNearby(double distanceX, double distanceY, double distanceZ, double radius) {
        return this.getEntitiesNearby(LivingEntity.class, distanceX, distanceY, distanceZ, radius);
    }

    public <T extends Entity> List<T> getEntitiesNearby(Class<T> entityClass, double dX, double dY, double dZ, double r) {
        return this.level().getEntitiesOfClass(entityClass, this.getBoundingBox().inflate(dX, dY, dZ), (e) -> {
            return e != this && (double) this.distanceTo(e) <= r + (double) (e.getBbWidth() / 2.0F) && e.getY() <= this.getY() + dY;
        });
    }


    @Override
    public int getRemainingPersistentAngerTime() {
        return 120;
    }

    @Override
    public void setRemainingPersistentAngerTime(int remainingPersistentAngerTime) {

    }


    @Override
    public void setPersistentAngerTarget(@javax.annotation.Nullable UUID target) {
        this.persistentAngerTarget = target;
    }

    @Override
    public @Nullable UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    @Override
    public void startPersistentAngerTimer() {

    }
}