package me.plugin.kitpvp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class KarmaYML {
	
	static Main main;
	public KarmaYML(Main instance){
		main = instance;
	}

	public static YamlConfiguration karma = null;
	public static File teamFile = null;
	
	@SuppressWarnings("deprecation")
	public static void reloadKarmas(){
		
	
	if(teamFile == null){
		teamFile = new File(Bukkit.getPluginManager().getPlugin("SgKits").getDataFolder(), "karma.yml");
	}
	
	karma = YamlConfiguration.loadConfiguration(teamFile);
	
	InputStream defConfigStream = Bukkit.getPluginManager().getPlugin("SgKits").getResource("karma.yml");
	if(defConfigStream != null){
		YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
		if(!(teamFile.exists() || teamFile.length() == 0L)){
			karma.setDefaults(defConfig);
		}
	}
	}
	
	public static FileConfiguration getKarmas(){
		
		if(karma == null){
			
			reloadKarmas();
		}
		return karma;
	}
	
	public static void saveKarma(){
		if(karma == null || teamFile == null){
			return;
		}
		try{
			getKarmas().save(teamFile);
		}catch(IOException ex){
			ex.printStackTrace();
			Bukkit.getLogger().log(Level.SEVERE, "Could not save config " + teamFile, ex);
		}
	}
}
