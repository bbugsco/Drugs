package com.github.bbugsco.substancecraft.block.entity.one_input;

import com.github.bbugsco.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.bbugsco.substancecraft.gui.one_input_menu.RefineryMenu;
import com.github.bbugsco.substancecraft.recipe.recipes.RefineryRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class RefineryBlockEntity extends OneInputBlockEntity<RefineryRecipe> {

    public RefineryBlockEntity(BlockPos pos, BlockState blockState) {
        super(pos, blockState, "Refinery", RefineryRecipe.Type.INSTANCE, SubstanceCraftBlockEntities.REFINERY, true);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int syncId, Inventory inventory, Player player) {
        return new RefineryMenu(syncId, inventory, this, dataAccess);
    }

}