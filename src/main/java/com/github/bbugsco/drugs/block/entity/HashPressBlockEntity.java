package com.github.bbugsco.drugs.block.entity;

import com.github.bbugsco.drugs.block.generic.OneInputBlockEntity;
import com.github.bbugsco.drugs.gui.HashPressMenu;
import com.github.bbugsco.drugs.recipe.recipes.HashPressRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class HashPressBlockEntity extends OneInputBlockEntity<HashPressRecipe> {

    public HashPressBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, "Hash Press", HashPressRecipe.Type.INSTANCE, DrugsBlockEntities.HASH_PRESS, 0, false);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new HashPressMenu(i, inventory, this, dataAccess);
    }
}
