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

    public AbstractSingleInputTimedRecipe(
            RecipeType<? extends AbstractSingleInputTimedRecipe> type,
            RecipeSerializer<? extends AbstractSingleInputTimedRecipe> serializer, String group,
            Ingredient ingredient, ItemStack result, int time)
    {
        super(type, serializer, group, ingredient, result);
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

    @NotNull
    public Ingredient input() {
        return ingredient;
    }

    @Override
    public @NotNull ItemStack assemble(SingleRecipeInput input, HolderLookup.Provider registries) {
        return result.copy();
    }

    public static class Serializer<T extends AbstractSingleInputTimedRecipe> implements RecipeSerializer<T> {

        private final AbstractSingleInputTimedRecipe.Factory<T> factory;
        private final MapCodec<T> codec;
        private final StreamCodec<RegistryFriendlyByteBuf, T> packetCodec;

        public Serializer(AbstractSingleInputTimedRecipe.Factory<T> factory) {
            this.factory = factory;
            this.codec = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                    Ingredient.CODEC.fieldOf("ingredient").forGetter(AbstractSingleInputTimedRecipe::input),
                    ItemStack.STRICT_CODEC.fieldOf("result").forGetter(AbstractSingleInputTimedRecipe::result),
                    Codec.INT.fieldOf("time").orElse(200).forGetter(recipe -> recipe.time)
            ).apply(instance, factory::create));
            packetCodec = StreamCodec.of(this::write, this::read);
        }

        @Override
        public @NotNull MapCodec<T> codec() {
            return codec;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, T> streamCodec() {
            return packetCodec;
        }

        private T read(RegistryFriendlyByteBuf buf) {
            Ingredient input = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            ItemStack output = ItemStack.STREAM_CODEC.decode(buf);
            return this.factory.create(input, output, buf.readInt());
        }

        private void write(RegistryFriendlyByteBuf buf, T recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.input());
            ItemStack.STREAM_CODEC.encode(buf, recipe.result());
            buf.writeInt(recipe.time);
        }
    }

    public interface Factory<T extends AbstractSingleInputTimedRecipe> {
        T create(Ingredient ingredient, ItemStack itemStack, int time);
    }

}