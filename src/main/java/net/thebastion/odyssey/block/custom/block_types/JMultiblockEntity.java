package net.thebastion.odyssey.block.custom.block_types;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.thebastion.odyssey.block.entity.AncientsAltarEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JMultiblockEntity extends BaseEntityBlock {

    public static final MapCodec<JMultiblockEntity> CODEC = simpleCodec((JMultiblockEntity::new));
    protected JMultiblockEntity(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return null;
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock())) {
            if(level.getBlockEntity(pos) instanceof AncientsAltarEntity AncientsAltarEntity) {
                AncientsAltarEntity.drops();
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    private List<BlockPos> getHelperPositions(BlockPos basePos) {
        return null;
    }
}
