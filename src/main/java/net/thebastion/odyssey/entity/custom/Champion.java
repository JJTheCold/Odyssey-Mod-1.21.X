package net.thebastion.odyssey.entity.custom;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.thebastion.odyssey.entity.type.IAnimatedNeutralMob;
import net.thebastion.odyssey.sound.ModSounds;
import net.thebastion.odyssey.entity.type.IAnimatedMonster;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Champion extends IAnimatedNeutralMob implements NeutralMob {
    public static String ACTIVATE = "activate";
    public static String IDLE = "idle";
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState runAnimationState = new AnimationState();

    public final AnimationState punchAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;
    public int punchAnimationTimeout = 30;
    public int idleAnimationTimeout = 0;
    private int attackAnimationTick;
    float speed = (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED);
    boolean sprintSpeed = (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED) >= speed + 0.75;

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.attackAnimationTick > 0) {
            this.attackAnimationTick--;
        } else {
            --this.attackAnimationTick;
        }

        if (!this.level().isClientSide) {
            this.updatePersistentAnger((ServerLevel)this.level(), true);
        }
    }

    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(Champion.class, EntityDataSerializers.BOOLEAN);

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ATTACKING, true); // Initialize your accessor here
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public Champion(EntityType<? extends IAnimatedNeutralMob> entityType, Level level) {
        super(entityType, level);
        xpReward = 0;
    }

    @Override
    protected int decreaseAirSupply(int air) {
        return air;
    }

    protected SoundEvent getAmbientSound() {
        return ModSounds.HAN_D_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ModSounds.HAN_D_HIT.get();
    }

    @Override
    public void die(DamageSource cause) {
        super.die(cause);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.HAN_D_DEATH.get();
    }

    @Override
    protected void registerGoals() {
        //this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MoveTowardsTargetGoal(this, 0.9, 132.0F));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 4.0F));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Monster.class, 40.0F));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Animal.class, 4.0F));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, AbstractGolem.class, 4.0F));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 0.6));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new MeleeAttackGoal(this, 1.1, false));

        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector
                .addGoal(3, new NearestAttackableTargetGoal<>
                        (this, Mob.class, 0, false, false, p_28879_ -> p_28879_ instanceof Enemy)
                );
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AbstractGolem.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 1200d)
                .add(Attributes.FOLLOW_RANGE, 120d)
                .add(Attributes.ATTACK_DAMAGE, 20)
                .add(Attributes.JUMP_STRENGTH, 0.5d)
                .add(Attributes.KNOCKBACK_RESISTANCE, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 1d);
    }

    @Override
    public void customServerAiStep() {
        if (this.getMoveControl().hasWanted()) {
            double d0 = this.getMoveControl().getSpeedModifier();
            if (d0 == 0.6) {
                this.setPose(Pose.CROUCHING);
                this.setSprinting(false);
            } else if (d0 == 1.33) {
                this.setPose(Pose.STANDING);
                this.setSprinting(true);
            } else {
                this.setPose(Pose.STANDING);
                this.setSprinting(false);
            }
        } else {
            this.setPose(Pose.STANDING);
            this.setSprinting(false);
        }
    }

    @Override
    public void setSprinting(boolean sprinting) {
        super.setSprinting(sprinting);
    }

    private float getAttackDamage() {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    public int getAttackAnimationTick() {
        return this.attackAnimationTick;
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        this.attackAnimationTick = 30;
        this.level().broadcastEntityEvent(this, (byte)4);
        float f = this.getAttackDamage();
        float f1 = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
        DamageSource damagesource = this.damageSources().mobAttack(this);
        boolean hurt = entity.hurt(damagesource, f1);
        this.stopInPlace();
        if (attackAnimationTick <= 0){
            if (hurt) {
                double d0 = entity instanceof LivingEntity livingentity ? livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE) : 0.0;
                double d1 = Math.max(0.0, 1.0 - d0);
                entity.setDeltaMovement(entity.getDeltaMovement().add(0.0, 0.4F * d1, 0.0));
                if (this.level() instanceof ServerLevel serverlevel) {
                    EnchantmentHelper.doPostAttackEffects(serverlevel, entity, damagesource);
                }
            }
            this.playSound(ModSounds.HAN_D_PUNCH.get(), 2.5F, 0.2F);
        }
        return hurt;
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

    public void playSound(SoundEvent p_216991_) {
        if (!this.isSilent()) {
            this.playSound(p_216991_, this.getSoundVolume(), this.getVoicePitch());
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
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }
}
