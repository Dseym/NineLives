package ru.dseymo.nineLives.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import ru.dseymo.nineLives.Main;

public class Tablist {
	
	public Tablist() {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {update();}
			
		}.runTaskTimer(Main.getInstance(), 20, 20);
		
	}
	
	public void update() {
		
		for(Player p: Bukkit.getOnlinePlayers()) {
			
			String str = "&9%player%: &7&l%lives%/%maxLives%";
			str = ChatColor.translateAlternateColorCodes('&', str);
			str = str.replace("%player%", p.getName());
			str = str.replace("%lives%", Main.getInstance().getPlayers().getLive(p) + "");
			str = str.replace("%maxLives%", Main.getInstance().getMain().getStartLives() + "");
			
			p.setPlayerListName(str);
		}
		
	}
	
}
