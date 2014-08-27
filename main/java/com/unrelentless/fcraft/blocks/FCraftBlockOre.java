package com.unrelentless.fcraft.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.unrelentless.fcraft.FantasyCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FCraftBlockOre extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon[] texture;

	final static String[] subBlocks = {"mythrilore", "orichore", "zincore", "platinumore", "damascusore", "zodiacore", "einore", "scarletore"};

	public FCraftBlockOre(){
		super(Material.rock);

		this.setHardness(5.0F);
		this.setResistance(5.0F);
		this.setCreativeTab(FantasyCraft.fcraftTabBlocks);
		this.setBlockName("ore");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister ir){
		texture = new IIcon[subBlocks.length];

		for(int i=0;i<subBlocks.length;i++){
			texture[i] = ir.registerIcon(FantasyCraft.MODID+":Ore"+(i+1));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item block, CreativeTabs tabs, List list) {
		for (int i = 0; i < subBlocks.length; i++) {
			ItemStack itemStack = new ItemStack(this, 1, i);
			list.add(itemStack);	
		}
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return texture[meta];
	}

	@Override
	public int damageDropped (int metadata) {
		return metadata;
	}

}
