package com.unrelentless.fcraft.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
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
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy extends CommonProxy{
	@Override
	public void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlockMako.class, new RenderTileBlockMako());
		RenderingRegistry.registerEntityRenderingHandler(FCraftEntityOrich.class, new RenderEntityOrichalcum());
		RenderingRegistry.registerEntityRenderingHandler(FCraftEntityFrog.class, new RenderEntityFrog());

		MinecraftForgeClient.registerItemRenderer(FCraftWeapon.swordBuster, (IItemRenderer)new RenderItemBusterSword());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(FCraftBlock.blockMako), new RenderItemBlockMako(new RenderTileBlockMako(), new TileEntityBlockMako()));
	}

	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		// Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
		// your packets will not work as expected because you will be getting a
		// client player even when you are on the server!
		// Sounds absurd, but it's true.
		// Solution is to double-check side before returning the player:
		return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
	}
}

