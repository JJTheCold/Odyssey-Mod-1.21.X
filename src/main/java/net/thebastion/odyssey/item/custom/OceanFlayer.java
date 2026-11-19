package net.thebastion.odyssey.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.ArrayList;
import java.util.List;

public class OceanFlayer extends PickaxeItem {
    public OceanFlayer(Tier tiers, Properties properties) {
        super(tiers, properties);
    }
    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Level level = target.level();
        if (level instanceof ServerLevel serverLevel) {
            target.igniteForSeconds(5);
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void setDamage(ItemStack stack, int damage){
        super.setDamage(stack, 0);
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext context, List<Component> lines, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, context, lines, pIsAdvanced);
        lines.add(Component.translatable("tooltip.odyssey.ground-breaker"));
    }
}