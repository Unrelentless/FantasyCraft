package com.unrelentless.fcraft.items;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class FCraftItem {

	public static Item materiaGreen;
	
	public static void init(){
	
		materiaGreen = new FCraftMateriaGreen();
		
		GameRegistry.registerItem(materiaGreen, materiaGreen.getUnlocalizedName());
		
	}
}
