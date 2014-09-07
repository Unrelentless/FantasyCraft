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
	public static final int ZODIAC_WATCHER = 20, MANA_WATCHER = 21;
	private final EntityPlayer player;
	public final InventorySocket inventory = new InventorySocket();

	private float maxMana;
	private int currentMateriaSelected;
	private int currentJob, currentSpec;

	/**
	 * Constructor - make sure to init all variables.
	 * 
	 * @param player
	 */
	public FCraftExtendedPlayer(EntityPlayer player){
		this.player = player;
		this.maxMana = 50.0F;
		this.player.getDataWatcher().addObject(ZODIAC_WATCHER, 0);
		this.player.getDataWatcher().addObject(MANA_WATCHER, maxMana);
		this.currentMateriaSelected = 0;
		this.currentJob = 0;
		this.currentSpec = 0;
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
		properties.setFloat("ManaCount", player.getDataWatcher().getWatchableObjectFloat(MANA_WATCHER));
		properties.setInteger("CurrentMateria", currentMateriaSelected);
		properties.setInteger("CurrentJob", currentJob);
		properties.setInteger("CurrentSpec", currentSpec);
		properties.setFloat("MaxMana", maxMana);

		compound.setTag(FCRAFT_EXT_PLAYER, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(FCRAFT_EXT_PLAYER);
		player.getDataWatcher().updateObject(ZODIAC_WATCHER, properties.getInteger("ZodiacCount"));
		player.getDataWatcher().updateObject(MANA_WATCHER, properties.getFloat("CurrentMana"));
		currentMateriaSelected = properties.getInteger("CurrentMateria");
		currentJob = properties.getInteger("CurrentJob");
		currentSpec = properties.getInteger("CurrentSpec");
		maxMana = properties.getFloat("MaxMana");

	}
	/*===============================================================================
	 * 
	 * Registering and syncing Extended Properties
	 * 
	 *===============================================================================
	 */	
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
	 * Active methods
	 * 
	 *================================================================================
	 */
	/**
	 * Consumes mana.
	 * 
	 * @param manaToConsume
	 * 
	 * @return true if able, false if not
	 */
	public boolean consumeMana(float manaToConsume)
	{
		// Consume the amount anyway; if it's more than the player's current mana,
		// mana will be set to 0\
		float currentMana = this.player.getDataWatcher().getWatchableObjectFloat(MANA_WATCHER);
		if(currentMana >= manaToConsume){
			this.player.getDataWatcher().updateObject(MANA_WATCHER, currentMana -= manaToConsume);
			return true;
		}else{
			this.player.getDataWatcher().updateObject(MANA_WATCHER, currentMana);
			return false;
		}
	}

	/*================================================================================
	 * 
	 * GETTERS AND SETTERS
	 * 
	 *================================================================================
	 */

	/**
	 * Sets the player's job to a new one.
	 * 
	 * @param newJob
	 */
	public void setJob(int newJob){
		this.currentJob = newJob;
		if (!player.worldObj.isRemote) {
			syncAll();
		}
	}
	/**
	 * Gets the players job.
	 * 
	 * @return
	 */
	public int getJob(){
		return this.currentJob;
	}

	/**
	 * Sets the player's spec to a new one.
	 * 
	 * @param newSpec
	 */
	public void setSpec(int newSpec){
		this.currentSpec = newSpec;
		if (!player.worldObj.isRemote) {
			syncAll();
		}
	}
	/**
	 * Gets the players spec.
	 * 
	 * @return
	 */
	public int getSpec(){
		return this.currentSpec;
	}

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

	/**
	 * Sets the current value of max mana.
	 * 
	 * @param newMaxMana
	 */
	public void seMaxMana(float newMaxMana){
		this.maxMana = newMaxMana;
	}
	/**
	 * Returns the current value of max mana.
	 * 
	 * @return
	 */
	public float getMaxMana(){
		return this.maxMana;
	}

	/**
	 * Sets current mana.
	 * 
	 * @param newCurrentMana
	 */
	public void setCurrentMana(float newCurrentMana){
		this.player.getDataWatcher().updateObject(MANA_WATCHER, newCurrentMana);
	}
	/**
	 * Returns the current mana value.
	 * 
	 * @return
	 */
	public float getCurrentMana(){
		return this.player.getDataWatcher().getWatchableObjectFloat(MANA_WATCHER);
	}
}
