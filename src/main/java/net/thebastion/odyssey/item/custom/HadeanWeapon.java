package net.thebastion.odyssey.item.custom;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.thebastion.odyssey.particle.ModParticles;

import java.util.List;
import java.util.Objects;

public class HadeanWeapon extends AxeItem {
    public HadeanWeapon(Tier tier, Properties properties) {
        super(tier, properties);
    }

    private boolean isAllied(LivingEntity attacker, LivingEntity target) {
        if (attacker == target) return true;
        return attacker != null && attacker.isAlliedTo(target);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Level level = target.level();

        if (level instanceof ServerLevel serverLevel) {
            target.igniteForSeconds(6);

            float attackStrength = 1.0F;
            if (attacker instanceof Player player) {
                attackStrength = player.getAttackStrengthScale(0.1F);
            }

            float maxRadius = 2.0F;
            float maxDamage = 15.0F;
            float currentRadius = maxRadius * attackStrength;
            float currentDamage = maxDamage * attackStrength;

            if (!isAllied(attacker, target) && currentRadius > 1) {
                DamageSource properDamageSource = attacker instanceof Player player ?
                        serverLevel.damageSources().playerAttack(player) :
                        serverLevel.damageSources().mobAttack(attacker);

                target.hurt(properDamageSource, currentDamage);

                serverLevel.playSound(null, target.getX(), target.getY(), target.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 1.0F, 1.0F);
                serverLevel.sendParticles(ParticleTypes.EXPLOSION, target.getX(), target.getY() + 1.5, target.getZ(), 1, 0.0, 0.0, 0.0, 0.0);
                serverLevel.sendParticles(ModParticles.AXE_HIT_PARTICLE.get(), target.getX(), target.getY() + 1.0, target.getZ(), 1, 0.3, 0.3, 0.3, 0);

                AABB explosionRadius = target.getBoundingBox().inflate(currentRadius);
                List<LivingEntity> nearbyEntities = serverLevel.getEntitiesOfClass(LivingEntity.class, explosionRadius);

                for (LivingEntity entity : nearbyEntities) {
                    if (entity != attacker && entity != target && !isAllied(attacker, entity)) {
                        entity.hurt(serverLevel.damageSources().explosion(attacker, attacker), currentDamage);

                        Vec3 knockback = entity.position().subtract(target.position()).normalize().scale(currentRadius);
                        entity.setDeltaMovement(knockback.x * 0, 0, knockback.z * 0);
                    }
                }

                // 5. Trigger tool durability damage manually since we are skipping super
                stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);

                // CRITICAL: Return true here to bypass super.hurtEnemy() and stop double-processing
                return true;
            }
        }

        return super.hurtEnemy(stack, target, attacker);
    }

    public int getExplosionPower() {
        return 3;
    }

    @Override
    public void setDamage(ItemStack stack, int damage){
        super.setDamage(stack, 0);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide) {
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }
        player.getCooldowns().addCooldown(this, 20 * 20);
        Vec3 lookDirection = player.getViewVector(1.0F).normalize();
        level.playSound(
                null,
                player.getX(),player.getY(),player.getZ(),
                SoundEvents.FIRECHARGE_USE,
                SoundSource.BLOCKS,
                1.5F,
                0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
        );

        Vec3 spawnDirection = player.position().add(lookDirection.x * 1.5, lookDirection.y + 1.5, lookDirection.z * 1.5);
        LargeFireball largefireball = new LargeFireball(level, player, lookDirection, this.getExplosionPower());
        largefireball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 1.5F, 1.0F);
        largefireball.setPos(spawnDirection.x, spawnDirection.y, spawnDirection.z);

        level.addFreshEntity(largefireball);


        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), false);
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext context, List<Component> lines, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, context, lines, pIsAdvanced);
        lines.add(Component.translatable("tooltip.odyssey.hadean-greataxe"));
    }
}
