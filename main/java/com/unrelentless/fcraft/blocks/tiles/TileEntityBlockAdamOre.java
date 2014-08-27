package com.unrelentless.fcraft.blocks.tiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.unrelentless.fcraft.blocks.FCraftBlock;

public class TileEntityBlockAdamOre extends TileEntity {
	Random rand = new Random();
	int ticks = 1;

	int growthStage = 0;
	boolean inert = false;

	public int getStage(){
		return this.growthStage;
	}

	public void setStage(int stage){
		this.growthStage = stage;
	}

	public boolean isInert(){
		return this.inert;
	}

	public void setInert(){
		this.inert = true;
	}

	public void setActive(){
		this.inert = false;
	}


	@Override
	public void updateEntity()
	{
		super.updateEntity();
		int[] coords = {this.xCoord, this.yCoord, this.zCoord};
		World world = this.worldObj;

		if(this != null && ticks%100 == 0 && !this.isInert() && !world.isRemote){
			int[] stoneCoords = searchStone(this.worldObj, this, coords);
			if(stoneCoords[0]==-2){
				this.setStage(this.getStage()+1);
				ticks = 0;
			}else{
				world.setBlockToAir(this.zCoord+stoneCoords[0], this.yCoord+stoneCoords[1], this.zCoord+stoneCoords[2]);
				world.setBlock(this.xCoord+stoneCoords[0], this.yCoord+stoneCoords[1], this.zCoord+stoneCoords[2], FCraftBlock.blockAdamOre);
				TileEntityBlockAdamOre tileBred = (TileEntityBlockAdamOre) world.getTileEntity(this.xCoord+stoneCoords[0], this.yCoord+stoneCoords[1], this.zCoord+stoneCoords[2]);
				this.setStage(this.getStage()+1);
				tileBred.setInert();
			}
			
			if(this.getStage()==100){
				this.setInert();
			}
		}
		ticks++;
	}

	private int[] searchStone(World world, TileEntityBlockAdamOre te, int[] coords){
		List<String> blockCoordList = new ArrayList<String>();

		int[] stoneCoords = {0, 0, 0};
		String stringCoords = "";
		blockCoordList.clear();
		for(int i=-1;i<=1;i++){
			for(int j=-1;j<=1;j++){
				for(int k=-1;k<=1;k++){
					if(world.getBlock(coords[0]+i, coords[1]+j, coords[2]+k) == Blocks.stone){
						stringCoords = ""+i+"<"+j+">"+k;
						blockCoordList.add(stringCoords);
					}
				}
			}
		}
		if(blockCoordList.size() != 0){
			int random = rand.nextInt(blockCoordList.size());
			String tempString = blockCoordList.get(random);
			stoneCoords[0] = Integer.parseInt(tempString.substring(0, tempString.indexOf("<")));
			stoneCoords[1] = Integer.parseInt(tempString.substring(tempString.indexOf("<")+1, tempString.indexOf(">")));
			stoneCoords[2] = Integer.parseInt(tempString.substring(tempString.indexOf(">")+1));
		}else{
			stoneCoords[0] = -2;
		}
		return stoneCoords;
	}


	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		tagCompound.setBoolean("inert", this.inert);
		tagCompound.setInteger("stage", this.growthStage);
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{		
		super.readFromNBT(tagCompound);
		this.inert = tagCompound.getBoolean("inert");
		this.growthStage = tagCompound.getInteger("stage");
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		readFromNBT(packet.func_148857_g());
	}
}
