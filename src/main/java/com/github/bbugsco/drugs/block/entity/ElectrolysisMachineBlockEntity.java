package com.github.bbugsco.drugs.block.entity;

import com.github.bbugsco.drugs.block.generic.OneInputBlockEntity;
import com.github.bbugsco.drugs.gui.ElectrolysisMachineMenu;
import com.github.bbugsco.drugs.recipe.recipes.ElectrolysisRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ElectrolysisMachineBlockEntity extends OneInputBlockEntity<ElectrolysisRecipe> {

    public ElectrolysisMachineBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, "Electrolysis", ElectrolysisRecipe.Type.INSTANCE, DrugsBlockEntities.ELECTROLYSIS_MACHINE, 2, false);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ElectrolysisMachineMenu(i, inventory, this, dataAccess);
    }

}
