package net.thebastion.odyssey.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.thebastion.odyssey.block.ModBlocks;
import net.thebastion.odyssey.block.custom.block_types.JMultiblockEntity;
import net.thebastion.odyssey.block.entity.AncientsAltarEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AncientsAltar extends JMultiblockEntity {
    public AncientsAltar(Properties properties) {
        super(properties);
    }

    /* BLOCK ENTITY */

    /*
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos basePos = context.getClickedPos();

        for (BlockPos helperPos : getHelperPositions(basePos)) {
            if (!level.getBlockState(helperPos).canBeReplaced(context)) {
                return null;
            }
        }
        return this.defaultBlockState();
    }
    */

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return null;
    }

    /*
    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide) {
            for (BlockPos helperPos : getHelperPositions(pos)) {
                level.setBlock(helperPos, ModBlocks.ALTAR_PART.get().defaultBlockState(), 3);
            }
        }
    }
    */

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock())) {
            /*
            for (BlockPos helperPos : getHelperPositions(pos)) {
                if (level.getBlockState(helperPos).is(ModBlocks.ALTAR_PART.get()) || level.getBlockState(helperPos).is(ModBlocks.ANCIENTS_ALTAR.get())) {
                    level.setBlock(helperPos, Blocks.AIR.defaultBlockState(), 3);
                }
            }
            */
            if(level.getBlockEntity(pos) instanceof AncientsAltarEntity AncientsAltarEntity) {
                AncientsAltarEntity.drops();
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);

    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
        return false;
    }

    private List<BlockPos> getHelperPositions(BlockPos basePos) {
        return List.of(
                basePos.east(),
                basePos.south(),
                basePos.east().south()
        );
    }
}
