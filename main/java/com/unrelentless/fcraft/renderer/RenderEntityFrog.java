package com.unrelentless.fcraft.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.unrelentless.fcraft.FantasyCraft;

public class RenderEntityFrog extends Render {

	private IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation(FantasyCraft.MODID+":models/Frog.obj"));
	private ResourceLocation texture = new ResourceLocation(FantasyCraft.MODID + ":textures/entity/Frog.png");

	public RenderEntityFrog() {
		super();
	}

	@Override
	public void doRender(Entity var1, double x, double y, double z,
			float var8, float var9) {
		
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glBlendFunc(770,771);
		GL11.glRotatef(-90F, 1, 0, 0);
		GL11.glScalef(3.0F, 3.0F, 3.0F);
		GL11.glScalef(1.0F,1.0F,1.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		model.renderAll();
		GL11.glDisable(3042);
		GL11.glPopMatrix();

	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		return new ResourceLocation(FantasyCraft.MODID + ":textures/entity/Frog.png");
	}
}
