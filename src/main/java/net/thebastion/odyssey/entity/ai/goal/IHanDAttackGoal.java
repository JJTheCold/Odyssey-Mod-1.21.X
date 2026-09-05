package net.thebastion.odyssey.entity.ai.goal;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.thebastion.odyssey.entity.custom.HanD;

public class IHanDAttackGoal extends ModMeleeAttackGoal {
    private final HanD entity;

    public IHanDAttackGoal(PathfinderMob pMob, double speedModifier, boolean pFollowingTargetEvenIfNotSeen) {
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