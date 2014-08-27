package com.unrelentless.fcraft.renderer.item;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.unrelentless.fcraft.FantasyCraft;
import com.unrelentless.fcraft.blocks.tiles.TileEntityBlockMako;
import com.unrelentless.fcraft.renderer.RenderTileBlockMako;

public class RenderItemBlockMako implements IItemRenderer {

	private IModelCustom model;
	private ResourceLocation texture = new ResourceLocation(FantasyCraft.MODID + ":textures/blocks/CrystalTest.png");

	public RenderItemBlockMako(RenderTileBlockMako renderTileBlockMako, TileEntityBlockMako tileEntityBlockMako) {
		model = AdvancedModelLoader.loadModel(new ResourceLocation(FantasyCraft.MODID+":models/Crystal.obj"));

	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {

		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {

		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		ResourceLocation location = texture;
		Minecraft.getMinecraft().renderEngine.bindTexture(location);

		switch(type){
		case EQUIPPED:{

			GL11.glPushMatrix();
			model.renderAll();
			GL11.glPopMatrix();
			break;
		}
		case EQUIPPED_FIRST_PERSON:{			
			GL11.glPushMatrix();
			model.renderAll();
			GL11.glPopMatrix();
			break;
		}
		case INVENTORY:{
			GL11.glPushMatrix();
			GL11.glTranslatef(0F, -0.4F, 0);
			model.renderAll();
			GL11.glPopMatrix();
			break;
		}
		case ENTITY:{
			GL11.glPushMatrix();
			GL11.glTranslatef(0F, -0.4F, 0);
			model.renderAll();
			GL11.glPopMatrix();
			break;
		}
		default: break;
		}

	}
}