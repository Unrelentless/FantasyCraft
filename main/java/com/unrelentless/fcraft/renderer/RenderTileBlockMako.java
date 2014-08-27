package com.unrelentless.fcraft.renderer;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.unrelentless.fcraft.FantasyCraft;
import com.unrelentless.fcraft.blocks.tiles.TileEntityBlockMako;

public class RenderTileBlockMako extends TileEntitySpecialRenderer  {

	private IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation(FantasyCraft.MODID+":models/Crystal.obj"));
	private ResourceLocation texture = new ResourceLocation(FantasyCraft.MODID + ":textures/blocks/CrystalTest.png");
	IModelCustom[] models = new IModelCustom[8];
	Random rand = new Random();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y,
			double z, float tick) {

		TileEntityBlockMako tile = (TileEntityBlockMako) tileEntity;
		float[] colours = tile.getColour();
		
		GL11.glPushMatrix();
		GL11.glColor3f(colours[0],colours[1],colours[2]);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770,771);
		GL11.glTranslated(x, y, z);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		model.renderAll();
		GL11.glDisable(3042);
		GL11.glPopMatrix();


		for(int i=0;i<tile.getStage();i++){

			models[i] = AdvancedModelLoader.loadModel(new ResourceLocation(FantasyCraft.MODID+":models/Crystal.obj"));

			GL11.glPushMatrix();
			GL11.glColor3f(colours[0],colours[1],colours[2]);
			GL11.glEnable(3042);
			GL11.glBlendFunc(770,771);
			GL11.glTranslated(x, y, z);
			GL11.glTranslated((tile.getTrans()[i][0]), 0D, (tile.getTrans()[i][1]));	
			GL11.glScaled(tile.getScale()[i],tile.getScale()[i], tile.getScale()[i]);
			GL11.glRotated(tile.getAngles()[i][0], tile.getAngles()[i][1], tile.getAngles()[i][2], tile.getAngles()[i][3]);
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			models[i].renderAll();
			GL11.glDisable(3042);
			GL11.glPopMatrix();
		}
	}
}
