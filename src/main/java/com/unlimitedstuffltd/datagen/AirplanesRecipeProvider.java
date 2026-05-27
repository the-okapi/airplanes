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
                        .define('i', Items.IRON_INGOT)
                        .define('n', Items.NETHERITE_INGOT)
                        .define('r', Items.REDSTONE)
                        .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                        .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                        .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
                        .save(output);
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "AirplanesRecipeProvider";
    }
}
