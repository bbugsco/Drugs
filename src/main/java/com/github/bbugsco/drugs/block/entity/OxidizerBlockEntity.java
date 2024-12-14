package com.github.bbugsco.drugs.block.entity;

import com.github.bbugsco.drugs.block.generic.OneInputBlockEntity;
import com.github.bbugsco.drugs.gui.OxidizerMenu;
import com.github.bbugsco.drugs.recipe.recipes.OxidizerRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class OxidizerBlockEntity extends OneInputBlockEntity<OxidizerRecipe> {

    public OxidizerBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, "Oxidizer", OxidizerRecipe.Type.INSTANCE, DrugsBlockEntities.OXIDIZER, 0, false);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new OxidizerMenu(i, inventory, this, dataAccess);
    }

}
