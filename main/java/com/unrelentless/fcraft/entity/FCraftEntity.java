package com.unrelentless.fcraft.entity;

import java.util.Random;

import net.minecraft.entity.EntityList;

import com.unrelentless.fcraft.FantasyCraft;

import cpw.mods.fml.common.registry.EntityRegistry;

public class FCraftEntity {

	public static void init(){
		registerEntity(FCraftEntityOrich.class, "entityOrichalcum");
		registerEntity(FCraftEntityFrog.class, "entityFrog");
	}
	
	public static void registerEntity(Class entityClass, String name){
		
		int entityID = EntityRegistry.findGlobalUniqueEntityId();
		long seed = name.hashCode();
		Random rand = new Random(seed);
		int primaryColor = rand.nextInt() * 16777215;
		int secondaryColor = rand.nextInt() * 16777215;

		EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
		EntityRegistry.registerModEntity(entityClass, name, entityID, FantasyCraft.instance, 64, 1, true);
		EntityList.entityEggs.put(Integer.valueOf(entityID), 
				new EntityList.EntityEggInfo(entityID, primaryColor, secondaryColor));
		
	}
}
