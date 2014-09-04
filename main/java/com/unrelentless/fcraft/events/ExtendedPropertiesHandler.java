package com.unrelentless.fcraft.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import com.unrelentless.fcraft.extendedprops.FCraftExtendedPlayer;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ExtendedPropertiesHandler {

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer) {
			if (FCraftExtendedPlayer.get((EntityPlayer) event.entity) == null)
				FCraftExtendedPlayer.register((EntityPlayer) event.entity);
		}
	}
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
			FCraftExtendedPlayer.loadProxyData((EntityPlayer) event.entity);
		}
	}
	@SubscribeEvent
	public void onLivingDeathEvent(LivingDeathEvent event) {
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
			FCraftExtendedPlayer.saveProxyData((EntityPlayer) event.entity);
		}
	}
}
