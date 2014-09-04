package com.unrelentless.fcraft.extendedprops;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import com.unrelentless.fcraft.inventory.InventorySocket;
import com.unrelentless.fcraft.network.PacketDispatcher;
import com.unrelentless.fcraft.network.packet.client.SyncPlayerPropsMessage;
import com.unrelentless.fcraft.proxy.CommonProxy;

public class FCraftExtendedPlayer implements IExtendedEntityProperties {

	public static final String FCRAFT_EXT_PLAYER = "playerExtendedProps";
	public static final int ZODIAC_WATCHER = 20;
	private final EntityPlayer player;
	public final InventorySocket inventory = new InventorySocket();

	private int currentMateriaSelected;

	/**
	 * Constructor - make sure to init all variables.
	 * 
	 * @param player
	 */
	public FCraftExtendedPlayer(EntityPlayer player){
		this.player = player;
		this.player.getDataWatcher().addObject(ZODIAC_WATCHER, 0);
		this.currentMateriaSelected = 0;
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

	public void syncAll(){
		PacketDispatcher.sendTo(new SyncPlayerPropsMessage(player), (EntityPlayerMP) player);
	}

	/**
	 * Needed for initialization of the extended properties.
	 *
	 * @param entity
	 * @param world
	 */
	@Override
	public void init(Entity entity, World world) {}

	/**
	 * Used for saving NBT tags using the player username.
	 * 
	 * @param player
	 * @return String
	 */
	private static final String getSaveKey(EntityPlayer player) {
		// no longer a username field, so use the command sender name instead:
		return player.getCommandSenderName() + ":" + FCRAFT_EXT_PLAYER;
	}

	/**
	 * Server/Client packet synchronization and NBT uploading.
	 * 
	 * @param player
	 */
	public static final void loadProxyData(EntityPlayer player) {
		FCraftExtendedPlayer playerData = FCraftExtendedPlayer.get(player);
		NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));
		if (savedData != null) { playerData.loadNBTData(savedData); }
		PacketDispatcher.sendTo(new SyncPlayerPropsMessage(player), (EntityPlayerMP) player);
	}

	public static final void saveProxyData(EntityPlayer player) {
		NBTTagCompound savedData = new NBTTagCompound();
		FCraftExtendedPlayer.get(player).saveNBTData(savedData);
		CommonProxy.storeEntityData(getSaveKey(player), savedData);
	}

	/*================================================================================
	 * 
	 * GETTERS AND SETTERS
	 * 
	 *================================================================================
	 */

	/**
	 * Sets the currently selected materia.
	 * 
	 * @param newMateria
	 */
	public void setCurrentMateria(int newMateria){
		this.currentMateriaSelected = newMateria;
		if (!player.worldObj.isRemote) {
			syncAll();
		}
	}
	/**
	 * Gets the currently selected materia.
	 * 
	 * @return
	 */
	public int getCurrentMateria(){
		return this.currentMateriaSelected;
	}

	/**
	 * Sets the current value of the zodiac counter.
	 * 
	 * @param newValue
	 */
	public void setZodiac(int newValue){
		this.player.getDataWatcher().updateObject(ZODIAC_WATCHER, newValue);
	}
	/**
	 * Returns the current value of the zodiac counter.
	 * 
	 * @return
	 */
	public int getZodiac(){
		return this.player.getDataWatcher().getWatchableObjectInt(ZODIAC_WATCHER);
	}

	/*===============================================================================
	 * 
	 * NBT SAVE/LOAD
	 * 
	 *===============================================================================
	 */

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		properties.setFloat("ZodiacCount", player.getDataWatcher().getWatchableObjectInt(ZODIAC_WATCHER));
		properties.setInteger("CurrentMateria", currentMateriaSelected);

		compound.setTag(FCRAFT_EXT_PLAYER, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(FCRAFT_EXT_PLAYER);
		player.getDataWatcher().updateObject(ZODIAC_WATCHER, properties.getInteger("ZodiacCount"));
		currentMateriaSelected = properties.getInteger("CurrentMateria");
	}
}
