package com.unrelentless.fcraft.blocks;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.unrelentless.fcraft.FantasyCraft;
import com.unrelentless.fcraft.blocks.tiles.TileEntityBlockAdamOre;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FCraftBlockAdamOre extends BlockContainer {

	Minecraft mc;
	Random rand = new Random();
	IIcon texture;
	
	public FCraftBlockAdamOre(){
		super(Material.rock);

		this.setHardness(50.0F);
		this.setResistance(50.0F);
		this.setBlockName("ore.adamore");
		this.setCreativeTab(FantasyCraft.fcraftTabBlocks);
		this.setTickRandomly(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon)
	{

			texture = icon.registerIcon(FantasyCraft.MODID+":OreAdam");
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return texture;
	}

	protected String getUnwrappedUnlocalizedName(String name){
		return name.substring(name.indexOf(".") + 1);
	}


	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {

		return new TileEntityBlockAdamOre();
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9)
	{
		if(!world.isRemote){
			TileEntityBlockAdamOre tile = (TileEntityBlockAdamOre) world.getTileEntity(x, y, z);
			player.addChatMessage(new ChatComponentText(""+tile.getStage()+", Inert:"+tile.isInert()));
		}
		return true;
	}
}