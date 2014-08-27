package com.unrelentless.fcraft.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import com.unrelentless.fcraft.blocks.FCraftBlock;
import com.unrelentless.fcraft.blocks.tiles.TileEntityBlockMako;
import com.unrelentless.fcraft.entity.FCraftEntityFrog;
import com.unrelentless.fcraft.entity.FCraftEntityOrich;
import com.unrelentless.fcraft.items.weapons.FCraftWeapon;
import com.unrelentless.fcraft.renderer.RenderEntityFrog;
import com.unrelentless.fcraft.renderer.RenderEntityOrichalcum;
import com.unrelentless.fcraft.renderer.RenderTileBlockMako;
import com.unrelentless.fcraft.renderer.item.RenderItemBlockMako;
import com.unrelentless.fcraft.renderer.item.RenderItemBusterSword;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{
	@Override
	public void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockMako.class, new RenderTileBlockMako());
		RenderingRegistry.registerEntityRenderingHandler(FCraftEntityOrich.class, new RenderEntityOrichalcum());
		RenderingRegistry.registerEntityRenderingHandler(FCraftEntityFrog.class, new RenderEntityFrog());

		MinecraftForgeClient.registerItemRenderer(FCraftWeapon.swordBuster, (IItemRenderer)new RenderItemBusterSword());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(FCraftBlock.blockMako), new RenderItemBlockMako(new RenderTileBlockMako(), new TileEntityBlockMako()));
	}
}

