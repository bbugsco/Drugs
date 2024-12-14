package com.github.bbugsco.drugs.recipe.generic;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

public class MultipleInputSerializer<T extends MultipleInputTimedRecipe> implements RecipeSerializer<T> {

    private final MultipleInputTimedRecipe.Factory<T> factory;
    private final MapCodec<T> codec;
    private final StreamCodec<RegistryFriendlyByteBuf, T> packetCodec;


    public MultipleInputSerializer(MultipleInputTimedRecipe.Factory<T> factory) {
        this.factory = factory;
        this.codec = RecordCodecBuilder.mapCodec(
                (instance) -> instance.group(
                        Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").forGetter(recipe -> recipe.ingredients),
                        ItemStack.STRICT_CODEC.fieldOf("result").forGetter(MultipleInputTimedRecipe::result),
                        Codec.INT.fieldOf("time").orElse(200).forGetter(MultipleInputTimedRecipe::time)
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
        NonNullList<Ingredient> input =  NonNullList.withSize(buf.readVarInt(), Ingredient.EMPTY);;
        input.replaceAll(ingredient -> Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
        ItemStack output = ItemStack.STREAM_CODEC.decode(buf);
        return this.factory.create(input, output, buf.readInt());
    }

    private void write(RegistryFriendlyByteBuf buf, T recipe) {
        buf.writeVarInt(recipe.ingredients.size());
        for (Ingredient ingredient : recipe.ingredients) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ingredient);
        }
        ItemStack.STREAM_CODEC.encode(buf, recipe.result());
        buf.writeInt(recipe.time);
    }
}
