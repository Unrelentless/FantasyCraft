package com.unrelentless.fcraft.entity;

import com.unrelentless.fcraft.blocks.FCraftBlock;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class FCraftEntityOrich extends EntityBat{

	public FCraftEntityOrich(World par1World) {
		super(par1World);

	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(0.5D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
	}	

	@Override
	protected void updateAITasks()
	{
		super.updateAITasks();

		for(int i=0;i<this.worldObj.getLoadedEntityList().size();i++){
			if (((Entity)this.worldObj.loadedEntityList.get(i)).getDistanceToEntity(this)<8){
				if (this.worldObj.loadedEntityList.get(i) instanceof EntityPlayer){
					int x = MathHelper.floor_double(this.posX);
					int y = MathHelper.floor_double(this.posY + 0.5D);
					int z = MathHelper.floor_double(this.posZ);
					int l1 = this.rand.nextInt(6);
					Block block = this.worldObj.getBlock(x + Facing.offsetsXForSide[l1], y + Facing.offsetsYForSide[l1], z + Facing.offsetsZForSide[l1]);
					if (block==(Blocks.stone))
					{
						this.worldObj.setBlock(x + Facing.offsetsXForSide[l1], y + Facing.offsetsYForSide[l1],
								z + Facing.offsetsZForSide[l1], Block.getBlockFromItem(new ItemStack(FCraftBlock.blockOre, 1, 1).getItem()), 1, 3);
						this.spawnExplosionParticle();
						this.setDead();
					}else if(block!=Blocks.air){
						this.spawnExplosionParticle();
						this.setDead();
					}

				}
			}
		}
	}
}