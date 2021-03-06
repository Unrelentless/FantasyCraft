package com.unrelentless.fcraft.items.weapons;

import java.util.List;
import com.unrelentless.fcraft.extendedprops.FCraftExtendedPlayer;
import com.unrelentless.fcraft.handlers.materia.GreenMateriaHandler;
import com.unrelentless.fcraft.items.FCraftItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class FCraftWeapon extends ItemSword{

	public static ItemSword swordBuster;
	public static ItemSword rodNirvana;
	
	public FCraftWeapon(ToolMaterial material) {
		super(material);
	}
	
	public static void init(){
		swordBuster = new SwordBuster();
		rodNirvana = new RodNirvana();
		
		GameRegistry.registerItem(FCraftWeapon.swordBuster, swordBuster.getUnlocalizedName());
		GameRegistry.registerItem(FCraftWeapon.rodNirvana, rodNirvana.getUnlocalizedName());
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
		}
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
		FCraftExtendedPlayer props = FCraftExtendedPlayer.get(par3EntityPlayer);
		if(!par2World.isRemote && hasMateria(par1ItemStack)){
			int materiaID = par1ItemStack.stackTagCompound.getInteger("SocketContents"+props.getCurrentMateria());
			int materiaMeta = par1ItemStack.stackTagCompound.getInteger("SocketContentsMeta"+props.getCurrentMateria());
			if(materiaID != 0){
				if(materiaID == Item.getIdFromItem(FCraftItem.materiaGreen)){
					new GreenMateriaHandler(par3EntityPlayer);
				}
			}
		}
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
