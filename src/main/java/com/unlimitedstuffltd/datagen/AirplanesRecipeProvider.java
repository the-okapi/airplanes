package com.unlimitedstuffltd.datagen;

import com.unlimitedstuffltd.Airplanes;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class AirplanesRecipeProvider extends FabricRecipeProvider {
    public AirplanesRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.MISC, Airplanes.JET_ENGINE)
                        .pattern("ini")
                        .pattern("nrn")
                        .pattern("ini")
                        .define('i', Items.IRON_BLOCK)
                        .define('n', Items.NETHERITE_BLOCK)
                        .define('r', Items.REDSTONE_BLOCK)
                        .unlockedBy(getHasName(Items.IRON_BLOCK), has(Items.IRON_BLOCK))
                        .unlockedBy(getHasName(Items.NETHERITE_BLOCK), has(Items.NETHERITE_BLOCK))
                        .unlockedBy(getHasName(Items.REDSTONE_BLOCK), has(Items.REDSTONE_BLOCK))
                        .save(output);
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "AirplanesRecipeProvider";
    }
}
