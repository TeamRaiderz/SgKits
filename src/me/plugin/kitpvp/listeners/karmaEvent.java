package me.plugin.kitpvp.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.plugin.kitpvp.KarmaYML;

public class karmaEvent implements Listener{
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		FileConfiguration config = KarmaYML.getKarmas();
		Player p = e.getPlayer();
		
		config.set(p.getName() + ".Karma", 0);
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		FileConfiguration config = KarmaYML.getKarmas();
		Player p = e.getEntity();
		
		config.set(p.getName() + ".Karma", 0);
		p.sendMessage("§cKuolit ja sinun karma on nyt §d0§c!");
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		FileConfiguration config = KarmaYML.getKarmas();
		Player p = e.getPlayer();
		
		if(!config.contains(p.getName())){
			config.set(p.getName() + ".Karma", 0);
		}
		
	}
	
	public static void giveKarma(Player p, int i){
		FileConfiguration config = KarmaYML.getKarmas();
		
		config.set(p.getName() + ".Karma", config.getInt(p.getName() + ".Karma", 0) + i);
		KarmaYML.saveKarma();
		p.sendMessage("§aSinä sait§d " + i + "§a karmaa!");
		
	}
	
	public static void takeKarma(Player p, int i){
		FileConfiguration config = KarmaYML.getKarmas();
		
		config.set(p.getName() + ".Karma", config.getInt(p.getName() + ".Karma", 0) + i);
		KarmaYML.saveKarma();
		p.sendMessage("§cMenetit §d" + i + "§c karmaa!");
	}
	
}
