package com.unlimitedstuffltd.datagen;

import com.unlimitedstuffltd.Airplanes;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class AirplanesBlockLootTableProvider extends FabricBlockLootSubProvider {
    public AirplanesBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        dropSelf(Airplanes.JET_ENGINE);
    }
}
