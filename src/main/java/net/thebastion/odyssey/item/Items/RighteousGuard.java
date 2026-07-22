package net.thebastion.odyssey.item.Items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShieldItem;
import net.thebastion.odyssey.item.ModItems;

public class RighteousGuard extends ShieldItem {
    public RighteousGuard(Properties properties) {
        super(new Properties()
                .durability(2400)
                .fireResistant()
                .rarity(Rarity.EPIC)
        );
    }
    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.is(ModItems.RIGHTEOUS_ALLOY) || super.isValidRepairItem(toRepair, repair);
    }
    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 195000;
    }

    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.level().isClientSide) {
            target.playSound(SoundEvents.ANVIL_LAND, 0.2F, 0.1F);
            target.knockback( 0.7F, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
        }
        return super.hurtEnemy(stack, target, attacker);
    }

}
