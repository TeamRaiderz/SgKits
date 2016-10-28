package me.plugin.kitpvp.kits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.plugin.kitpvp.PacketsUtils;
import me.plugin.kitpvp.main;

public class Lightning implements Listener{
	
	main main;
	public Lightning(main instance) {
		this.main = instance;
	}
	
	public static HashMap<String, Double> cooldownTime = new HashMap<String, Double>();
    public static HashMap<String, BukkitRunnable> cooldownTask = new HashMap<String, BukkitRunnable>();
	
    private ArrayList<String> shooted = new ArrayList<String>();
    
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		
		if(p.getItemInHand().getItemMeta().getDisplayName().contains("Salama")){
					
			
			 if(cooldownTime.containsKey(p.getName())){
				 p.sendMessage("ßcOdota ß4" + cooldownTime.get(p.getName()) + " ßcsekuntia jotta voit k‰ytt‰‰ t‰t‰ uudelleen!");
					return;
			 }
				new BukkitRunnable(){

					@Override
					public void run() {
						 if(shooted.contains(p.getName())){
							 cancel();
							 return;
						 }
						
						 shooted.add(p.getName());
						 Block target = p.getTargetBlock((HashSet<Byte>) null, 100);
						 List<Block> blocks = p.getLastTwoTargetBlocks((HashSet<Byte>) null, 100);
						 blocks.add(target);
						 p.getWorld().strikeLightning(target.getLocation());
							p.setLastDamage(0);
							
							cooldownTime.put(p.getName(), (double) 20);
					        cooldownTask.put(p.getName(), new BukkitRunnable() {
					        	@Override
					                public void run() {
					                        cooldownTime.put(p.getName(), cooldownTime.get(p.getName()) - 1);
					                        PacketsUtils.sendActionBar(p, "ßcßlSalama ß4" + cooldownTime.get(p.getName()));
					                        if (cooldownTime.get(p.getName()) == 0) {
					                                cooldownTime.remove(p.getName());
					                                cooldownTask.remove(p.getName());
					                                p.sendMessage("ßaVoit k‰ytt‰‰ salamaa uudelleen!");
					                                p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1, 0.2f);
					                                cancel();
					                                shooted.remove(p.getName());
					                        }else{
					                        	return;
					                        }
					                }
					        });
					        cooldownTask.get(p.getName()).runTaskTimer(main, 20, 20);
						 
					}
					
				}.runTaskLater(main, 100);
		}
			
		}
	}
