package net.thebastion.odyssey.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;

import net.minecraft.ChatFormatting;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Spawner;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.thebastion.odyssey.entity.custom.ScytheProjectile;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;

public class GuidedWeapon extends SwordItem {
    public GuidedWeapon(Tier tier, Properties properties) {
        super(tier, properties);
    }

    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.level().isClientSide) {
            attacker.heal(2);
            attacker.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20 * 10, 0, true, true));
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    /// When the saved data isn't saveding
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack pickupItemStack = player.getItemInHand(hand);
        player.getCooldowns().addCooldown(this, 25);
        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.TRIDENT_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));

        ScytheProjectile scytheProjectile = new ScytheProjectile(player, level, pickupItemStack);

        /// 0 Z axis rotation, larp is free
        scytheProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0f, 2f, 0F);
        level.addFreshEntity(scytheProjectile);

        player.awardStat(Stats.ITEM_USED.get(this));

        player.getInventory().removeItem(pickupItemStack);

        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), false);
    }

    @Override
    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
        return !player.isCreative();
    }

    @Override
    public int getEnchantmentValue() {
        return 1;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext context, List<Component> lines, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, context, lines, pIsAdvanced);
        lines.add(Component.translatable("tooltip.odyssey.guided-scythe"));
    }
}