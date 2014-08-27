package com.unrelentless.fcraft.handlers;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigHandler {

	static Configuration config = new Configuration();

	public static void init(File file){

		config = new Configuration(file);

		try{

			config.load();

		}

		catch(Exception e){

			System.out.println("Config shat itself.");
		}

		finally{

			config.save();
		}
	}
}