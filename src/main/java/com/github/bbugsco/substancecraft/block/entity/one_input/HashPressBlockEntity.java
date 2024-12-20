package com.github.bbugsco.substancecraft.block.entity.one_input;

import com.github.bbugsco.substancecraft.block.entity.SubstanceCraftBlockEntities;
import com.github.bbugsco.substancecraft.gui.one_input_menu.HashPressMenu;
import com.github.bbugsco.substancecraft.recipe.recipes.HashPressRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class HashPressBlockEntity extends OneInputBlockEntity<HashPressRecipe> {

    public HashPressBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, "Hash Press", HashPressRecipe.Type.INSTANCE, SubstanceCraftBlockEntities.HASH_PRESS, false);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new HashPressMenu(i, inventory, this, dataAccess);
    }
}
