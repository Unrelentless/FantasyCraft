package com.unrelentless.fcraft.extendedprops;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import com.unrelentless.fcraft.FantasyCraft;
import com.unrelentless.fcraft.packets.SyncPlayerPropsPacket;
import com.unrelentless.fcraft.proxy.CommonProxy;

public class FCraftExtendedPlayer implements IExtendedEntityProperties {

	public static final String FCRAFT_EXT_PLAYER = "playerExtendedProps";
	public static final int ZODIAC_WATCHER = 20, EXP_WATCHER = 21, DEATH_WATCHER = 22;
	
	private final EntityPlayer player;

	public FCraftExtendedPlayer(EntityPlayer player){
		this.player = player;
		this.player.getDataWatcher().addObject(ZODIAC_WATCHER, 0);
	}

	public void setZodiac(int newValue){
		this.player.getDataWatcher().updateObject(ZODIAC_WATCHER, newValue);
		loadProxyData(player);
	}
	
	public int getZodiac(){
		return this.player.getDataWatcher().getWatchableObjectInt(ZODIAC_WATCHER);
	}
	/**
	 * Used to register these extended properties for the player during EntityConstructing event
	 * This method is for convenience only; it will make your code look nicer
	 */
	public static final void register(EntityPlayer player){
		player.registerExtendedProperties(FCraftExtendedPlayer.FCRAFT_EXT_PLAYER, new FCraftExtendedPlayer(player));
	}

	/**
	 * Returns ExtendedPlayer properties for player
	 * This method is for convenience only; it will make your code look nicer
	 */
	public static final FCraftExtendedPlayer get(EntityPlayer player){
		return (FCraftExtendedPlayer) player.getExtendedProperties(FCRAFT_EXT_PLAYER);
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		properties.setFloat("ZodiacCount", this.player.getDataWatcher().getWatchableObjectInt(ZODIAC_WATCHER));
		
		compound.setTag(FCRAFT_EXT_PLAYER, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(FCRAFT_EXT_PLAYER);
		this.player.getDataWatcher().updateObject(ZODIAC_WATCHER, properties.getInteger("ZodiacCount"));
		
	}

	@Override
	public void init(Entity entity, World world) {}

	private static final String getSaveKey(EntityPlayer player) {
		// no longer a username field, so use the command sender name instead:
		return player.getCommandSenderName() + ":" + FCRAFT_EXT_PLAYER;
	}

	public static final void loadProxyData(EntityPlayer player) {
		FCraftExtendedPlayer playerData = FCraftExtendedPlayer.get(player);
		NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));
		if (savedData != null) { playerData.loadNBTData(savedData); }
		FantasyCraft.packetPipeline.sendTo(new SyncPlayerPropsPacket(player), (EntityPlayerMP) player);
	}

}
