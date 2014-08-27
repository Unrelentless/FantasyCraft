package com.unrelentless.fcraft.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

import com.unrelentless.fcraft.FantasyCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class FCraftMateriaGreen extends FCraftItemMateria {

	public FCraftMateriaGreen() {
		super();
		this.setUnlocalizedName("green_materia");	
		setCreativeTab(FantasyCraft.fcraftTabItems);
		this.setMaxStackSize(1);
		setHasSubtypes(true);
	}

	@SideOnly(Side.CLIENT)
	public static IIcon[] icons;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon) {
	icons = new IIcon[4];

		for(int i = 0; i < icons.length; i++) {
			icons[i] = icon.registerIcon(FantasyCraft.MODID + ":GreenMateria");
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int damage) {
		return icons[damage];
	}
	
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < icons.length; i++) {
			ItemStack itemStack = new ItemStack(this, 1, i);
			list.add(itemStack);	
		}
	}	

	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        switch (stack.getItemDamage())
        {
        case 0:
            list.add(StatCollector.translateToLocal("green1"));
            break;
        case 1:
            list.add(StatCollector.translateToLocal("green2"));
            break;
        case 2:
            list.add(StatCollector.translateToLocal("green3"));
            break;
        case 3:
            list.add(StatCollector.translateToLocal("green4"));
            break;
        case 4:
            list.add(StatCollector.translateToLocal("green5"));
            break;
        case 5:
            list.add(StatCollector.translateToLocal("green6"));
            break;
        case 6:
            list.add(StatCollector.translateToLocal("green7"));
            break;
        case 7:
            list.add(StatCollector.translateToLocal("green8"));
            break;
        case 8:
            list.add(StatCollector.translateToLocal("green9"));
            break;
        case 9:
            list.add(StatCollector.translateToLocal("green10"));
            break;
        case 10:
            list.add(StatCollector.translateToLocal("green11"));
            break;
        case 11:
            list.add(StatCollector.translateToLocal("green12"));
            break;
        case 12:
            list.add(StatCollector.translateToLocal("green13"));
            break;
        case 13:
            list.add(StatCollector.translateToLocal("green14"));
            break;
        case 14:
            list.add(StatCollector.translateToLocal("green15"));
            break;
        case 15:
            list.add(StatCollector.translateToLocal("green16"));
            break;
        case 16:
            list.add(StatCollector.translateToLocal("green17"));
            break;
        case 17:
            list.add(StatCollector.translateToLocal("green18"));
            break;
        case 18:
            list.add(StatCollector.translateToLocal("green19"));
            break;
        case 19:
            list.add(StatCollector.translateToLocal("green20"));
            break;
        case 20:
            list.add(StatCollector.translateToLocal("green21"));
            break;
        case 21:
            list.add(StatCollector.translateToLocal("green22"));
            break;
        default:
            list.add(StatCollector.translateToLocal("Unknown"));
            break;
        }
    }
}
