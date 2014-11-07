package com.unrelentless.fcraft.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.unrelentless.fcraft.FantasyCraft;
import com.unrelentless.fcraft.inventory.ContainerSocket;
import com.unrelentless.fcraft.inventory.InventorySocket;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSocket extends GuiContainer
{
	public static final int GUI_ID = 21;
	
	/** x size of the inventory window in pixels. Defined as float, passed as int */
	private float xSize_lo;
	/** y size of the inventory window in pixels. Defined as float, passed as int. */
	private float ySize_lo;
	/** Normally I use '(ModInfo.MOD_ID, "textures/...")', but it can be done this way as well */
	private static final ResourceLocation iconLocation = new ResourceLocation(FantasyCraft.MODID + ":textures/gui/socketingGui.png");
	/** Could use IInventory type to be more generic, but this way will save an import... */
	private final InventorySocket inventory;
	
	
	public GuiSocket(EntityPlayer player, InventoryPlayer inventoryPlayer, InventorySocket inventoryCustom) {
		super(new ContainerSocket(player, inventoryPlayer, inventoryCustom));
		// if you need the player for something later on, store it in a local variable here as well
		this.inventory = inventoryCustom;
	}
	
	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		super.drawScreen(mouseX, mouseY, f);
	}

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of the items)
	 */
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		// This method will simply draw inventory's name on the screen - you could do without it entirely
		// if that's not important to you, since we are overriding the default inventory rather than
		// creating a specific type of inventory
		//		/String s = inventory.hasCustomInventoryName() ? inventory.getInventoryName() : I18n.format(inventory.getInventoryName());
		EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
		int numOfSockets = player.getCurrentEquippedItem().stackTagCompound.getInteger("CurrentSockets");

		for(int i=0;i<numOfSockets;i++){
			if(this.inventory.getStackInSlot(i) != null){
				int materiaID = Item.getIdFromItem(this.inventory.getStackInSlot(i).getItem());
				int materiaMeta = this.inventory.getStackInSlot(i).getItemDamage();
				String s = (String) new ItemStack(Item.getItemById(materiaID), 1, materiaMeta).getTooltip(player, true).get(1);
				fontRendererObj.drawString(s, xSize - fontRendererObj.getStringWidth(s)/2-(174/2), 5+(i*21), 4210752);
			}else{
				fontRendererObj.drawString("--", xSize - fontRendererObj.getStringWidth("--")/2-(174/2), 5+(i*21), 4210752);
			}
			fontRendererObj.drawString("Socketing", xSize - fontRendererObj.getStringWidth("Socketing")/2-(174/2), -25, 0xFFFF0000);
			fontRendererObj.drawString("Active", 5, -15, 4210752);
			fontRendererObj.drawString("Support", 132, -15, 4210752);
		}
	}

	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(iconLocation);
		ItemStack itemStack = FMLClientHandler.instance().getClientPlayerEntity().getCurrentEquippedItem();
		int k = (this.width - 256) / 2;
		int l = (this.height - 230) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, 256, 230);
		for(int i=0;i<itemStack.stackTagCompound.getInteger("CurrentSockets");i++){
			this.drawTexturedModalRect(k+50, l+31+(i*21), 0, 238, 18, 18);
			this.drawTexturedModalRect(k+188, l+31+(i*21), 0, 238, 18, 18);
		}		
	}
}