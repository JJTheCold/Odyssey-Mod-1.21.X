package net.thebastion.odyssey.event;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.thebastion.odyssey.Odyssey;
import net.thebastion.odyssey.item.custom.GroundBreaker;
import net.thebastion.odyssey.item.custom.RighteousGuard;
import net.thebastion.odyssey.item.custom.RighteousWeapon;

import java.util.HashSet;
import java.util.Set;

@EventBusSubscriber(modid = Odyssey.MODID)
public class ModEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if(mainHandItem.getItem() instanceof GroundBreaker hammer && player instanceof ServerPlayer serverPlayer && !serverPlayer.isShiftKeyDown() && ((GroundBreaker) mainHandItem.getItem()).CanGroundbreak) {
            BlockPos initialBlockPos = event.getPos();
            if(HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for(BlockPos pos : GroundBreaker.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
                if(pos == initialBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }

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