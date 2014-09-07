package com.unrelentless.fcraft.handlers.materia;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import com.unrelentless.fcraft.extendedprops.FCraftExtendedPlayer;

public class GreenMateriaHandler {

	FCraftExtendedPlayer props;

	public GreenMateriaHandler(EntityPlayer player){

		World world = player.worldObj;
		props = FCraftExtendedPlayer.get(player);
		int currentlySelectedMateria = props.getCurrentMateria();
		int materiaID = player.getCurrentEquippedItem().stackTagCompound.getInteger("SocketContents"+currentlySelectedMateria);
		int materiaMeta = player.getCurrentEquippedItem().stackTagCompound.getInteger("SocketContentsMeta"+currentlySelectedMateria);

		if(props.getJob() == 0){
			switch(materiaMeta){
			case 0: fire(world, player); return;
			/*	case 1: ice(world, player); return;
			case 2: earth(world, player); return;
			case 3: lightning(world, player); return;
			case 4: bio(world, player); return;
			case 5: death(world, player); return;
			case 6: silence(world, player); return;
			case 7: confuse(world, player); return;
			case 8: mini(world, player); return;
			case 9: slow(world, player); return;
			case 10: gravity(world, player); return;*/
			default:
			}
		}else{
			player.addChatMessage(new ChatComponentText("You cannot cast this!"));
		}	
	}

	private void fire(World world, EntityPlayer player){
		props = FCraftExtendedPlayer.get(player);
		if(props.consumeMana(15) || player.capabilities.isCreativeMode){
			if(player.isSneaking()){
				MovingObjectPosition movingObject = getMovingEntity(player, world);
				int i = movingObject.blockX;
				int j = movingObject.blockY;
				int k = movingObject.blockZ;
				world.setBlock(i, j+1, k, Blocks.fire);
			}
		}else{
			player.addChatMessage(new ChatComponentText("Not enough mana."));
		}
	}


	private MovingObjectPosition getMovingEntity(EntityPlayer player, World world){
		float f = 1.0F;
		float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
		float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
		double d = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
		double d1 = (player.prevPosY + (player.posY - player.prevPosY) * (double)f + 1.6200000000000001D) - (double)player.yOffset;
		double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
		Vec3 vec3d = Vec3.createVectorHelper(d, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
		float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
		float f5 = -MathHelper.cos(-f1 * 0.01745329F);
		float f6 = MathHelper.sin(-f1 * 0.01745329F);
		float f7 = f4 * f5;
		float f8 = f6;
		float f9 = f3 * f5;
		double d3 = 5000D;
		Vec3 vec3d1 = vec3d.addVector((double)f7 * d3, (double)f8 * d3 + 1, (double)f9 * d3);
		MovingObjectPosition movingobjectposition = world.rayTraceBlocks(vec3d, vec3d1, false);
		return movingobjectposition;
	}
}
