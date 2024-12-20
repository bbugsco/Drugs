package com.github.bbugsco.substancecraft.block.blocks;

import com.github.bbugsco.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.bbugsco.substancecraft.block.entity.one_input.AirExtractorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import org.jetbrains.annotations.Nullable;

public class AirExtractor extends GenericMenuBlock<AirExtractorBlockEntity> {

    public AirExtractor(Properties properties) {
        super(properties, simpleCodec(AirExtractor::new), Shapes.block());
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AirExtractorBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : createTickerHelper(blockEntityType, SubstanceCraftBlockEntities.AIR_EXTRACTOR, (world1, pos, blockState, blockEntity) -> blockEntity.tick(world1, pos, blockState));
    }

}
