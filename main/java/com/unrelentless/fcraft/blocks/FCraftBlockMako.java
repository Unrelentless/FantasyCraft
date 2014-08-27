package com.unrelentless.fcraft.blocks;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.unrelentless.fcraft.FantasyCraft;
import com.unrelentless.fcraft.blocks.tiles.TileEntityBlockMako;

public class FCraftBlockMako extends BlockContainer {

	int ticks;
	
	public FCraftBlockMako(){
		super(Material.glass);

		this.setHardness(50.0F);
		this.setResistance(50.0F);
		this.setBlockName("blockMako");
		this.setCreativeTab(FantasyCraft.fcraftTabBlocks);
		this.setTickRandomly(true);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {

		return new TileEntityBlockMako();
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}

	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
		TileEntityBlockMako tile = (TileEntityBlockMako) world.getTileEntity(x, y, z);
		float[] colours = new float[3];
		
		if(tile != null && ticks%100==0){
			for(int i=0;i<colours.length;i++){
				colours[i] = rand.nextFloat();
			}
			tile.setColour(colours);
			ticks = 0;
		}
		ticks++;
	}
}
