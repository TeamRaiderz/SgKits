package me.plugin.kitpvp.kits;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.plugin.kitpvp.Main;

public class raimbowbeam implements Listener {

	Main main;

	public raimbowbeam(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();

		if (p.getItemInHand().getItemMeta().getDisplayName().contains("§aSat§ceen§bkaa§eri")) {
			Projectile entity = p.launchProjectile(org.bukkit.entity.Fireball.class);
			entity.setShooter(e.getPlayer());

			Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
				public void run() {
					entity.getWorld().playEffect(entity.getLocation(), Effect.POTION_SWIRL, 1);
				}
			}, 20, 20);
		}
	}
}
