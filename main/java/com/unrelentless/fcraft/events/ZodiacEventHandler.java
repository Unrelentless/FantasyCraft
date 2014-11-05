package com.unrelentless.fcraft.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

import com.unrelentless.fcraft.blocks.FCraftBlock;
import com.unrelentless.fcraft.extendedprops.FCraftExtendedPlayer;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ZodiacEventHandler {

	Random rand = new Random();

	Minecraft mc;

	int maxZodiac;
	int totalMined;
	boolean reached = false;
	List<EntityPlayer> playerList = new ArrayList<EntityPlayer>();
	Block[] stoneArray = {Blocks.stone, Blocks.obsidian, Blocks.gravel};

	@SubscribeEvent
	public void onHarvestEvent(BreakEvent event) {
		maxZodiac = FMLClientHandler.instance().getServer().getCurrentPlayerCount()*10;
		if (event.getPlayer() != null) {
			EntityPlayer player = (EntityPlayer) event.getPlayer();
			FCraftExtendedPlayer props = FCraftExtendedPlayer.get(player);
			props.setZodiac(props.getZodiac()+1);
			player.addChatMessage(new ChatComponentText(""+props.getZodiac()));
		}

		totalMined = 0;

		for(int i=0;i<event.getPlayer().worldObj.getLoadedEntityList().size();i++){
			if (event.getPlayer().worldObj.loadedEntityList.get(i) instanceof EntityPlayer){
				totalMined += event.getPlayer().getDataWatcher().getWatchableObjectInt(20);
			}
		}

		if(totalMined >= maxZodiac){
			for(int j=0;j<event.getPlayer().worldObj.getLoadedEntityList().size();j++){
				if (event.getPlayer().worldObj.loadedEntityList.get(j) instanceof EntityPlayer){
					EntityPlayer player = (EntityPlayer) event.getPlayer().worldObj.loadedEntityList.get(j);
					FCraftExtendedPlayer props = FCraftExtendedPlayer.get(player);
					props.setZodiac(0);	
					playerList.add(player);
				}
			}
			EntityPlayer player = playerList.get(rand.nextInt(playerList.size()));
			int blockX = (int) (player.posX + rand.nextInt(128)-64);
			int blockZ = (int) (player.posZ + rand.nextInt(128)-64);
			int blockY = player.worldObj.getTopSolidOrLiquidBlock(blockX, blockZ);

			player.worldObj.addWeatherEffect(new EntityLightningBolt(player.worldObj, player.posX+blockX, blockY, player.posZ+blockZ));
			player.worldObj.setBlock(blockX, blockY, blockZ, FCraftBlock.blockOre, 5, 3);
			for(int j=0;j<=8;j++){
				int blockXRand = rand.nextInt(2)-1;
				int blockYRand = rand.nextInt(2)-1;
				int blockZRand = rand.nextInt(2)-1;

				player.worldObj.setBlock(blockX+blockXRand, (int) (blockY+blockYRand), blockZ+blockZRand, FCraftBlock.blockOre, 5, 3);
			}
			for(int i=0;i<=20+rand.nextInt(20);i++){
				int blockXRand = rand.nextInt(6)-3;
				int blockZRand = rand.nextInt(6)-3;
				int blockYRand = player.worldObj.getTopSolidOrLiquidBlock(blockX+blockXRand, blockZ+blockZRand);

				player.worldObj.setBlock(blockX+blockXRand, (int) (blockYRand), blockZ+blockZRand, stoneArray[rand.nextInt(stoneArray.length)]);
			}
			player.addChatMessage(new ChatComponentText(blockX+","+blockY+","+blockZ));
		}
	}
}