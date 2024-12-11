package com.github.bbugsco.drugs.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

public class RefineryRecipe extends SingleItemRecipe {

    private final Ingredient ingredient;
    private final ItemStack result;
    protected final int time;

    public RefineryRecipe(Ingredient ingredient, ItemStack result, int time) {
        super(Type.INSTANCE, Serializer.INSTANCE, "refinery", ingredient, result);
        this.ingredient = ingredient;
        this.result = result;
        this.time = time;
    }

    public int getTime() {
        return this.time;
    }

    @NotNull
    public ItemStack result() {
        return result;
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return this.ingredient.test(input.item());
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(Blocks.STONECUTTER);
    }

    public static class Type implements RecipeType<RefineryRecipe> {
        public static final RefineryRecipe.Type INSTANCE = new RefineryRecipe.Type();
        public static final String ID = "refinery";
    }

    public static class Serializer implements RecipeSerializer<RefineryRecipe> {

        public static final RefineryRecipe.Serializer INSTANCE = new RefineryRecipe.Serializer();
        public static final String ID = "refinery";

        private final MapCodec<RefineryRecipe> codec = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        Ingredient.CODEC.fieldOf("ingredient").forGetter(recipe -> recipe.ingredient),
                        ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.result),
                        Codec.INT.fieldOf("time").orElse(200).forGetter(recipe -> recipe.time)
                ).apply(instance, RefineryRecipe::new)
        );

        private final StreamCodec<RegistryFriendlyByteBuf, RefineryRecipe> packetCodec = StreamCodec.of(this::write, this::read);

        @Override
        public @NotNull MapCodec<RefineryRecipe> codec() {
            return codec;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, RefineryRecipe> streamCodec() {
            return packetCodec;
        }

        private RefineryRecipe read(RegistryFriendlyByteBuf buf) {
            Ingredient input = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            ItemStack output = ItemStack.STREAM_CODEC.decode(buf);
            return new RefineryRecipe(input, output, buf.readInt());
        }

        private void write(RegistryFriendlyByteBuf buf, RefineryRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.ingredient);
            ItemStack.STREAM_CODEC.encode(buf, recipe.result);
            buf.writeInt(recipe.time);
        }
    }

    public interface RecipeFactory<RefineryRecipe> {
        RefineryRecipe create(Ingredient input, ItemStack result, int time);
    }

}
