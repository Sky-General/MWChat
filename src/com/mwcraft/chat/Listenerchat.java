package com.mwcraft.chat;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Listenerchat implements Listener {
	Main plugin;
	public Listenerchat(Main plugin){
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
		}
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		String message = e.getMessage();
		Player p = e.getPlayer();

		Player player = e.getPlayer();
		if(player.hasPermission("mw.chat.link")){
			if(plugin.getConfig().contains("links." + e.getMessage().toLowerCase())){
				e.setCancelled(true);
				String link = plugin.getConfig().getString("links." + e.getMessage().toLowerCase());
				String linkname = e.getMessage().trim();
				Bukkit.broadcastMessage(ChatColor.YELLOW + "-----------------------" + ChatColor.GOLD + "[LINK]" + ChatColor.YELLOW + "-----------------------");
				Bukkit.broadcastMessage(ChatColor.GOLD + ("" + linkname.charAt(0)).toUpperCase() + linkname.substring(1).toLowerCase() + ": " + ChatColor.BLUE + link);
				Bukkit.broadcastMessage(ChatColor.YELLOW + "----------------------------------------------------");
			
		}
	}
		
		if(player.hasPermission("mw.chat.alert")){
			if(message.startsWith("!")){
				e.setCancelled(true);
				message = message.replaceFirst("!", "§7[§aAlert§7]§a ");
				for(Player target : Bukkit.getOnlinePlayers()){
					
					BarAPI.setMessage(target, plugin.colorize(message).replaceAll("%player%", target.getName()), 10);
				}
				log(message);
			}
		}
	
		if ((!message.startsWith("@")) || (!p.hasPermission("mw.chat.adminchat"))) return;
		e.setCancelled(true);
		message = message.replaceFirst("@", "");
		for(Player player1 : Bukkit.getOnlinePlayers()){
			if(player1.hasPermission("mw.chat.adminchat")){
				player1.sendMessage(this.plugin.colorize("&8[&a@&8] &a&o" + p.getName() + "&7: " + message));
			}
		}
		e.getPlayer().getServer().getConsoleSender().sendMessage(this.plugin.colorize("&8[&a@&8] &a&o" + p.getName() + "&7: " + message));
		log(p.getName() + ": " + this.plugin.colorize(message.replaceAll("§", "")));
		}
	public void log(String message) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(calendar.getTime());
		try{
			PrintWriter pw = new PrintWriter(new FileWriter(this.plugin.file, true));
			pw.println(time + " - " + message.replaceAll("§", ""));
			pw.flush();
			pw.close();
			}
		catch (Exception localException){
			}
		}
	}