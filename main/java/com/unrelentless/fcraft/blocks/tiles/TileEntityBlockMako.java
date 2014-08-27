package com.unrelentless.fcraft.blocks.tiles;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBlockMako extends TileEntity {
	Random rand = new Random();

	float[] colours = {1.0F, 1.0F ,1.0F};
	int numOfCrystals = 0;
	int stage = rand.nextInt(8);
	double[][] listTrans = new double[8][2];
	double[][] listAngles = new double[8][4];
	double[] listScale = new double[8];
	
	public TileEntityBlockMako(){
		
		for(int i=0;i<8;i++){
			listScale[i] = (rand.nextInt(3)+5)/10D;
			for(int j=0;j<2;j++){
				listTrans[i][j] = getRandomTranslation()[j];								
			}
		}
		
		for(int i=0;i<8;i++){
			for(int j=0;j<4;j++){
				listAngles[i][j] = getRandomAngle(listTrans[i])[j];								
			}
		}
	}
	
	public float getStage(){
		return this.stage;
	}

	public void setStage(int stage){
		this.stage = stage;
	}

	public float getNumberOfCrystals(){
		return this.numOfCrystals;
	}

	public void setNumberOfCrystals(int number){
		this.numOfCrystals = number;
	}

	public float[] getColour(){
		return this.colours;
	}

	public void setColour(float[] colour){
		for(int i=0;i<colour.length;i++){
			this.colours[i] = colour[i];
		}
	}
	
	public double[][] getTrans(){
		return listTrans;
	}
	
	public double[][] getAngles(){
		return listAngles;
	}

	public double[] getScale(){
		return listScale;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound par1)
	{
		super.writeToNBT(par1);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1)
	{
		super.readFromNBT(par1);
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
	
	private double[] getRandomTranslation(){
		double[] xzDir = new double[2];
		if(rand.nextBoolean()){
			xzDir[0] = (10+rand.nextInt(10))*(-1)/100D;
			if(rand.nextBoolean()){
				xzDir[1] = (10+rand.nextInt(10))*(-1)/100D;
			}else{
				xzDir[1] = (10+rand.nextInt(10))/100D;
			}
		}else{
			xzDir[0] = (1+rand.nextInt(10))/100D;
			if(rand.nextBoolean()){
				xzDir[1] = (10+rand.nextInt(10))/100D;
			}else{
				xzDir[1] = (10+rand.nextInt(10))*(-1)/100D;
			}
		}
		return xzDir;
	}

	private double[] getRandomAngle(double[] xzTrans){
		double[] xzAngle = new double[4];

		if(xzTrans[0] < 0){
			if(xzTrans[1] < 0){
				xzAngle[0]=rand.nextInt(10)+10D;
				xzAngle[1]=-1;
				xzAngle[2]=0;
				xzAngle[3]=1;
			}else if(xzTrans[1] > 0){
				xzAngle[0]=rand.nextInt(10)+10D;
				xzAngle[1]=1;
				xzAngle[2]=0;
				xzAngle[3]=1;
			}else{
				xzAngle[0]=rand.nextInt(10)+10D;
				xzAngle[1]=0;
				xzAngle[2]=0;
				xzAngle[3]=1;
			}
		}else if(xzTrans[0] > 0){
			if(xzTrans[1] < 0){
				xzAngle[0]=rand.nextInt(10)+10D;
				xzAngle[1]=-1;
				xzAngle[2]=0;
				xzAngle[3]=-1;
			}else if(xzTrans[1] > 0){
				xzAngle[0]=rand.nextInt(10)+10D;
				xzAngle[1]=1;
				xzAngle[2]=0;
				xzAngle[3]=-1;
			}else{
				xzAngle[0]=rand.nextInt(10)+10D;
				xzAngle[1]=0;
				xzAngle[2]=0;
				xzAngle[3]=-1;
			}
		}else{
			if(xzTrans[1] < 0){
				xzAngle[0]=rand.nextInt(10)+10D;
				xzAngle[1]=-1;
				xzAngle[2]=0;
				xzAngle[3]=0;
			}else if(xzTrans[1] > 0){
				xzAngle[0]=rand.nextInt(10)+10D;
				xzAngle[1]=1;
				xzAngle[2]=0;
				xzAngle[3]=0;
			}
		}
		return xzAngle;
	}
}
