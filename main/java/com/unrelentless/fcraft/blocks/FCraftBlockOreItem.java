package com.unrelentless.fcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class FCraftBlockOreItem extends ItemBlock{

	public FCraftBlockOreItem(Block block) {
		super(block);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		int i = itemstack.getItemDamage();
		if(i<0 || i>=FCraftBlockOre.subBlocks.length){
			i=0;
		}
		return super.getUnlocalizedName() + "." + FCraftBlockOre.subBlocks[i];
		
	}
}
