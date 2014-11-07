package com.unrelentless.fcraft.creativetabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class FCraftCreativeTabItem extends CreativeTabs{

	public FCraftCreativeTabItem(int tabID, String label) {
		super(tabID, label);
	}

    @Override
    @SideOnly(Side.CLIENT)
    public String getTranslatedTabLabel()
    {
        return "Fantasy Craft Items";
    }
    
	@Override
	public Item getTabIconItem() {
		// TODO Auto-generated method stub
		return Items.blaze_powder;
	}

}
