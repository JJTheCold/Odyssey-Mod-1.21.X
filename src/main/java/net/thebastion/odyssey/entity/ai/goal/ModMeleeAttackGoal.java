package net.thebastion.odyssey.entity.ai.goal;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Path;

import java.util.EnumSet;

public class ModMeleeAttackGoal extends Goal {

    protected final PathfinderMob mob;
    private final double speedModifier;
    private final boolean followingTargetEvenIfNotSeen;

    private Path path;
    private double pathedTargetX;
    private double pathedTargetY;
    private double pathedTargetZ;

    private int ticksUntilNextPathRecalculation;
    public int ticksUntilNextAttack = 0;

    private long lastCanUseCheck;

    public ModMeleeAttackGoal(PathfinderMob mob, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.followingTargetEvenIfNotSeen = followingTargetEvenIfNotSeen;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        long time = this.mob.level().getGameTime();

        if (time - this.lastCanUseCheck < 20L) {
            return false;
        }

        this.lastCanUseCheck = time;

        LivingEntity target = this.mob.getTarget();

        if (target == null || !target.isAlive()) {
            return false;
        }

        this.path = this.mob.getNavigation().createPath(target, 0);

        if (this.path != null) {
            return true;
        }

        return this.getAttackReachSqr(target) >= this.mob.distanceToSqr(target);
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity target = this.mob.getTarget();

        if (target == null) {
            return false;
        }

        if (!target.isAlive()) {
            return false;
        }

        if (!this.followingTargetEvenIfNotSeen) {
            return !this.mob.getNavigation().isDone();
        }

        if (!this.mob.isWithinRestriction(target.blockPosition())) {
            return false;
        }

        return !(target instanceof Player player)
                || (!player.isSpectator() && !player.isCreative());
    }

    @Override
    public void start() {
        this.mob.getNavigation().moveTo(this.path, this.speedModifier);
        this.ticksUntilNextPathRecalculation = 0;
    }

    @Override
    public void stop() {
        LivingEntity target = this.mob.getTarget();

        if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(target)) {
            this.mob.setTarget(null);
        }

        this.mob.getNavigation().stop();
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        LivingEntity target = this.mob.getTarget();

        if (target == null) {
            return;
        }

        this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);

        double distanceSqr = this.mob.distanceToSqr(target);

        this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
        //this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);

        this.ticksUntilNextAttack--;

        boolean canSee = this.followingTargetEvenIfNotSeen || this.mob.getSensing().hasLineOfSight(target);

        if (canSee && this.ticksUntilNextPathRecalculation <= 0 && (
                this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D || target.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D || this.mob.getRandom().nextFloat() < 0.05F
        )) {

            this.pathedTargetX = target.getX();
            this.pathedTargetY = target.getY();
            this.pathedTargetZ = target.getZ();

            this.ticksUntilNextPathRecalculation = 8;

            if (distanceSqr > 1024.0D) {
                this.ticksUntilNextPathRecalculation += 10;
            } else if (distanceSqr > 256.0D) {
                this.ticksUntilNextPathRecalculation += 5;
            }

            if (!this.mob.getNavigation().moveTo(target, this.speedModifier)) {
                this.ticksUntilNextPathRecalculation += 5;
            }

            this.ticksUntilNextPathRecalculation = this.adjustedTickDelay(this.ticksUntilNextPathRecalculation);
        }

        this.checkAndPerformAttack(target, distanceSqr);
    }

    protected void checkAndPerformAttack(LivingEntity target, double distanceSqr) {
        double attackReach = this.getAttackReachSqr(target);

        if (distanceSqr <= attackReach && this.isTimeToAttack()) {
            this.resetAttackCooldown();
            this.mob.swing(InteractionHand.MAIN_HAND);
            this.mob.doHurtTarget(target);
        }
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = 30;
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected double getAttackReachSqr(LivingEntity target) {
        return this.defaultAttackReachSqr(target);
    }

    protected double defaultAttackReachSqr(LivingEntity target) {
        double combinedWidth = (this.mob.getBbWidth() * 2.0D) + target.getBbWidth();
        return Math.max(combinedWidth * combinedWidth, 0.5D);
    }
}