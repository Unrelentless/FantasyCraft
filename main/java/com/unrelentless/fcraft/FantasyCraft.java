package com.unrelentless.fcraft;


import java.io.File;
import java.io.IOException;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.input.Keyboard;

import com.unrelentless.fcraft.blocks.FCraftBlock;
import com.unrelentless.fcraft.blocks.tiles.FCraftTileEntity;
import com.unrelentless.fcraft.creativetabs.FCraftCreativeTabBlock;
import com.unrelentless.fcraft.creativetabs.FCraftCreativeTabItem;
import com.unrelentless.fcraft.creativetabs.FCraftCreativeTabWeapons;
import com.unrelentless.fcraft.entity.FCraftEntity;
import com.unrelentless.fcraft.events.ExtendedPropertiesHandler;
import com.unrelentless.fcraft.events.ZodiacEventHandler;
import com.unrelentless.fcraft.handlers.ConfigHandler;
import com.unrelentless.fcraft.handlers.KeybindHandler;
import com.unrelentless.fcraft.items.FCraftItem;
import com.unrelentless.fcraft.items.weapons.FCraftWeapon;
import com.unrelentless.fcraft.network.PacketDispatcher;
import com.unrelentless.fcraft.proxy.CommonProxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = FantasyCraft.MODID, version = FantasyCraft.VERSION)
public class FantasyCraft
{
	public static final String MODID = "fcraft";
	public static final String VERSION = "0.2.5";

	//Keybinds
	@SideOnly(Side.CLIENT)
	public static KeyBinding socketMateria, materia1, materia2, materia3, materia4;

	//says where the client and server 'proxy' code is loaded
	@SidedProxy(clientSide = "com.unrelentless.fcraft.proxy.ClientProxy", serverSide = "com.unrelentless.fcraft.proxy.CommonProxy")
	public static CommonProxy proxy;

	//Set Creative Tabs
	public static CreativeTabs fcraftTabBlocks = new FCraftCreativeTabBlock(CreativeTabs.getNextID(), MODID);
	public static CreativeTabs fcraftTabWeapons = new FCraftCreativeTabWeapons(CreativeTabs.getNextID(), MODID);
	public static CreativeTabs fcraftTabItems = new FCraftCreativeTabItem(CreativeTabs.getNextID(), MODID);
	
	@Instance(MODID)
	public static FantasyCraft instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) throws IOException{

		ConfigHandler.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + FantasyCraft.MODID + File.separator + FantasyCraft.MODID + ".cfg"));	
		FCraftTileEntity.init();
		FCraftItem.init();
		FCraftBlock.init();
		FCraftEntity.init();
		FCraftWeapon.init();
		
		// Remember to register your packets! This applies whether or not you used a
		// custom class or direct implementation of SimpleNetworkWrapper
		PacketDispatcher.registerPackets();
	}
	@EventHandler
	public void load(FMLInitializationEvent event)
	{        
		proxy.registerRenderers();
		
		//keybinding
		socketMateria = new KeyBinding("Open Socketing GUI", Keyboard.KEY_F, "FantasyCraft");
		materia1 = new KeyBinding("Select Materia 1", Keyboard.KEY_Z, "FantasyCraft");
		materia2 = new KeyBinding("Select Materia 2", Keyboard.KEY_X, "FantasyCraft");
		materia3 = new KeyBinding("Select Materia 3", Keyboard.KEY_C, "FantasyCraft");
		materia4 = new KeyBinding("Select Materia 4", Keyboard.KEY_V, "FantasyCraft");
		
		ClientRegistry.registerKeyBinding(socketMateria);
		ClientRegistry.registerKeyBinding(materia1);
		ClientRegistry.registerKeyBinding(materia2);
		ClientRegistry.registerKeyBinding(materia3);
		ClientRegistry.registerKeyBinding(materia4);
		
		//Registering the gui handlers
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new CommonProxy());
		
		//event registration
		FMLCommonHandler.instance().bus().register(new KeybindHandler());
		MinecraftForge.EVENT_BUS.register(new ZodiacEventHandler());
		MinecraftForge.EVENT_BUS.register(new ExtendedPropertiesHandler());

	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {}
}
