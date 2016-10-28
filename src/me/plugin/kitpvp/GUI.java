package me.plugin.kitpvp;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI implements Listener{
	
	private Inventory inv;
	
	//Päävalikko
	public void openGUI(Player p){
		
		this.inv = Bukkit.createInventory(null, InventoryType.CHEST, "§c§lLoitsuvalikko");
		
		ItemStack limeIkkuna = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
		ItemMeta limeta = limeIkkuna.getItemMeta();
		limeta.setDisplayName("§r");
		limeIkkuna.setItemMeta(limeta);
		
		ItemStack attack = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta am = attack.getItemMeta();
		am.setDisplayName("§cAttack");
		am.setLore(Arrays.asList("§7Hyökkäys loitsut"));
		attack.setItemMeta(am);
		
		ItemStack support = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta sm = support.getItemMeta();
		sm.setDisplayName("§aSupport");
		sm.setLore(Arrays.asList("§7Avustavat loitsut"));
		support.setItemMeta(sm);
		
		ItemStack misc = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta mm = misc.getItemMeta();
		mm.setDisplayName("§5Misc");
		mm.setLore(Arrays.asList("§7Sekalaiset loitsut"));
		misc.setItemMeta(mm);
		
		inv.setItem(0, limeIkkuna);
		inv.setItem(1, limeIkkuna);
		inv.setItem(2, limeIkkuna);
		inv.setItem(3, limeIkkuna);
		inv.setItem(4, limeIkkuna);
		inv.setItem(5, limeIkkuna);
		inv.setItem(6, limeIkkuna);
		inv.setItem(7, limeIkkuna);
		inv.setItem(8, limeIkkuna);
		
		inv.setItem(11, attack);
		inv.setItem(13, support);
		inv.setItem(15, misc);
		
		inv.setItem(18, limeIkkuna);
		inv.setItem(19, limeIkkuna);
		inv.setItem(20, limeIkkuna);
		inv.setItem(21, limeIkkuna);
		inv.setItem(22, limeIkkuna);
		inv.setItem(23, limeIkkuna);
		inv.setItem(24, limeIkkuna);
		inv.setItem(25, limeIkkuna);
		inv.setItem(26, limeIkkuna);
		
		p.openInventory(inv);
		
	}
	
	//ATTACK LOITSU-VALIKKO
	public void openAttackGUI(Player p){
		
		this.inv = Bukkit.createInventory(null, 54, "§c§lLoitsut (Hyökkäys)");
		
		//Tulipallo
		ItemStack fireball = new ItemStack(Material.COAL);
		ItemMeta fmeta = fireball.getItemMeta();
		fmeta.setDisplayName("§c§lTulipallo");
		fmeta.setLore(Arrays.asList("§7Ammu tulipallo sinne", "§7minne katsot!", "", "§aOta loitsu käyttöön klikkaamllla!", "", "§7Cooldown: §c10s"));
		fireball.setItemMeta(fmeta);
		
		ItemStack rainbow = new ItemStack(Material.INK_SACK, 1, (byte) 10);
		ItemMeta rbmeta = rainbow.getItemMeta();
		rbmeta.setDisplayName("§aSat§ceen§bkaa§eri");
		rbmeta.setLore(Arrays.asList("§7Ammu sateenkaarisäde sinne", "§7minne katsot!", "", "§aOta loitsu käyttöön klikkaamllla!", "", "§7Cooldown: §c10s"));
		rainbow.setItemMeta(rbmeta);
		
		//Salama
		ItemStack lightning = new ItemStack(Material.INK_SACK, 1, (byte) 12);
		ItemMeta lmeta = lightning.getItemMeta();
			lmeta.setDisplayName("§f§lSalama");
			lmeta.setLore(Arrays.asList("§7Ammu salama siihen", "§7blockiin mitä klikkasit", "", "§aOta loitsu käyttöön klikkaamllla!", "", "§7Cooldown: §c20s"));
		lightning.setItemMeta(lmeta);
		
		inv.setItem(0, lightning);
		inv.setItem(1, fireball);
		inv.setItem(2, rainbow);
		
		p.openInventory(inv);
	}
	//SEKALAIS LOITSU-VALIKKO
	public void openMiscGUI(Player p){
		
		this.inv = Bukkit.createInventory(null, 54, "§c§lLoitsut (Sekalainen)");
		
		p.openInventory(inv);
	}
	//AVUSTAVAT LOITSUT-VALIKKO
	public void openSupportGUI(Player p){
		
		this.inv = Bukkit.createInventory(null, 54, "§c§lLoitsut (Avustavat)");
		
			//Jäädytys
			ItemStack jäädytys = new ItemStack(Material.GOLDEN_CARROT);
			ItemMeta jmeta = jäädytys.getItemMeta();
			if(!p.hasPermission("sg.kit.jaadytys")){
				jmeta.setDisplayName("§b§lJäädytys");
				jmeta.setLore(Arrays.asList("§7Jäädytä toinen pelaaja!", "", "§aOta loitsu käyttöön klikkaamllla!"));
			jäädytys.setItemMeta(jmeta);

			//Jääkupoli
			ItemStack jääkupoli = new ItemStack(Material.EYE_OF_ENDER);
			ItemMeta jkmeta = jääkupoli.getItemMeta();
				jkmeta.setDisplayName("§b§lJääkupoli");
				jkmeta.setLore(Arrays.asList("§7Luo jääkupoli", "§7sinne minne katsot!", "", "§aOta loitsu käyttöön klikkaamllla!"));
			jääkupoli.setItemMeta(jkmeta);
			
			//Healing
			ItemStack healing = new ItemStack(Material.QUARTZ);
			ItemMeta hmeta = healing.getItemMeta();
				hmeta.setDisplayName("§cParannus");
				hmeta.setLore(Arrays.asList("§7Anna itsellesi regeneration-", "§7efekti hetkeksi!", "", "§aOta loitsu käyttöön klikkaamllla!"));
			healing.setItemMeta(hmeta);
		
		
			inv.setItem(0, jäädytys);
			inv.setItem(1, jääkupoli);
			inv.setItem(2, healing);
			
		p.openInventory(inv);
			}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		if(p.getItemInHand().getType() == Material.ENCHANTED_BOOK){
			e.setCancelled(true);
			openGUI(p);
		}
		else{
			return;
		}
		
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		
		ItemStack attack = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta am = attack.getItemMeta();
		am.setDisplayName("§cLoitsuvalikko");
		am.setLore(Arrays.asList("§7Klikkaa saadaksesi valikon ", "§7saatavilla olevista loitsuista!"));
		attack.setItemMeta(am);
		
		p.getInventory().setItem(4, attack);
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e){
		Player p = e.getPlayer();
		
		ItemStack attack = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta am = attack.getItemMeta();
		am.setDisplayName("§cLoitsuvalikko");
		am.setLore(Arrays.asList("§7Klikkaa saadaksesi valikon ", "§7saatavilla olevista loitsuista!"));
		attack.setItemMeta(am);
		
		p.getInventory().setItem(4, attack);
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e){
		if(!(e.getWhoClicked() instanceof Player)) return;
		Player p = (Player) e.getWhoClicked();
		
		if(e.getInventory().getName().contains("Loitsuvalikko")){
			e.setCancelled(true);
			
			if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Attack")){
				p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 0.2f);
				openAttackGUI(p);
			}
			else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Support")){
				p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 0.2f);
				openSupportGUI(p);
			}
			else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Misc")){
				p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 0.2f);
				openMiscGUI(p);
			}
			
		}
		else if(e.getInventory().getName().contains("(Hyökkäys)")){
			e.setCancelled(true);
			
			if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§f§lSalama")){
				
				ItemStack lightning = new ItemStack(Material.INK_SACK, 1, (byte) 12);
				ItemMeta lmeta = lightning.getItemMeta();
					lmeta.setDisplayName("§f§lSalama");
					lmeta.setLore(Arrays.asList("§7Ammu salama siihen", "§7blockiin mitä klikkasit", "", "§aOta loitsu käyttöön klikkaamllla!", "", "§7Cooldown: §c20s"));
				lightning.setItemMeta(lmeta);
				p.closeInventory();
				p.getInventory().clear();
				p.getInventory().setItem(0, lightning);
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 0.2f);
			}
			else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Tulipallo")){
				
				ItemStack fireball = new ItemStack(Material.COAL);
				ItemMeta fmeta = fireball.getItemMeta();
				fmeta.setDisplayName("§c§lTulipallo");
				fmeta.setLore(Arrays.asList("§7Ammu tulipallo sinne", "§7minne katsot!", "", "§aOta loitsu käyttöön klikkaamllla!", "", "§7Cooldown: §c10s"));
				fireball.setItemMeta(fmeta);
				p.closeInventory();
				p.getInventory().clear();
				p.getInventory().setItem(1, fireball);
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 0.2f);
			}
			else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§aSat§ceen§bkaa§eri")){
				ItemStack rainbow = new ItemStack(Material.INK_SACK, 1, (byte) 10);
				ItemMeta rbmeta = rainbow.getItemMeta();
				rbmeta.setDisplayName("§aSat§ceen§bkaa§eri");
				rbmeta.setLore(Arrays.asList("§7Ammu sateenkaarisäde sinne", "§7minne katsot!", "", "§aOta loitsu käyttöön klikkaamllla!", "", "§7Cooldown: §c10s"));
				rainbow.setItemMeta(rbmeta);
				p.closeInventory();
				p.getInventory().clear();
				p.getInventory().setItem(2, rainbow);
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 0.2f);
			}
			
		}
		else if(e.getInventory().getName().contains("(Sekalainen)")){
			e.setCancelled(true);
			
		}
		else if(e.getInventory().getName().contains("(Avustavat)")){
			e.setCancelled(true);
			
		}
	}
}