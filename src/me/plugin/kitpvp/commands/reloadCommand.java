package me.plugin.kitpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.plugin.kitpvp.main;

public class reloadCommand implements CommandExecutor{

	main main;
	public reloadCommand(main instance) {
		this.main = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage("§cVain pelaajille!");
			return true;
		}
		Player p = (Player) sender;
		if(label.equalsIgnoreCase("kitreload")){
			if(!p.hasPermission("sgkit.reload")){
				p.sendMessage("§cSinulla ei ole oikeuksia tähän komentoon!");
				return true;
			}
			if(args.length == 0){
				p.sendMessage("§cKäyttötapa: /kitreload config | kit");
				return true;
			}
			else if (args.length == 1){
				if(args[0].equalsIgnoreCase("config")){
					main.reloadConfig();
					main.saveConfig();
					p.sendMessage("§aConfig.yml reloadattu!");
				}else if (args[0].equalsIgnoreCase("kit")){
					main.getServer().getPluginManager().disablePlugin(main);
					main.getServer().getPluginManager().enablePlugin(main);
					p.sendMessage("§aPlugin reloadattu!");
				}
			}
		}
		
		return true;
	}
}
