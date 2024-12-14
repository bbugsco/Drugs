package com.github.bbugsco.drugs.block.entity;

import com.github.bbugsco.drugs.block.generic.MultipleOutputsFromSingleInputBlockEntity;
import com.github.bbugsco.drugs.gui.CatalyticReformerMenu;
import com.github.bbugsco.drugs.recipe.recipes.CatalyticReformerRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CatalyticReformerBlockEntity extends MultipleOutputsFromSingleInputBlockEntity<CatalyticReformerRecipe> {

    public CatalyticReformerBlockEntity(BlockPos pos, BlockState state) {
        super(DrugsBlockEntities.CATALYTIC_REFORMER, pos, state, "Catalytic Reformer", CatalyticReformerRecipe.Type.INSTANCE);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new CatalyticReformerMenu(i, inventory, this, dataAccess);
    }
}