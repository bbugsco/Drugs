package com.github.bbugsco.drugs.block.entity;

import com.github.bbugsco.drugs.block.generic.OneInputBlockEntity;
import com.github.bbugsco.drugs.gui.RefineryMenu;
import com.github.bbugsco.drugs.recipe.recipes.RefineryRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class RefineryBlockEntity extends OneInputBlockEntity<RefineryRecipe> {

    public RefineryBlockEntity(BlockPos pos, BlockState blockState) {
        super(pos, blockState, "Refinery", RefineryRecipe.Type.INSTANCE, DrugsBlockEntities.REFINERY, 1, true);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int syncId, Inventory inventory, Player player) {
        return new RefineryMenu(syncId, inventory, this, dataAccess);
    }

}
