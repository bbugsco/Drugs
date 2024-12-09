package com.github.bbugsco.drugs.gui;

import com.github.bbugsco.drugs.blocks.entity.RefineryBlockEntity;
import com.github.bbugsco.drugs.recipe.RefineryRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.List;

public class RefineryMenu extends AbstractInputOutputProgressMenu {

    private final RefineryBlockEntity handle;

    public RefineryMenu(int syncId, Inventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.level().getBlockEntity(pos), new SimpleContainerData(3));
    }

    public RefineryMenu(int syncId, Inventory inventory, BlockEntity entity, SimpleContainerData arrayPropertyDelegate) {
        super(DrugsMenus.REFINERY_MENU, syncId, inventory, 2, entity, arrayPropertyDelegate, new Slot[]{new Slot((Container) entity, 0, 121, 11), new Slot((Container) entity, 1, 121, 59)});
        handle = (RefineryBlockEntity) entity;
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        handle.setSelectedRecipeIndex(id);
        return true;
    }

    public int getSelectedRecipeIndex() {
        return handle.getSelectedRecipeIndex();
    }

    public List<RecipeHolder<RefineryRecipe>> getRecipes() {
        return handle.getRecipes();
    }

    public int getNumRecipes() {
        return getRecipes().size();
    }

}
