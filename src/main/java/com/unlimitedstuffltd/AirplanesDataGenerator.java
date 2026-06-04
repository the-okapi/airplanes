package com.unlimitedstuffltd;

import com.unlimitedstuffltd.datagen.AirplanesBlockLootTableProvider;
import com.unlimitedstuffltd.datagen.AirplanesModelProvider;
import com.unlimitedstuffltd.datagen.AirplanesRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class AirplanesDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(AirplanesRecipeProvider::new);
		pack.addProvider(AirplanesBlockLootTableProvider::new);
		pack.addProvider(AirplanesModelProvider::new);
	}
}
