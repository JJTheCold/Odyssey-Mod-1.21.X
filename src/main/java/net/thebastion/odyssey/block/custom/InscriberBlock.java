package net.thebastion.odyssey.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.thebastion.odyssey.block.entity.AncientsAltarEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED;

public class InscriberBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {
    public InscriberBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false));
    }

    public VoxelShape SHAPE_EAST(){ VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0, 0, 0.125, 0.875, 0.25, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.625, 0, 1, 1, 1), BooleanOp.OR);
        return shape;}
    public VoxelShape SHAPE(){ VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.125, 0, 0.125, 1, 0.25, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.625, 0, 1, 1, 1), BooleanOp.OR);

        return shape;
    }
    public VoxelShape SHAPE_NORTH(){ VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.125, 0, 0.125, 0.875, 0.25, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.625, 0, 1, 1, 1), BooleanOp.OR);

        return shape;
    }
    public VoxelShape SHAPE_SOUTH(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.125, 0, 0, 0.875, 0.25, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.625, 0, 1, 1, 1), BooleanOp.OR);

        return shape;
    }

    public static final EnumProperty<ChestType> PART = BlockStateProperties.CHEST_TYPE;

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(PART).equals(ChestType.RIGHT) ? pState.getValue(FACING) : pState.getValue(FACING).getOpposite();
        return switch (direction) {
            case NORTH -> SHAPE();
            case SOUTH -> SHAPE_EAST();
            case WEST -> SHAPE_SOUTH();
            default -> SHAPE_NORTH();
        };
    }

    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        if (!pLevel.isClientSide) {
            BlockPos blockpos = pPos.relative(pState.getValue(FACING).getClockWise());
            pLevel.setBlock(blockpos, pState.setValue(PART, ChestType.LEFT).setValue(WATERLOGGED, pLevel.getFluidState(blockpos).getType() == Fluids.WATER), 3);
            pLevel.setBlock(pPos, pState.setValue(PART, ChestType.RIGHT), 3);
            pLevel.blockUpdated(pPos, Blocks.AIR);
            pState.updateNeighbourShapes(pLevel, pPos, 3);
        }
    }

    @javax.annotation.Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Direction direction = pContext.getHorizontalDirection();
        BlockPos blockpos = pContext.getClickedPos();
        BlockPos blockpos1 = blockpos.relative(direction.getCounterClockWise());
        Level level = pContext.getLevel();
        if (level.getBlockState(blockpos1).canBeReplaced(pContext) && level.getWorldBorder().isWithinBounds(blockpos1)) {
            return this.defaultBlockState().setValue(FACING, direction.getOpposite()).setValue(WATERLOGGED, level.getFluidState(blockpos).getType() == Fluids.WATER);
        }

        return null;
    }

    public PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.BLOCK;
    }

    public BlockState updateShape(BlockState myState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos myPos, BlockPos pFacingPos) {
        ChestType half = myState.getValue(PART);
        BlockPos requiredNeighborPos = myPos.relative(getNeighbourDirection(half, myState.getValue(FACING)));
        boolean waterlogged = myState.getValue(WATERLOGGED);
        if (waterlogged) {
            pLevel.scheduleTick(myPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
            pLevel.scheduleTick(requiredNeighborPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
        }
        BlockState neighborState = pLevel.getBlockState(requiredNeighborPos);
        if (!neighborState.is(this)) {
            var air = (waterlogged ? Blocks.WATER : Blocks.AIR).defaultBlockState();
            //manually set to prevent block from dropping
            pLevel.setBlock(myPos, air, 35);
            pLevel.levelEvent(null, 2001, myPos, Block.getId(air));
            return air;
        }
        return super.updateShape(myState, pFacing, pFacingState, pLevel, myPos, pFacingPos);
    }

    private static Direction getNeighbourDirection(ChestType pPart, Direction pDirection) {
        return pPart == ChestType.LEFT ? pDirection.getCounterClockWise() : pDirection.getClockWise();
    }

    public static final MapCodec<InscriberBlock> CODEC = simpleCodec(InscriberBlock::new);

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
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

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART, WATERLOGGED);
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        if (blockState.getValue(PART).equals(ChestType.RIGHT))
            return RenderShape.MODEL;
        else
            return RenderShape.INVISIBLE;
    }

    /* BLOCK ENTITY */
}
