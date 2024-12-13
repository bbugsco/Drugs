package com.github.bbugsco.drugs.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class AbstractSingleInputTimedRecipe extends SingleItemRecipe {

    private final ItemStack result;
    private final Ingredient ingredient;
    protected final int time;

    public AbstractSingleInputTimedRecipe(Ingredient ingredient, ItemStack result, int time, String group) {
        super(AbstractSingleInputTimedRecipe.Type.INSTANCE, AbstractSingleInputTimedRecipe.Serializer.INSTANCE, group, ingredient, result);
        this.ingredient = ingredient;
        this.result = result;
        this.time = time;
    }

    public int getTime() {
        return this.time;
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        if (level.isClientSide()) {
            return false;
        }
        return ingredient.test(input.getItem(0));
    }

    @NotNull
    public ItemStack result() {
        return result;
    }

    @Override
    public @NotNull ItemStack assemble(SingleRecipeInput input, HolderLookup.Provider registries) {
        return result.copy();
    }

    @Override
    public @NotNull RecipeSerializer<? extends SingleItemRecipe> getSerializer() {
        return HashPressRecipe.Serializer.INSTANCE;
    }

    @Override
    public @NotNull RecipeType<? extends SingleItemRecipe> getType() {
        return HashPressRecipe.Type.INSTANCE;
    }

    public static class Type<T extends SingleItemRecipe> implements RecipeType<T> {
        public static final AbstractSingleInputTimedRecipe.Type<? extends SingleItemRecipe> INSTANCE = new AbstractSingleInputTimedRecipe.Type<T>;
    }

    public static class Serializer<T extends SingleItemRecipe> implements RecipeSerializer<T> {

        public static final AbstractSingleInputTimedRecipe.Serializer INSTANCE = new AbstractSingleInputTimedRecipe.Serializer();

        private final MapCodec<T> codec = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        Ingredient.CODEC.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient),
                        ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
                        Codec.INT.fieldOf("time").orElse(200).forGetter(recipe -> recipe.time)
                ).apply(instance, T::new)
        );

        private final StreamCodec<RegistryFriendlyByteBuf, T> packetCodec = StreamCodec.of(this::write, this::read);

        @Override
        public @NotNull MapCodec<T> codec() {
            return codec;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, T> streamCodec() {
            return packetCodec;
        }

        private AbstractSingleInputTimedRecipe read(RegistryFriendlyByteBuf buf) {
            Ingredient input = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            ItemStack output = ItemStack.STREAM_CODEC.decode(buf);
            return new (input, output, buf.readInt());
        }

        private void write(RegistryFriendlyByteBuf buf, HashPressRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.ingredient);
            ItemStack.STREAM_CODEC.encode(buf, recipe.result());
            buf.writeInt(recipe.time);

        }


    public interface RecipeFactory<HashPressRecipe> {
        HashPressRecipe create(Ingredient input, ItemStack result, int time);
    }

}
