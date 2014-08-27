package com.unrelentless.fcraft.blocks.tiles;

import cpw.mods.fml.common.registry.GameRegistry;

public class FCraftTileEntity {

	public static void init(){
		GameRegistry.registerTileEntity(TileEntityBlockAdamOre.class, "adamTile");
		GameRegistry.registerTileEntity(TileEntityBlockMako.class, "makoTile");
		
	}
}
