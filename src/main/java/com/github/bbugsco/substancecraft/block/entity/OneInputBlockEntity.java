package com.github.bbugsco.substancecraft.block.entity;

import com.github.bbugsco.substancecraft.recipe.generic.OneInputRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class OneInputBlockEntity<T extends OneInputRecipe> extends AbstractIoBlockEntity implements RecipeList<T> {

    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;
    public static final int BYPRODUCT_START_SLOT = 2;

    public final RecipeManager.CachedCheck<SingleRecipeInput, T> matchGetter;
    private final boolean selectsRecipe;
    private final RecipeType<T> type;
    private List<RecipeHolder<T>> recipes;

    public OneInputBlockEntity(BlockPos pos, BlockState state, String displayName, RecipeType<T> type, BlockEntityType<?> blockEntityType, boolean selectsRecipe) {
        super(blockEntityType, pos, state, displayName, 5);
        this.selectsRecipe = selectsRecipe;
        this.matchGetter = RecipeManager.createCheck(type);
        this.type = type;
        this.recipes = new ArrayList<>();
    }

    @Override
    public void setLevel(Level level) {
        super.setLevel(level);
        setupRecipeList();
    }

    public int getMaxByproducts() {
        int max = 0;
        for (RecipeHolder<T> holder : recipes) {
            if (holder.value().getByproducts().size() > max)
                max = holder.value().getByproducts().size();
        }
        return max;
    }

    @Override
    public List<RecipeHolder<T>> getRecipes() {
        return this.recipes;
    }

    @Override
    public boolean selectsRecipe() {
        return selectsRecipe;
    }

    public void setupRecipeList() {
        if (this.level != null) {
            this.recipes = this.level.getRecipeManager().getAllRecipesFor(type);
        }
    }

    public ItemStack getRenderStack() {
        if(this.getItem(OUTPUT_SLOT).isEmpty()) {
            return this.getItem(INPUT_SLOT);
        } else {
            return this.getItem(OUTPUT_SLOT);
        }
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        ItemStack itemStack = this.inventory.get(slot);
        boolean itemsEqual = !stack.isEmpty() && ItemStack.isSameItem(itemStack, stack);
        this.inventory.set(slot, stack);
        if (slot == INPUT_SLOT && !itemsEqual) {
            this.maxProgress = getCookTime();
            this.progress = 0;
            this.setChanged();
        }
    }

    public Optional<RecipeHolder<T>> getCurrentRecipe() {
        if (selectsRecipe) {
            List<RecipeHolder<T>> recipes = getRecipes();
            if (recipes.isEmpty()) {
                return Optional.empty();
            } else {
                int index = getSelectedRecipeIndex();
                if (index > -1 && index < recipes.size()) {
                    return Optional.of(recipes.get(index));
                } else return Optional.empty();
            }
        } else {
            return matchGetter.getRecipeFor(new SingleRecipeInput(getItem(INPUT_SLOT)), level);
        }
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if(level.isClientSide) return;
        if (selectsRecipe) {
            selectedRecipeTick(level, pos, state);
        } else {
            if (isSlotEmptyOrReceivable(OUTPUT_SLOT)) {
                if (hasRecipe()) {
                    progress++;
                    setChanged(level, pos, state);
                    if (progress >= maxProgress) {
                        craftItem();
                        progress = 0;
                    }
                } else {
                    progress = 0;
                }
            } else {
                progress = 0;
                setChanged(level, pos, state);
            }
        }
    }

    public void selectedRecipeTick(Level level, BlockPos pos, BlockState state) {
        if (isSlotEmptyOrReceivable(OUTPUT_SLOT)) {
            if (hasRecipe()) {
                T recipe = getRecipes().get(getSelectedRecipeIndex()).value();
                if (recipe.matches(new SingleRecipeInput(inventory.getFirst()), level)) {
                    if ((inventory.get(OUTPUT_SLOT).getCount() == 0) || inventory.get(OUTPUT_SLOT).getItem() == recipe.getResult().getItem()) {
                        progress++;
                        setChanged(level, pos, state);
                        if (progress >= maxProgress) {
                            craftItem(recipe);
                            progress = 0;
                        }
                    } else {
                        progress = 0;
                    }
                }
            } else {
                progress = 0;
            }
        } else {
            progress = 0;
            setChanged(level, pos, state);
        }
    }

    private void craftItem(T recipe) {
        this.removeItem(INPUT_SLOT, 1);
        ItemStack result = recipe.getResult();
        if (canInsertAmountIntoSlot(result, OUTPUT_SLOT)) {
            this.setItem(OUTPUT_SLOT, new ItemStack(result.getItem(), getItem(OUTPUT_SLOT).getCount() + recipe.getResult().getCount()));
        }
        byproduct(recipe);
    }

    private void craftItem() {
        Optional<RecipeHolder<T>> recipe = getCurrentRecipe();
        this.removeItem(INPUT_SLOT, 1);
        recipe.ifPresent(inputOutputRecipeRecipeEntry -> this.setItem(OUTPUT_SLOT, new ItemStack(
                inputOutputRecipeRecipeEntry.value().getResult().getItem(),
                getItem(OUTPUT_SLOT).getCount() + inputOutputRecipeRecipeEntry.value().getResult().getCount())));
        recipe.ifPresent(recipeHolder -> byproduct(recipeHolder.value()));
    }

    private void byproduct(OneInputRecipe recipe) {
        List<ItemStack> byproducts = recipe.getByproducts();
        if (byproducts.isEmpty()) { return; }
        int index = 0;
        for (ItemStack byproduct : byproducts) {
            if (getLevel() == null) return;
            if (getLevel().random.nextInt(100) < byproduct.getCount() << 1) continue;
            int slot = BYPRODUCT_START_SLOT + index;
            if (!canInsertItemIntoSlot(byproduct.getItem(), slot)) return;
            if (!canInsertAmountIntoSlot(byproduct, slot)) return;
            setItem(slot, new ItemStack(byproduct.getItem(), getItem(slot).getCount() + 1));
            index++;
        }
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<T>> recipe = getCurrentRecipe();
        return recipe.isPresent() && canInsertAmountIntoSlot(recipe.get().value().getResult(), OUTPUT_SLOT) && canInsertItemIntoSlot(recipe.get().value().getResult().getItem(), OUTPUT_SLOT);
    }

    private int getCookTime() {
        SingleRecipeInput input = new SingleRecipeInput(getItem(INPUT_SLOT));
        return matchGetter.getRecipeFor(input, level).map((recipe) -> recipe.value().getTime()).orElse(200);
    }

}
