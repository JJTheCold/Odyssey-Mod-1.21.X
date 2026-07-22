package net.thebastion.odyssey.entity.custom;

import net.minecraft.core.Holder;
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
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.thebastion.odyssey.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class HanD extends AbstractGolem implements NeutralMob {
    public static double MELEE_RANGE = 8.0D;
    public static String ACTIVATE = "activate";
    public static String IDLE = "idle";
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;
    public int idleAnimationTimeout = 0;
    private int attackAnimationTick;

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
            SynchedEntityData.defineId(HanD.class, EntityDataSerializers.BOOLEAN);

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ATTACKING, true); // Initialize your accessor here
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public HanD(EntityType<? extends AbstractGolem> entityType, Level level) {
        super(entityType, level);
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

        this.targetSelector.addGoal(1, new MeleeAttackGoal(this, 1.7, false));

        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector
                .addGoal(3, new NearestAttackableTargetGoal<>
                        (this, Mob.class, 5, false, false, p_28879_ -> p_28879_ instanceof Enemy)
                );
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AbstractGolem.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 240d)
                .add(Attributes.FOLLOW_RANGE, 60d)
                .add(Attributes.ATTACK_DAMAGE, 20)
                .add(Attributes.JUMP_STRENGTH, 0.5d)
                .add(Attributes.KNOCKBACK_RESISTANCE, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2d);
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
        if (hurt) {
            double d0 = entity instanceof LivingEntity livingentity ? livingentity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE) : 0.0;
            double d1 = Math.max(0.0, 1.0 - d0);
            entity.setDeltaMovement(entity.getDeltaMovement().add(0.0, 0.4F * d1, 0.0));
            if (this.level() instanceof ServerLevel serverlevel) {
                EnchantmentHelper.doPostAttackEffects(serverlevel, entity, damagesource);
            }
        }
        this.playSound(ModSounds.HAN_D_PUNCH.get(), 2.5F, 0.2F);
        return hurt;
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 4) {
            this.attackAnimationTick = 30;
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

    public void playSound(SoundEvent p_216991_) {
        if (!this.isSilent()) {
            this.playSound(p_216991_, this.getSoundVolume(), this.getVoicePitch());
        }
    }
    @Override
    protected float getSoundVolume() {
        return 1.5F;
    }

    private void playSound(Holder.Reference<SoundEvent> genericExplode, float volume, float pitch) {
    }
/*/
    static class MeleeGoal extends Goal {
        public HanD mob;
        private int attackTick;

        public MeleeGoal(HanD mob) {
            this.mob = mob;
            this.setFlags(EnumSet.of(Flag.LOOK, Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            return this.mob.getTarget() != null
                    && this.mob.isAttacking();
        }

        @Override
        public boolean canContinueToUse() {
            return !this.mob.isAttacking();
        }

        @Override
        public void start() {
            this.mob.setAttacking(true);
            if (this.mob.getTarget() != null){
                ModUtil.instaLook(this.mob, this.mob.getTarget());
                this.attackTick = 0;
            }
        }

        @Override
        public void stop() {
            this.mob.setAttacking(false);
            this.mob.setupAnimationStates();
            this.attackTick = 0;
        }

        @Override
        public void tick() {
            if (this.mob.attackTick < 30) {
                if (this.mob.getTarget() != null) {
                    ModUtil.rotateTo(this.mob, this.mob.getTarget(), 5.0F);
                }
            }
            this.mob.getNavigation().stop();
            if (this.mob.attackTick == 34) {
                this.mob.playSound(ModSounds.HAN_D_SLAM.get(), this.mob.getSoundVolume() / 2.0F, 0.9F);
                Vec3 vec3 = this.mob.getHorizontalLookAngle();
                AABB aabb = new AABB(this.mob.blockPosition());
                for (LivingEntity ignored : this.mob.level().getEntitiesOfClass
                        (LivingEntity.class, aabb.move(vec3.scale(5.0D)).inflate(MELEE_RANGE))) {
                }
                CameraShake.cameraShake(this.mob.level(), this.mob.position(), 25.0F, 0.3F, 0, 20);
            }
        }

        public void hurtTarget(Entity target) {
            float f = (float)this.mob.getAttributeValue(Attributes.ATTACK_DAMAGE);
            float f1 = (float)this.mob.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
            if (target instanceof LivingEntity livingEntity){
                f += (livingEntity.getMaxHealth() * ModAttributes.HanDPrecentDamage.get().floatValue());
            }

            boolean flag = target.hurt(Objects.requireNonNull(this.mob.getLastDamageSource()), f);
            if (flag) {
                if (f1 > 0.0F && target instanceof LivingEntity livingEntity) {
                    if (livingEntity.getBoundingBox().getSize() > this.mob.getBoundingBox().getSize()){
                        livingEntity.knockback((double)(f1 * 0.5F), (double) Mth.sin(this.mob.getYRot() * ((float)Math.PI / 180F)), (double)(-Mth.cos(this.mob.getYRot() * ((float)Math.PI / 180F))));
                    }
                }
                this.mob.setLastHurtMob(target);
            }
        }

        @Override
        public boolean requiresUpdateEveryTick() {
            return true;
        }
    }/*/

    private void setupAnimationStates() {
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
