package com.unrelentless.fcraft.handlers;

import net.minecraft.client.Minecraft;

import com.unrelentless.fcraft.FantasyCraft;
import com.unrelentless.fcraft.gui.GuiSocket;
import com.unrelentless.fcraft.items.weapons.FCraftWeapon;
import com.unrelentless.fcraft.packets.OpenGuiPacket;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;


public class KeybindHandler {

	Minecraft mc;

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {

		if(FantasyCraft.socketMateria.isPressed() &&  FMLClientHandler.instance().getClient().inGameHasFocus) {
			if(FMLClientHandler.instance().getClientPlayerEntity().getCurrentEquippedItem() != null && FMLClientHandler.instance().getClientPlayerEntity().getCurrentEquippedItem().getItem() instanceof FCraftWeapon){
				FantasyCraft.packetPipeline.sendToServer(new OpenGuiPacket(GuiSocket.GUI_ID));
			}
		}
	}
}
