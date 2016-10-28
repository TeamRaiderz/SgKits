package me.plugin.kitpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.plugin.kitpvp.TokenYML;

public class SgCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage("§4Vain pelaajille!");
			return true;
		}
		
		Player p = (Player) sender;
		if(label.equalsIgnoreCase("token")){
			if(args.length == 0){
				p.sendMessage("§cLiian vähän argumentteja!");
				return true;
			}
			else if (args.length == 1){
					p.sendMessage("§cKäyttötapa: /token set [määrä] (pelaaja)");
					return true;
			}
			else if (args.length == 2){
					p.sendMessage("§cKäyttötapa: /token set [määrä] (pelaaja)");
					return true;
			}
			else if (args.length == 3){
				TokenYML.getTokens().set(p.getName() + ".Token", args[0]);
				return true;
			}	
		}
		
		return true;
	}
	
	

}
