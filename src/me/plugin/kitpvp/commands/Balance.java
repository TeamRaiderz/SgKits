package me.plugin.kitpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import me.plugin.kitpvp.KarmaYML;
import me.plugin.kitpvp.TokenYML;

public class Balance implements CommandExecutor{
	
	ConfigurationSection kConfig = KarmaYML.getKarmas();
	ConfigurationSection tConfig = TokenYML.getTokens();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage("§4Vain pelaajilla voi olla rahaa!");
			return true;
		}
		Player p = (Player) sender;
		if(label.equalsIgnoreCase("tokenit")){
			tConfig.getInt(p.getName()  + ".Token");
		}
		else if (label.equalsIgnoreCase("karmat")){
			kConfig.getInt(p.getName() + ".Karma");
		}
		else if (label.equalsIgnoreCase("bal") || label.equalsIgnoreCase("balance")){
			if(args.length == 0){
				p.sendMessage("§cKäyttötapa: /balance karma | token tai /bal karma | token !");
				return true;
			}
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("karma")){
					tConfig.getInt(p.getName()  + ".Token");
				}
				else if (args[0].equalsIgnoreCase("token")){
					kConfig.getInt(p.getName() + ".Karma");
				}
			}
			
		}
		
		return true;
	}

}
