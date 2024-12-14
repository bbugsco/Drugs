package com.github.bbugsco.drugs.block.blocks.one_input;

import com.github.bbugsco.drugs.block.entity.one_input.CatalyticReformerBlockEntity;
import com.github.bbugsco.drugs.block.entity.DrugsBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import org.jetbrains.annotations.Nullable;

public class CatalyticReformer extends OneInputBlock<CatalyticReformerBlockEntity> {

    public CatalyticReformer(Properties properties) {
        super(properties, simpleCodec(CatalyticReformer::new), Shapes.block());
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CatalyticReformerBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : createTickerHelper(blockEntityType, DrugsBlockEntities.CATALYTIC_REFORMER, (world1, pos, blockState, blockEntity) -> blockEntity.tick(world1, pos, blockState));
    }

}
