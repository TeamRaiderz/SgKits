package me.plugin.kitpvp.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import me.plugin.kitpvp.Main;

public class PlayerJoin {
	
	Main main;
	public PlayerJoin(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		
		main.hubScoreboard(p);
	}

}
