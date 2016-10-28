package me.plugin.kitpvp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class TokenYML {
	
	static Main main;
	public TokenYML(Main instance){
		main = instance;
	}

	public static YamlConfiguration token = null;
	public static File tokenFile = null;
	
	@SuppressWarnings("deprecation")
	public static void reloadTokens(){
		
	
	if(tokenFile == null){
		tokenFile = new File(Bukkit.getPluginManager().getPlugin("SgKits").getDataFolder(), "token.yml");
	}
	
	token = YamlConfiguration.loadConfiguration(tokenFile);
	
	InputStream defConfigStream = Bukkit.getPluginManager().getPlugin("SgKits").getResource("token.yml");
	if(defConfigStream != null){
		YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
		if(!(tokenFile.exists() || tokenFile.length() == 0L)){
			token.setDefaults(defConfig);
		}
	}
	}
	
	public static FileConfiguration getTokens(){
		
		if(token == null){
			
			reloadTokens();
		}
		return token;
	}
	
	public static void saveTokens(){
		if(token == null || tokenFile == null){
			return;
		}
		try{
			getTokens().save(tokenFile);
		}catch(IOException ex){
			ex.printStackTrace();
			Bukkit.getLogger().log(Level.SEVERE, "Could not save config " + tokenFile, ex);
		}
	}
}
