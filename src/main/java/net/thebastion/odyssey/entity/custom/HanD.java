package net.thebastion.odyssey.entity.custom;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.thebastion.odyssey.entity.ai.goal.ModMeleeAttackGoal;
import net.thebastion.odyssey.entity.type.IAnimatedNeutralMob;
import net.thebastion.odyssey.sound.ModSounds;
import net.thebastion.odyssey.entity.ai.goal.IHanDAttackGoal;
import org.jetbrains.annotations.Nullable;
import java.util.*;

public class HanD extends IAnimatedNeutralMob implements NeutralMob {
    public static String ACTIVATE = "activate";
    public static String IDLE = "idle";
    public final AnimationState attackAnimationState = new AnimationState();
    private int attackAnimationTimeout = 0;
    public final AnimationState idleAnimationState = new AnimationState();
    public int idleAnimationTimeout = 0;

    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(HanD.class, EntityDataSerializers.BOOLEAN);

    public HanD(EntityType<? extends IAnimatedNeutralMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ATTACKING, false);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    @Override
    protected int decreaseAirSupply(int air) {
        return air;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.HAN_D_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ModSounds.HAN_D_HIT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.HAN_D_DEATH.get();
    }

    @Override
    public void die(DamageSource cause) {
        super.die(cause);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new HanDAttackGoal(this, 1.3, false));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 4.0F));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Monster.class, 40.0F));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Animal.class, 4.0F));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, AbstractGolem.class, 4.0F));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Mob.class, 5, true, true, entity -> entity instanceof Enemy));
        this.targetSelector.addGoal(3, new ResetUniversalAngerTargetGoal<>(this, false));
    }

    public static class HanDAttackGoal extends ModMeleeAttackGoal {
        private final HanD entity;

        public HanDAttackGoal(PathfinderMob pMob, double speedModifier, boolean pFollowingTargetEvenIfNotSeen) {
            super(pMob, speedModifier, pFollowingTargetEvenIfNotSeen);
            this.entity = ((HanD) pMob);
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
        return AbstractGolem.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 240d)
                .add(Attributes.FOLLOW_RANGE, 20d)
                .add(Attributes.ATTACK_DAMAGE, 20)
                .add(Attributes.JUMP_STRENGTH, 0.5d)
                .add(Attributes.KNOCKBACK_RESISTANCE, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2d);
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 4) {
            this.playSound(ModSounds.HAN_D_PUNCH.get(), 2.5F, 2.2F);
        } else {
            super.handleEntityEvent(id);
        }
    }

    @Override
    protected boolean canRide(Entity entity) {
        return false;
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return 60;
    }

    @Override
    public void setRemainingPersistentAngerTime(int remainingPersistentAngerTime) {
    }

    @Override
    public @Nullable UUID getPersistentAngerTarget() {
        return null;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID persistentAngerTarget) {
    }

    @Override
    public void startPersistentAngerTimer() {
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
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 30; // Length in ticks of your animation
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
    public void tick() {
        super.tick();
        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
        /*if (this.getTarget() != null) {
            System.out.println("Target: " + this.getTarget().getName().getString());
        }*/
    }
}
