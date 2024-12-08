package com.github.bbugsco.drugs.blocks;

import com.github.bbugsco.drugs.blocks.entity.DrugsBlockEntities;
import com.github.bbugsco.drugs.blocks.entity.HashPressBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class HashPressBlock extends BaseEntityBlock implements EntityBlock {

    public static final MapCodec<HashPressBlock> CODEC = simpleCodec(HashPressBlock::new);

    private static final VoxelShape SHAPE = Block.box(1, 0, 1, 15, 15, 15);

    public HashPressBlock(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return Objects.requireNonNull(super.getStateForPlacement(ctx)).setValue(BlockStateProperties.HORIZONTAL_FACING, ctx.getHorizontalDirection());
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected @NotNull RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new HashPressBlockEntity(pos, state);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof HashPressBlockEntity) {
                if (level instanceof ServerLevel) {
                    Containers.dropContents(level, pos, (HashPressBlockEntity) blockEntity);
                }
                super.onRemove(state, level, pos, newState, moved);
                level.updateNeighbourForOutputSignal(pos, this);
            } else {
                super.onRemove(state, level, pos, newState, moved);
            }
        }
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(level.getBlockEntity(pos));
    }

    @Override
    public @NotNull InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
        if (!world.isClientSide) {
            MenuProvider menuProvider = (HashPressBlockEntity) world.getBlockEntity(pos);
            if (menuProvider != null) {
                entity.openMenu(menuProvider);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : createTickerHelper(blockEntityType, DrugsBlockEntities.HASH_PRESS, (world1, pos, blockState, blockEntity) -> blockEntity.tick(world1, pos, blockState));
    }

}