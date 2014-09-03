package com.unrelentless.fcraft.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

import com.unrelentless.fcraft.blocks.FCraftBlock;
import com.unrelentless.fcraft.blocks.tiles.TileEntityBlockMako;
import com.unrelentless.fcraft.entity.FCraftEntityFrog;
import com.unrelentless.fcraft.entity.FCraftEntityOrich;
import com.unrelentless.fcraft.gui.overlay.OverlayMateria;
import com.unrelentless.fcraft.items.weapons.FCraftWeapon;
import com.unrelentless.fcraft.renderer.RenderEntityFrog;
import com.unrelentless.fcraft.renderer.RenderEntityOrichalcum;
import com.unrelentless.fcraft.renderer.RenderTileBlockMako;
import com.unrelentless.fcraft.renderer.item.RenderItemBlockMako;
import com.unrelentless.fcraft.renderer.item.RenderItemBusterSword;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy extends CommonProxy{
	@Override
	public void registerRenderers() {
		
		//Bind Tile Entities
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockMako.class, new RenderTileBlockMako());
		
		//Register Entities
		RenderingRegistry.registerEntityRenderingHandler(FCraftEntityOrich.class, new RenderEntityOrichalcum());
		RenderingRegistry.registerEntityRenderingHandler(FCraftEntityFrog.class, new RenderEntityFrog());

		//Register Models
		MinecraftForgeClient.registerItemRenderer(FCraftWeapon.swordBuster, (IItemRenderer)new RenderItemBusterSword());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(FCraftBlock.blockMako), new RenderItemBlockMako(new RenderTileBlockMako(), new TileEntityBlockMako()));
	
		//Register overlays
		MinecraftForge.EVENT_BUS.register(new OverlayMateria(Minecraft.getMinecraft()));

	}

	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		//check whether client side, send client.
		return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
	}
}

