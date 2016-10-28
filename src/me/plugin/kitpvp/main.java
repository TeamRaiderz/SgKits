package me.plugin.kitpvp;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import me.plugin.kitpvp.commands.Balance;
import me.plugin.kitpvp.commands.SgCommand;
import me.plugin.kitpvp.commands.reloadCommand;
import me.plugin.kitpvp.kits.Fireball;
import me.plugin.kitpvp.kits.Lightning;
import me.plugin.kitpvp.kits.raimbowbeam;
import me.plugin.kitpvp.listeners.karmaEvent;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin{
	
	public void onEnable(){
		System.out.println("SgKits laitettu p‰‰lle!");
		
		//REGISTERING THE LISTENERS
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new GUI(), this);
		pm.registerEvents(new Lightning(this), this);
		pm.registerEvents(new Fireball(this), this);
		pm.registerEvents(new karmaEvent(), this);
		pm.registerEvents(new raimbowbeam(this), this);
		
		//REGISTERING THE COMMANDS
		getCommand("kitreload").setExecutor(new reloadCommand(this));
		getCommand("tokenit").setExecutor(new Balance());
		getCommand("karmat").setExecutor(new Balance());
		getCommand("bal").setExecutor(new Balance());
		getCommand("balance").setExecutor(new Balance());
		getCommand("token").setExecutor(new SgCommand());
		
		
		TokenYML.getTokens().options().copyDefaults(true);
		TokenYML.saveTokens();
		KarmaYML.getKarmas().options().copyDefaults(true);
		KarmaYML.saveKarma();
	}
	public void onDisable(){
		System.out.println("SgKits laitettu pois p‰‰lt‰!");
	}
	
	public void hubScoreboard(Player p){
		
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		
		Objective obj = board.registerNewObjective("board", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("ßcßlWizardßfßlMC");
		
		Team players = board.registerNewTeam("players");
		players.addEntry(ChatColor.BLACK.toString());
		players.setPrefix("ßbßlPelaajat: ");
		players.setSuffix("ßf");
		obj.getScore(ChatColor.BLACK.toString()).setScore(16);
		p.setScoreboard(board);
		
		Team level = board.registerNewTeam("level");
		level.addEntry(ChatColor.DARK_AQUA.toString());
		level.setPrefix("ß2ßlLevel: ");
		level.setSuffix("ßf0");
		obj.getScore(ChatColor.DARK_AQUA.toString()).setScore(15);
		p.setScoreboard(board);
		
		Team tokens = board.registerNewTeam("tokens");
		tokens.addEntry(ChatColor.LIGHT_PURPLE.toString());
		tokens.setPrefix("ß6ßlTokenit: ");
		tokens.setSuffix("ßf" + TokenYML.getTokens().getInt(p.getName() + ".Token"));
		obj.getScore(ChatColor.LIGHT_PURPLE.toString()).setScore(14);
		p.setScoreboard(board);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			public void run() {
				
				board.getTeam("players").setSuffix("ßf" + Bukkit.getOnlinePlayers().size());
				board.getTeam("level").setSuffix("ßf0");
				board.getTeam("tokens").setSuffix("ßf" + TokenYML.getTokens().getInt(p.getName() + ".Token"));
				
				p.setScoreboard(board);
				
			}
			
		}, 20, 40);
		
	}
	
	public void gameScoreboard(Player p){
		
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		
		Objective obj = board.registerNewObjective("board", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("ßcßlWizardßfßlMC");
		
		Team kills = board.registerNewTeam("kills");
		kills.addEntry(ChatColor.BLACK.toString());
		kills.setPrefix("ßaßlKills: ");
		kills.setSuffix("ßf0");
		obj.getScore(ChatColor.BLACK.toString()).setScore(16);
		p.setScoreboard(board);
		
		Team karma = board.registerNewTeam("karma");
		karma.addEntry(ChatColor.LIGHT_PURPLE.toString());
		karma.setPrefix("ßdßlKarmat: ");
		karma.setSuffix("ßf" + KarmaYML.getKarmas().getInt(p.getName() + ".Karma"));
		obj.getScore(ChatColor.LIGHT_PURPLE.toString()).setScore(15);
		p.setScoreboard(board);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			public void run() {
				
				board.getTeam("kills").setSuffix("ßf0");
				board.getTeam("karma").setSuffix("ßf" + KarmaYML.getKarmas().getInt(p.getName() + ".Karma"));

				p.setScoreboard(board);
				
			}
			
		}, 20, 40);
		
	}
	
}
