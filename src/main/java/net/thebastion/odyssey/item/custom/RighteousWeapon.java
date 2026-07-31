package net.thebastion.odyssey.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.entity.custom.RelicBolt;

import java.util.List;

public class RighteousWeapon extends SwordItem {
    public RighteousWeapon(Tier tier, Properties properties) {
        super(tier, properties);
    }
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.level().isClientSide) {
            target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20 * 2, 1, true, true));
            attacker.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20 * 2, 1, true, false));
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
                if(player.isShiftKeyDown()) {
                    if (level.isClientSide) {
                        return InteractionResultHolder.success(player.getItemInHand(hand));
                    }
                    player.getCooldowns().addCooldown(this, 20 * 60);
                    level.playSound(
                            null,
                            player.getX(),
                            player.getY(),
                            player.getZ(),
                            SoundEvents.ENCHANTMENT_TABLE_USE,
                            SoundSource.BLOCKS,
                            2F,
                            0.5F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
                    );
                    player.addEffect(new MobEffectInstance(MobEffects.HEAL, 20, 2, false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20 * 20, 1, false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 20 * 30, 2, false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20 * 30, 1, false, false));
                } else if (!player.isShiftKeyDown()) {
                    if (level.isClientSide) {
                        return InteractionResultHolder.success(player.getItemInHand(hand));
                    }
                    player.getCooldowns().addCooldown(this, 20);
                    Vec3 lookDirection = player.getViewVector(1.0F).normalize();
                    level.playSound(
                            null,
                            player.getX(),player.getY(),player.getZ(),
                            SoundEvents.ENCHANTMENT_TABLE_USE,
                            SoundSource.BLOCKS,
                            0.75F,
                            2.5F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
                    );
                    Vec3 spawnDirection = player.position().add(lookDirection.x, lookDirection.y + 1.5, lookDirection.z);
                    RelicBolt relicBolt = new RelicBolt(player, level);
                    relicBolt.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1F, 1F);
                    relicBolt.setPos(spawnDirection.x, spawnDirection.y, spawnDirection.z);

                    level.addFreshEntity(relicBolt);
                }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), false);
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext context, List<Component> lines, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, context, lines, pIsAdvanced);
        lines.add(Component.translatable("tooltip.odyssey.righteous-blade"));
    }
}
