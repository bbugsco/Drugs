package com.github.bbugsco.substancecraft.block.entity.one_input;

import com.github.bbugsco.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.bbugsco.substancecraft.gui.one_input_menu.ElectrolysisMachineMenu;
import com.github.bbugsco.substancecraft.recipe.recipes.ElectrolysisRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ElectrolysisMachineBlockEntity extends OneInputBlockEntity<ElectrolysisRecipe> {

    public ElectrolysisMachineBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, "Electrolysis", ElectrolysisRecipe.Type.INSTANCE, SubstanceCraftBlockEntities.ELECTROLYSIS_MACHINE, false);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new ElectrolysisMachineMenu(i, inventory, this, dataAccess);
    }

}