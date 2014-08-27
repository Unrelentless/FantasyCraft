package com.unrelentless.fcraft.items;

import com.unrelentless.fcraft.items.weapons.FCraftWeapon;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class FCraftItem {

	public static Item materia;
	
	public static void init(){
	
		materia = new FCraftMateriaGreen();
		
		GameRegistry.registerItem(materia, materia.getUnlocalizedName());
		
	}
}
