package com.unrelentless.fcraft.renderer.item;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.unrelentless.fcraft.FantasyCraft;
import com.unrelentless.fcraft.model.ModelNirvana;


public class RenderItemNirvana implements IItemRenderer {

	protected ModelNirvana nirvanaModel;
	private ResourceLocation texture = new ResourceLocation(FantasyCraft.MODID + ":textures/items/Nirvana.png");

	public RenderItemNirvana()
	{
		nirvanaModel = new ModelNirvana();
	}
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type){
		case EQUIPPED: return true;
		case EQUIPPED_FIRST_PERSON: return true;
		case INVENTORY: return true;
		case ENTITY: return true;
		default: return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		switch (type) {
		case ENTITY: {
			return (helper == ItemRendererHelper.ENTITY_BOBBING ||
					helper == ItemRendererHelper.ENTITY_ROTATION ||
					helper == ItemRendererHelper.BLOCK_3D);
		}
		case INVENTORY: {
			return (helper == ItemRendererHelper.ENTITY_BOBBING ||
					helper == ItemRendererHelper.ENTITY_ROTATION ||
					helper == ItemRendererHelper.INVENTORY_BLOCK ||
					helper == ItemRendererHelper.BLOCK_3D);
		}
		default: {
			return false;
		}
		}
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		ResourceLocation location = texture;
		Minecraft.getMinecraft().renderEngine.bindTexture(location);

		switch(type){
		case EQUIPPED:{

			GL11.glPushMatrix();

			GL11.glScalef(9.0F, 9.0F, 9.0F);
			GL11.glRotatef(-90F, 1, 0, 0);
			GL11.glRotatef(-90F, 0, 0, 1);
			GL11.glRotatef(-40F, -1, 0, 0);
			GL11.glTranslatef(0F, 0.07F, -0.2F);

			nirvanaModel.render();
			GL11.glPopMatrix();
			break;
		}
		case EQUIPPED_FIRST_PERSON:{			
			GL11.glPushMatrix();

			GL11.glScalef(10.0F, 10.0F, 10.0F);
			GL11.glRotatef(-90F, 0, 1, 0);
			GL11.glTranslatef(0.1F, -0.1F, -0.25F);
			GL11.glRotatef(-60F, 1, 0, 0);

			nirvanaModel.render();
			GL11.glPopMatrix();
			break;
		}
		case INVENTORY:{
			GL11.glPushMatrix();
			
			GL11.glScalef(5.0F, 5.0F, 5.0F);
			GL11.glRotatef(-90F, 1, 0, 0);	
			GL11.glRotatef(35F, 1, 0, 0);
			GL11.glTranslatef(0.1F, -0.05F, -0.1F);
			
			nirvanaModel.render();
			GL11.glPopMatrix();
			break;
		}
		case ENTITY:{
			GL11.glPushMatrix();
			
			GL11.glScalef(8.0F, 8.0F, 8.0F);
			GL11.glRotatef(-90F, 1, 0, 0);	
			
			nirvanaModel.render();
			GL11.glPopMatrix();
			break;
		}
		default: break;
		}

	}
}
