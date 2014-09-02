package com.unrelentless.fcraft.network.packet.server;


import net.minecraft.entity.player.EntityPlayer;

import com.unrelentless.fcraft.network.packet.AbstractMessageHandler;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
/**
 *
 * Handler for messages sent to the SERVER
 * Only allows implementation of {@link AbstractMessageHandler#handleServerMessage handleServerMessage}
 *
 */
public abstract class AbstractServerMessageHandler<T extends IMessage> extends AbstractMessageHandler<T> {
	// implementing a final version of the client message handler both prevents it from
	// appearing automatically and prevents us from ever accidentally overriding it
	public final IMessage handleClientMessage(EntityPlayer player, T message, MessageContext ctx) {
		return null;
	}
}