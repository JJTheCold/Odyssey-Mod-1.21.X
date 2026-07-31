package net.thebastion.odyssey.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = "odyssey")
public class RighteousBrothers {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player.level().isClientSide()) return;

        ItemStack mainHand = player.getItemInHand(InteractionHand.MAIN_HAND);
        ItemStack offHand = player.getItemInHand(InteractionHand.OFF_HAND);

        boolean mainIsWeapon = mainHand.getItem() instanceof RighteousWeapon;
        boolean offIsWeapon = offHand.getItem() instanceof RighteousGuard;

        if (mainIsWeapon && offIsWeapon) {
            player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 40 * 4, 1, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 40 * 4, 1, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 40 * 4, 1, false, false));
            player.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 40 * 4, 3, false, false));
        }
    }
}