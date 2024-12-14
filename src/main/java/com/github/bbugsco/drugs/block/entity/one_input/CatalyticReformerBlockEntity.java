package com.github.bbugsco.drugs.block.entity.one_input;

import com.github.bbugsco.drugs.block.entity.DrugsBlockEntities;
import com.github.bbugsco.drugs.gui.one_input_menu.CatalyticReformerMenu;
import com.github.bbugsco.drugs.recipe.recipes.CatalyticReformerRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CatalyticReformerBlockEntity extends OneInputBlockEntity<CatalyticReformerRecipe> {

    public CatalyticReformerBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, "Catalytic Reform", CatalyticReformerRecipe.Type.INSTANCE, DrugsBlockEntities.CATALYTIC_REFORMER, true);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new CatalyticReformerMenu(i, inventory, this, dataAccess);
    }

}
