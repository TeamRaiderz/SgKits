package me.plugin.kitpvp.kits;

import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.plugin.kitpvp.Main;

public class Rainbow implements Listener{
	
	Main main;
	public Rainbow(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		
		Player p = e.getPlayer();
		
		if(p.getItemInHand().getItemMeta().getDisplayName().contains("�aSat�ceen�bkaa�eri")){
			
			Projectile pr = p.launchProjectile(Snowball.class);
			
			new BukkitRunnable(){
				
				public void run() {
					pr.getWorld().playEffect(pr.getLocation(), Effect.POTION_SWIRL, 1);
					
				}
				
			}.runTaskTimer(main, 1, 1);
		}
		
	}

}
