package com.github.bbugsco.drugs.gui;

import com.github.bbugsco.drugs.block.generic.MultipleOutputsFromSingleInputBlockEntity;
import com.github.bbugsco.drugs.recipe.generic.SingleInputTimedRecipe;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.List;

public class RecipeSelectionIoMenu<R extends SingleInputTimedRecipe,T extends MultipleOutputsFromSingleInputBlockEntity<R>> extends InputOutputProgressMenu {

    private final T handle;

    protected RecipeSelectionIoMenu(MenuType<? extends InputOutputProgressMenu> menu, int syncId, Inventory playerInventory, int inventorySize, BlockEntity entity, SimpleContainerData blockEntityData, Slot[] slots) {
        super(menu, syncId, playerInventory, inventorySize, entity, blockEntityData, slots);
        handle = (T) entity;
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (this.isValidRecipeIndex(id)) {
            handle.setSelectedRecipeIndex(id);
            handle.setChanged();
        }
        return true;
    }

    private boolean isValidRecipeIndex(int recipeIndex) {
        return recipeIndex >= 0 && recipeIndex < handle.getRecipes().size();
    }

    public int getSelectedRecipeIndex() {
        return simpleContainerData.get(2);
    }

    public List<RecipeHolder<R>> getRecipes() {
        return handle.getRecipes();
    }

    public int getNumRecipes() {
        return getRecipes().size();
    }


}
