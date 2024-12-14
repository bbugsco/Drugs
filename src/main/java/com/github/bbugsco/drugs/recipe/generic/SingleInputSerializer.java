package com.github.bbugsco.drugs.recipe.generic;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

public class SingleInputSerializer<T extends SingleInputTimedRecipe> implements RecipeSerializer<T> {

    private final SingleInputTimedRecipe.Factory<T> factory;
    private final MapCodec<T> codec;
    private final StreamCodec<RegistryFriendlyByteBuf, T> packetCodec;

    public SingleInputSerializer(SingleInputTimedRecipe.Factory<T> factory) {
        this.factory = factory;
        this.codec = RecordCodecBuilder.mapCodec(
                (instance) -> instance.group(
                        Ingredient.CODEC.fieldOf("ingredient").forGetter(SingleInputTimedRecipe::input),
                        ItemStack.STRICT_CODEC.fieldOf("result").forGetter(SingleInputTimedRecipe::result),
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
