package com.mwcraft.chat;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	public void onEnable(){
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new pluginListener(), this /*I am doing this, because idk if I do the same as listener... */);
	}

}
