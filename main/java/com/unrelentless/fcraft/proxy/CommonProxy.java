package com.unrelentless.fcraft.proxy;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.unrelentless.fcraft.extendedprops.FCraftExtendedPlayer;
import com.unrelentless.fcraft.gui.GuiSocket;
import com.unrelentless.fcraft.inventory.ContainerSocket;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy implements IGuiHandler {

	private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();

	public void registerRenderers() {}

	/**
	 * Returns a side-appropriate EntityPlayer for use during message handling
	 */
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		return ctx.getServerHandler().playerEntity;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(ID){
		case GuiSocket.GUI_ID:return new GuiSocket(player, player.inventory, FCraftExtendedPlayer.get(player).inventory);
		default: return null;
		}
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch(ID){
		case GuiSocket.GUI_ID:return new ContainerSocket(player, player.inventory, FCraftExtendedPlayer.get(player).inventory);
		default: return null;
		}
	}

	/**
	 * Adds an entity's custom data to the map for temporary storage
	 * @param compound An NBT Tag Compound that stores the IExtendedEntityProperties data only
	 */
	public static void storeEntityData(String name, NBTTagCompound compound)
	{
		extendedEntityData.put(name, compound);
	}

	/**
	 * Removes the compound from the map and returns the NBT tag stored for name or null if none exists
	 */
	public static NBTTagCompound getEntityData(String name)
	{
		return extendedEntityData.remove(name);
	}
}
