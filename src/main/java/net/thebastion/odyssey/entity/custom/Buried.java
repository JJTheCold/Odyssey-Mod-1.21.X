package net.thebastion.odyssey.entity.custom;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.thebastion.odyssey.entity.ai.goal.ModMeleeAttackGoal;
import net.thebastion.odyssey.entity.type.IAnimatedMonster;

public class Buried extends IAnimatedMonster {
    public final AnimationState attackAnimationState = new AnimationState();
    private int attackAnimationTimeout = 0;
    public final AnimationState idleAnimationState = new AnimationState();
    public int idleAnimationTimeout = 0;
    public int ticksUntilNextAttack = 0;
    public int attackInterval = 15;
    public boolean damageWaitingTicks;

    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(Buried.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_GUARDING = SynchedEntityData.defineId(Buried.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HAS_TARGET = SynchedEntityData.defineId(Buried.class, EntityDataSerializers.BOOLEAN);

    public Buried(EntityType<? extends IAnimatedMonster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide) {
            this.entityData.set(HAS_TARGET, this.getTarget() != null);
        }
    }

    public boolean hasClientTarget() {
        return this.entityData.get(HAS_TARGET);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ATTACKING, false);
        builder.define(IS_GUARDING, false);
        builder.define(HAS_TARGET, false);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }
    public boolean isGuarding() {
        return this.entityData.get(IS_GUARDING);
    }

    public void setAttacking(boolean attacking) { this.entityData.set(ATTACKING, attacking); }
    public void setGuarding(boolean guarding) { this.entityData.set(IS_GUARDING, guarding); }

    @Override
    protected int decreaseAirSupply(int air) {
        return air;
    }

    @Override
    protected SoundEvent getAmbientSound() { return SoundEvents.WITHER_SKELETON_AMBIENT; }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.WITHER_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.WITHER_SKELETON_DEATH;
    }

    @Override
    public void die(DamageSource cause) {
        super.die(cause);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(3, new BuriedAttackGoals(this, 1.3, false));
        this.goalSelector.addGoal(2, new RestrictSunGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    public static class BuriedAttackGoals extends ModMeleeAttackGoal {
        private final Buried entity;

        public BuriedAttackGoals(PathfinderMob pMob, double speedModifier, boolean pFollowingTargetEvenIfNotSeen) {
            super(pMob, speedModifier, pFollowingTargetEvenIfNotSeen);
            this.entity = ((Buried) pMob);
        }

        @Override
        protected double defaultAttackReachSqr(LivingEntity target) {
            double combinedWidth = (this.mob.getBbWidth() * 1.75F) + target.getBbWidth();
            return Math.max(combinedWidth * combinedWidth, 0.5D);
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity livingEntity, double pDistToEnemySqr) {
            double reachSqr = this.getAttackReachSqr(livingEntity);

            if (pDistToEnemySqr <= reachSqr && this.isTimeToAttack()) {
                this.resetAttackCooldown();

                this.mob.getLookControl().setLookAt(livingEntity.getX(), livingEntity.getEyeY(), livingEntity.getZ());

                this.entity.attackAnimationState.start(this.mob.tickCount);
                this.mob.level().broadcastEntityEvent(this.mob, (byte) 4);

                this.mob.swing(InteractionHand.MAIN_HAND);
                this.mob.doHurtTarget(livingEntity);

                this.entity.setAttacking(true);
            }
        }
        @Override
        public void stop() {
            this.entity.setAttacking(false);
            super.stop();
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AbstractSkeleton.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 30d)
                .add(Attributes.FOLLOW_RANGE, 25d)
                .add(Attributes.ATTACK_DAMAGE, 6)
                .add(Attributes.MOVEMENT_SPEED, 0.25d)
                .add(Attributes.ARMOR, 10)
                .add(Attributes.WATER_MOVEMENT_EFFICIENCY, 1)
                .add(Attributes.ARMOR_TOUGHNESS, 5)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.6);
    }

    @Override
    protected boolean canRide(Entity entity) {
        return false;
    }

    public void playSound(SoundEvent sound) {
        if (!this.isSilent()) {
            this.playSound(sound, this.getSoundVolume(), this.getVoicePitch());
        }
    }

    @Override
    protected float getSoundVolume() {
        return 1.5F;
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 48;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 35; // Length in ticks of your animation
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }

        if(!this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationState.stop();
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6F, 1f);
        } else {
            f = 0f;
        }
        this.walkAnimation.update(f, 0.2f);
    }

    @Override
    public void startSeenByPlayer(ServerPlayer serverPlayer) {
        super.startSeenByPlayer(serverPlayer);

    }

    @Override
    public void tick() {
        super.tick();
        if(this.level().isClientSide()) {
            this.setupAnimationStates();
            if (this.getTarget() != null) {
                this.setGuarding(true);
            } else if (this.getTarget() == null) {
                this.setGuarding(false);
            }
        }
    }
}
