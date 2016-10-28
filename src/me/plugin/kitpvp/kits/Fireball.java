package me.plugin.kitpvp.kits;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.plugin.kitpvp.PacketsUtils;
import me.plugin.kitpvp.main;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;

public class Fireball implements Listener{
	
	public static HashMap<String, Double> cooldownTime = new HashMap<String, Double>();
    public static HashMap<String, BukkitRunnable> cooldownTask = new HashMap<String, BukkitRunnable>();

	private ArrayList<Player> shooter = new ArrayList<Player>();

    main main;
	public Fireball(main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		
		if(p.getItemInHand().getType() == Material.COAL && p.getItemInHand().getItemMeta().getDisplayName().contains("Tulipallo")){
			
			if(cooldownTime.containsKey(p.getName())){
				p.sendMessage("ßcOdota ß4" + cooldownTime.get(p.getName()) + " ßcsekuntia jotta voit k‰ytt‰‰ t‰t‰ uudelleen!");
				return;
			 }
			
			p.launchProjectile(SmallFireball.class);
			shooter.add(p);
			
			cooldownTime.put(p.getName(), (double) 10);
	        cooldownTask.put(p.getName(), new BukkitRunnable() {
	        		@Override
	                public void run() {
	                        cooldownTime.put(p.getName(), cooldownTime.get(p.getName()) - 1);
	                        PacketsUtils.sendActionBar(p, "ßcßlTulipallo ß4" + cooldownTime.get(p.getName()));
	                        if (cooldownTime.get(p.getName()) == 0) {
	                                cooldownTime.remove(p.getName());
	                                cooldownTask.remove(p.getName());
	                                p.sendMessage("ßaVoit k‰ytt‰‰ tulipalloa uudelleen!");
	                                p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1, 0.2f);
	                                cancel();
	                        }else{
	                        	return;
	                        }
	                }
	        });
	        cooldownTask.get(p.getName()).runTaskTimer(main, 20, 20);
		}
		
	}
	
	@EventHandler
	public void onHit(ProjectileHitEvent e){
		if(!(e.getEntity() instanceof SmallFireball)) return;
		
		PacketPlayOutWorldParticles packetParticles = new PacketPlayOutWorldParticles(EnumParticle.LAVA, true, 
				(float) (e.getEntity().getLocation().getX()), (float) (e.getEntity().getLocation().getY()), 
				(float) (e.getEntity().getLocation().getZ()), 0, 0, 0, 20, 40);
		
		for(Player pl : Bukkit.getOnlinePlayers()){
			((CraftPlayer) pl).getHandle().playerConnection.sendPacket(packetParticles);
		}
		
		e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.EXPLODE, 1, 0.2f);
		for(Entity p : e.getEntity().getNearbyEntities(5, 5, 5)){
			if(shooter.contains(p)){
				shooter.remove(p);
				continue;
			}
			p.setVelocity(new Vector(0, 1, 0));
		}
	}
	
	@EventHandler
	 public void noFireballFire(BlockIgniteEvent e){
	    if(e.getCause().equals(IgniteCause.FIREBALL)) e.setCancelled(true);
	    Bukkit.broadcastMessage("Test");
	    
	}
	
}
