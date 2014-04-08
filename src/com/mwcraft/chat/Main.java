package com.mwcraft.chat;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;



public class Main extends JavaPlugin implements Listener {
	File file;
	File dataFolder;
	public void onEnable(){
		PluginManager pm = getServer().getPluginManager();
		//I had the event in a listener class, but kept getting a NPE
		pm.registerEvents(this, this);
		saveDefaultConfig();
		new Listenerchat(this);
		this.dataFolder = getDataFolder();
	    this.file = new File(this.dataFolder, "ACLog.log");
	    if (!this.dataFolder.exists()) {
	    	this.dataFolder.mkdir();
	      }
	    if (!this.file.exists())
	    	try{
	    		this.file.createNewFile(); } catch (Exception e) { getLogger().info("Error creating log file!");
	    		}
	    }
	public String colorize(String message){
		return ChatColor.translateAlternateColorCodes('&', message);
		}
	}