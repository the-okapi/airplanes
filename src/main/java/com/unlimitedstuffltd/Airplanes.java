package com.unlimitedstuffltd;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

public class Airplanes implements ModInitializer {
	public static final String MOD_ID = "airplanes";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static <T extends Block> T registerBlock(String name, Function<BlockBehaviour.Properties, T> blockFunction, BlockBehaviour.Properties blockProperties) {
		ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Airplanes.MOD_ID, name));

		T block = blockFunction.apply(blockProperties.setId(blockKey));

		ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Airplanes.MOD_ID, name));

		BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());

		Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);

		return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
	}

	public static final JetEngineBlock JET_ENGINE = registerBlock("jet_engine", JetEngineBlock::new, BlockBehaviour.Properties.of().sound(SoundType.IRON).strength(5f, 6f).mapColor(MapColor.METAL));

	@Override
	public void onInitialize() {
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
				.register(creativeModeTab -> {
					creativeModeTab.accept(JET_ENGINE);
				});
	}
}