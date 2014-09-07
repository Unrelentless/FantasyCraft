package com.unrelentless.fcraft.items;

import com.unrelentless.fcraft.items.weapons.FCraftWeapon;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class FCraftItem {

	public static Item materiaGreen;
	
	public static void init(){
	
		materiaGreen = new FCraftMateriaGreen();
		
		GameRegistry.registerItem(materiaGreen, materiaGreen.getUnlocalizedName());
		
	}
}
