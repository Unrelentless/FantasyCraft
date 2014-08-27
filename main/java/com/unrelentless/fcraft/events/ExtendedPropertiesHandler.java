package com.unrelentless.fcraft.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;

import com.unrelentless.fcraft.extendedprops.FCraftExtendedPlayer;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ExtendedPropertiesHandler {
	
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		/*
		Be sure to check if the entity being constructed is the correct type for the extended properties you're about to add! The null check may not be necessary - I only use it to make sure properties are only registered once per entity
		 */
		if (event.entity instanceof EntityPlayer && FCraftExtendedPlayer.get((EntityPlayer) event.entity) == null){
			// This is how extended properties are registered using our convenient method from earlier
			FCraftExtendedPlayer.register((EntityPlayer) event.entity);
		}
	}
}
