package com.unrelentless.fcraft.items.weapons;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class FCraftWeapon extends ItemSword{

	public static ItemSword swordBuster;
	
	public FCraftWeapon(ToolMaterial material) {
		super(material);
	}
	
	public static void init(){
		swordBuster = new SwordBuster();
		
		GameRegistry.registerItem(FCraftWeapon.swordBuster, swordBuster.getUnlocalizedName());
	}
	
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {

		EntityPlayer player = (EntityPlayer)par3Entity;

		if(par1ItemStack.stackTagCompound == null ){
			par1ItemStack.setTagCompound(new NBTTagCompound( ));
			par1ItemStack.stackTagCompound.setBoolean("Init", true);	
		}else if(!par1ItemStack.stackTagCompound.getBoolean("Init")){
			String s = "Made by " + player.getCommandSenderName();
			par1ItemStack.stackTagCompound.setString("WhoCreated", s);
			par1ItemStack.stackTagCompound.setInteger("CurrentSockets", FCraftWeaponStats.currentSockets);
			par1ItemStack.stackTagCompound.setInteger("MaxSockets", FCraftWeaponStats.maxSockets);
			for(int i=0;i<par1ItemStack.stackTagCompound.getInteger("CurrentSockets");i++){
				par1ItemStack.stackTagCompound.setInteger("SocketContents"+i, 0);	
				par1ItemStack.stackTagCompound.setInteger("SocketContentsMeta"+i, 0);
				par1ItemStack.stackTagCompound.setString("SocketContentsString"+i, "--");	
			}
			par1ItemStack.stackTagCompound.setBoolean("Init", true);
			//sendToServer(par1ItemStack, par2World, par3Entity);
		}
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
/*		FCraftJobCore props = FCraftJobCore.get(par3EntityPlayer);
		if(!par2World.isRemote && hasMateria(par1ItemStack)){
			int materiaID = par1ItemStack.stackTagCompound.getInteger("SocketContents"+props.getCurrentMateria());
			int materiaMeta = par1ItemStack.stackTagCompound.getInteger("SocketContentsMeta"+props.getCurrentMateria());
			if(materiaID != 0){
				if(materiaID == ItemID.MATERIA_BLACK_ID+256){
					new BlackMateriaHandler(par1ItemStack, par2World, par3EntityPlayer ,materiaMeta);
				}else if(materiaID == ItemID.MATERIA_BLUE_ID+256){
					new BlueMateriaHandler(par1ItemStack, par2World, par3EntityPlayer ,materiaMeta);
				}else if(materiaID == ItemID.MATERIA_GREEN_ID+256){
					new GreenMateriaHandler(par1ItemStack, par2World, par3EntityPlayer ,materiaMeta);
				}else if(materiaID == ItemID.MATERIA_PROTO_ID+256){

				}else if(materiaID == ItemID.MATERIA_PURPLE_ID+256){
					new PurpleMateriaHandler(par1ItemStack, par2World, par3EntityPlayer ,materiaMeta);
				}else if(materiaID == ItemID.MATERIA_RED_ID+256){
					new RedMateriaHandler(par1ItemStack, par2World, par3EntityPlayer ,materiaMeta);
				}else if(materiaID == ItemID.MATERIA_WHITE_ID+256){
					new WhiteMateriaHandler(par1ItemStack, par2World, par3EntityPlayer ,materiaMeta);
				}else if(materiaID == ItemID.MATERIA_YELLOW_ID+256){
					new YellowMateriaHandler(par1ItemStack, par2World, par3EntityPlayer ,materiaMeta);
				}
			}
		}*/
		return par1ItemStack;
	}

	public boolean hasMateria(ItemStack itemstack){
		for(int i=0;i<itemstack.stackTagCompound.getInteger("CurrentSockets");i++){
			if(itemstack.stackTagCompound.getInteger("SocketContents"+i)!= 0){
				return true;
			}
		}
		return false;
	}
	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {

		if( par1ItemStack.stackTagCompound == null )
			par1ItemStack.setTagCompound( new NBTTagCompound( ) );

		if( par1ItemStack.stackTagCompound.hasKey("WhoCreated") ){
			par3List.add(EnumChatFormatting.GRAY + par1ItemStack.stackTagCompound.getString("WhoCreated"));
			par3List.add(EnumChatFormatting.GRAY + "Sockets: "+ 
					par1ItemStack.stackTagCompound.getInteger("CurrentSockets") + "/" +
					par1ItemStack.stackTagCompound.getInteger("MaxSockets"));
			par3List.add(EnumChatFormatting.GRAY + "---------------");
			for(int i=0;i<par1ItemStack.stackTagCompound.getInteger("CurrentSockets");i++){

				if(par1ItemStack.stackTagCompound.getInteger("SocketContents"+i) != 0){

/*					if(par1ItemStack.stackTagCompound.getInteger("SocketContents"+i) == ItemID.MATERIA_BLACK_ID+256){
						par3List.add(EnumChatFormatting.BLACK + par1ItemStack.stackTagCompound.getString("SocketContentsString"+i));
					}else if(par1ItemStack.stackTagCompound.getInteger("SocketContents"+i) == ItemID.MATERIA_BLUE_ID+256){
						par3List.add(EnumChatFormatting.BLUE + par1ItemStack.stackTagCompound.getString("SocketContentsString"+i));
					}else if(par1ItemStack.stackTagCompound.getInteger("SocketContents"+i) == ItemID.MATERIA_GREEN_ID+256){
						par3List.add(EnumChatFormatting.GREEN + par1ItemStack.stackTagCompound.getString("SocketContentsString"+i));
					}else if(par1ItemStack.stackTagCompound.getInteger("SocketContents"+i) == ItemID.MATERIA_PROTO_ID+256){
						par3List.add(EnumChatFormatting.GOLD + par1ItemStack.stackTagCompound.getString("SocketContentsString"+i));
					}else if(par1ItemStack.stackTagCompound.getInteger("SocketContents"+i) == ItemID.MATERIA_PURPLE_ID+256){
						par3List.add(EnumChatFormatting.DARK_PURPLE + par1ItemStack.stackTagCompound.getString("SocketContentsString"+i));
					}else if(par1ItemStack.stackTagCompound.getInteger("SocketContents"+i) == ItemID.MATERIA_RED_ID+256){
						par3List.add(EnumChatFormatting.RED + par1ItemStack.stackTagCompound.getString("SocketContentsString"+i));
					}else if(par1ItemStack.stackTagCompound.getInteger("SocketContents"+i) == ItemID.MATERIA_WHITE_ID+256){
						par3List.add(EnumChatFormatting.WHITE +par1ItemStack.stackTagCompound.getString("SocketContentsString"+i));
					}else if(par1ItemStack.stackTagCompound.getInteger("SocketContents"+i) == ItemID.MATERIA_YELLOW_ID+256){
						par3List.add(EnumChatFormatting.YELLOW + par1ItemStack.stackTagCompound.getString("SocketContentsString"+i));
					}else{
						par3List.add(""+par1ItemStack.stackTagCompound.getString("SocketContentsString"+i));						
					}*/
				}
			}
		}
	}
}
