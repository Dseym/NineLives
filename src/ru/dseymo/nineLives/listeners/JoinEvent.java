package ru.dseymo.nineLives.listeners;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;
import org.bukkit.event.player.PlayerJoinEvent;

import ru.dseymo.nineLives.Main;
import ru.dseymo.nineLives.MainConfig;
import ru.dseymo.nineLives.players.PlayersConfig;

public class JoinEvent implements Listener {
	
	@EventHandler
	private void preJoin(AsyncPlayerPreLoginEvent e) {
		OfflinePlayer p = Bukkit.getOfflinePlayer(e.getUniqueId());
		
		MainConfig confMain = Main.getInstance().getMain();
		PlayersConfig confPl = Main.getInstance().getPlayers();
		if(!confPl.contains(p))
			confPl.setLive(p, confMain.getStartLives());
		
		if(confPl.getLive(p) < 1 && confMain.isBanning()) {
			e.disallow(Result.KICK_BANNED, "Вы потеряли все жизни");
			return;
		}
		
	}
	
	@EventHandler
	private void join(PlayerJoinEvent e) {e.getPlayer().setMaxHealth(2);}
	
}
