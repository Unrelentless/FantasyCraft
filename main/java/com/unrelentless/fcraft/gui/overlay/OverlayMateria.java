package com.unrelentless.fcraft.gui.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

import org.lwjgl.opengl.GL11;

import com.unrelentless.fcraft.FantasyCraft;
import com.unrelentless.fcraft.extendedprops.FCraftExtendedPlayer;
import com.unrelentless.fcraft.items.weapons.FCraftWeapon;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class OverlayMateria extends Gui
{
	private Minecraft mc;
	ResourceLocation textureLoc = new ResourceLocation(FantasyCraft.MODID + ":" + "textures/gui/GUIOverlay"+".png");
	public OverlayMateria(Minecraft mc)
	{
		super();
		// We need this to invoke the render engine.
		this.mc = mc;
	}
	@SubscribeEvent(priority=EventPriority.NORMAL)
	public void onRenderMateriaOverlay(RenderGameOverlayEvent event)
	{
		EntityPlayer player = mc.thePlayer;
		int numberOfSlots;

		if (event.isCancelable() || event.type != ElementType.EXPERIENCE)
		{
			return;
		}
		// Get our extended player properties and assign it locally so we can easily access it
		FCraftExtendedPlayer props = FCraftExtendedPlayer.get(this.mc.thePlayer);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(2.0F, 1.0F, 2.0F, 1.0F);
		GL11.glDisable(GL11.GL_ALPHA_TEST);

		if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof FCraftWeapon){
			numberOfSlots = player.getCurrentEquippedItem().stackTagCompound.getInteger("CurrentSockets");

			for(int i=0; i<numberOfSlots;i++){	
				int materiaID = player.getCurrentEquippedItem().stackTagCompound.getInteger("SocketContents"+i);
				int materiaMeta = player.getCurrentEquippedItem().stackTagCompound.getInteger("SocketContentsMeta"+i);
				int currentMateriaSelected = props.getCurrentMateria();
				if(materiaID != 0){
					String materiaName = (String) new ItemStack(Item.getItemById(materiaID), 1, materiaMeta).getTooltip(player, true).get(1);
					if(i==currentMateriaSelected){
						this.mc.fontRenderer.drawString(EnumChatFormatting.RED + materiaName, 10, 10+(i*10), 0);	
					}else{
						this.mc.fontRenderer.drawString(EnumChatFormatting.BLACK + materiaName, 10, 10+(i*10), 0);	
					}
				}else{
					this.mc.fontRenderer.drawString("Empty", 10, 10+(i*10), 0);	
				}
			}
		}

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
	}
}
