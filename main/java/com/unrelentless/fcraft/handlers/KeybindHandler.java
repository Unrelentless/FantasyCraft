package com.unrelentless.fcraft.handlers;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;

import com.unrelentless.fcraft.FantasyCraft;
import com.unrelentless.fcraft.extendedprops.FCraftExtendedPlayer;
import com.unrelentless.fcraft.gui.GuiSocket;
import com.unrelentless.fcraft.items.weapons.FCraftWeapon;
import com.unrelentless.fcraft.network.PacketDispatcher;
import com.unrelentless.fcraft.network.packet.server.OpenGuiMessage;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;


public class KeybindHandler {

	Minecraft mc;

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {

		EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
		FCraftExtendedPlayer props = FCraftExtendedPlayer.get(player);

		int materiaID;
		int materiaMeta;
		String materiaName = "No materia in selected socket.";

		if(FMLClientHandler.instance().getClient().inGameHasFocus){

			if(FantasyCraft.socketMateria.isPressed()) {
				if(FMLClientHandler.instance().getClientPlayerEntity().getCurrentEquippedItem() != null && FMLClientHandler.instance().getClientPlayerEntity().getCurrentEquippedItem().getItem() instanceof FCraftWeapon){
					PacketDispatcher.sendToServer(new OpenGuiMessage(GuiSocket.GUI_ID));
				}
			}

			if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof FCraftWeapon){

				int numOfSockets = player.getCurrentEquippedItem().stackTagCompound.getInteger("CurrentSockets");

				if(FantasyCraft.materia1.isPressed()){
					materiaID = player.getCurrentEquippedItem().stackTagCompound.getInteger("SocketContents"+0);
					materiaMeta = player.getCurrentEquippedItem().stackTagCompound.getInteger("SocketContentsMeta"+0);
					if(materiaID != 0){
						materiaName = (String) new ItemStack(Item.getItemById(materiaID), 1, materiaMeta).getTooltip(player, true).get(1);
						props.setCurrentMateria(0);
						player.addChatMessage(new ChatComponentText(materiaName+" selected."));
					}else{
						player.addChatMessage(new ChatComponentText(materiaName));
					}
				}else if(FantasyCraft.materia2.isPressed() && numOfSockets>=2){
					materiaID = player.getCurrentEquippedItem().stackTagCompound.getInteger("SocketContents"+1);
					materiaMeta = player.getCurrentEquippedItem().stackTagCompound.getInteger("SocketContentsMeta"+1);
					if(materiaID != 0){
						materiaName = (String) new ItemStack(Item.getItemById(materiaID), 1, materiaMeta).getTooltip(player, true).get(1);
						props.setCurrentMateria(1);
						player.addChatMessage(new ChatComponentText(materiaName+" selected."));
					}else{
						player.addChatMessage(new ChatComponentText(materiaName));
					}
				}else if(FantasyCraft.materia3.isPressed() && numOfSockets>=3){
					materiaID = player.getCurrentEquippedItem().stackTagCompound.getInteger("SocketContents"+2);
					materiaMeta = player.getCurrentEquippedItem().stackTagCompound.getInteger("SocketContentsMeta"+2);
					if(materiaID != 0){
						materiaName = (String) new ItemStack(Item.getItemById(materiaID), 1, materiaMeta).getTooltip(player, true).get(1);
						props.setCurrentMateria(2);
						player.addChatMessage(new ChatComponentText(materiaName+" selected."));
					}else{
						player.addChatMessage(new ChatComponentText(materiaName));
					}
				}else if(FantasyCraft.materia4.isPressed() && numOfSockets>=4){
					materiaID = player.getCurrentEquippedItem().stackTagCompound.getInteger("SocketContents"+3);
					materiaMeta = player.getCurrentEquippedItem().stackTagCompound.getInteger("SocketContentsMeta"+3);
					if(materiaID != 0){
						materiaName = (String) new ItemStack(Item.getItemById(materiaID), 1, materiaMeta).getTooltip(player, true).get(1);
						props.setCurrentMateria(3);
						player.addChatMessage(new ChatComponentText(materiaName+" selected."));
					}else{
						player.addChatMessage(new ChatComponentText(materiaName));
					}
				}
			}
		}
	}
}