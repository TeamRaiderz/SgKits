package me.plugin.kitpvp.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import me.plugin.kitpvp.TokenYML;

public class tokenEvent implements Listener{

	FileConfiguration config = TokenYML.getTokens();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		
		if(!config.contains(p.getName())){
			config.set(p.getName() + ".Token", 0);
		}
		
	}
	
	public static void giveTokens(Player p, int i){
		FileConfiguration config = TokenYML.getTokens();
		
		config.set(p.getName() + ".Token", config.getInt(p.getName() + ".Token", 0) + i);
		TokenYML.saveTokens();
		p.sendMessage("§aSinä sait§6 " + i + "§a tokenia!");
		
	}
	
	public static void takeTokens(Player p, int i){
		FileConfiguration config = TokenYML.getTokens();
		
		config.set(p.getName() + ".Token", config.getInt(p.getName() + ".Token", 0) + i);
		TokenYML.saveTokens();
		p.sendMessage("§cMenetit §6" + i + "§c tokenia!");
	}

}
