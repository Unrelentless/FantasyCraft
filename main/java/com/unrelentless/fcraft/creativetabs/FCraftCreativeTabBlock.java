package com.unrelentless.fcraft.creativetabs;

import com.unrelentless.fcraft.blocks.FCraftBlock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class FCraftCreativeTabBlock extends CreativeTabs{

	public FCraftCreativeTabBlock(int tabID, String label) {
		super(tabID, label);
	}

    @Override
    @SideOnly(Side.CLIENT)
    public String getTranslatedTabLabel()
    {
        return "Fantasy Craft Blocks";
    }
    
	@Override
	public Item getTabIconItem() {
		// TODO Auto-generated method stub
		return Item.getItemFromBlock(FCraftBlock.blockMako);
	}

}
