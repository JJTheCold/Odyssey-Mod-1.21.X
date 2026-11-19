package net.thebastion.odyssey.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.thebastion.odyssey.block.entity.PedestalEntity;
import net.thebastion.odyssey.particle.ModParticles;
import org.jetbrains.annotations.Nullable;

public class Pedestal extends BaseEntityBlock {
    public static final MapCodec<Pedestal> CODEC = simpleCodec(Pedestal::new);

    public Pedestal(Properties properties) {
        super(properties);
    }


    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    /* BLOCK ENTITY */

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PedestalEntity(blockPos, blockState);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (state.getBlock() != newState.getBlock()) {
            if (level.getBlockEntity(pos) instanceof PedestalEntity PedestalEntity) {
                PedestalEntity.drops();
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                              Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof PedestalEntity PedestalEntity) {
            if (PedestalEntity.inventory.getStackInSlot(0).isEmpty() && !stack.isEmpty()) {
                PedestalEntity.inventory.insertItem(0, stack.copy(), false);
                stack.shrink(1);
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
            } else if (stack.isEmpty()) {
                ItemStack stackOnPedestal = PedestalEntity.inventory.extractItem(0, 1, false);
                player.setItemInHand(InteractionHand.MAIN_HAND, stackOnPedestal);
                PedestalEntity.clearContents();
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 1f);
            }
        }
        return ItemInteractionResult.SUCCESS;
    }


    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (level.getBlockEntity(pos) instanceof PedestalEntity PedestalEntity) {
            if (!PedestalEntity.inventory.getStackInSlot(0).isEmpty()) {
                int i = pos.getX();
                int j = pos.getY();
                int k = pos.getZ();
                double d0 = (double)i + random.nextDouble();
                double d1 = (double)j + 1 + random.nextDouble();
                double d2 = (double)k + random.nextDouble();
                level.addParticle(ModParticles.SPINNING_YELLOW_PARTICLES.get(), d0, d1, d2,
                        0.2F * random.nextFloat() * 2,
                        0.5F * random.nextFloat() * 2,
                        0.2F * random.nextFloat() * 2
                );
            }
        }
    }
}