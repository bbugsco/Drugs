package com.github.bbugsco.drugs.block.entity.one_input;

import com.github.bbugsco.drugs.block.entity.DrugsBlockEntities;
import com.github.bbugsco.drugs.gui.one_input_menu.AirExtractorMenu;
import com.github.bbugsco.drugs.recipe.recipes.AirExtractorRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class AirExtractorBlockEntity extends OneInputBlockEntity<AirExtractorRecipe> {

    public AirExtractorBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state, "Air Extractor", AirExtractorRecipe.Type.INSTANCE, DrugsBlockEntities.AIR_EXTRACTOR, true);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new AirExtractorMenu(i, inventory, this, dataAccess);
    }
}
