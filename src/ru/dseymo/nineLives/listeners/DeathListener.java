package ru.dseymo.nineLives.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import ru.dseymo.nineLives.Main;
import ru.dseymo.nineLives.MainConfig;
import ru.dseymo.nineLives.players.PlayersConfig;
import ru.dseymo.nineLives.utils.Chat;

public class DeathListener implements Listener {
	
	@EventHandler
	private void death(PlayerDeathEvent e) {
		Player p = e.getEntity();
		
		MainConfig confMain = Main.getInstance().getMain();
		PlayersConfig confPl = Main.getInstance().getPlayers();
		if(!confPl.contains(p))
			confPl.setLive(p, confMain.getStartLives()-1);
		else
			confPl.removeLive(p);
		
		Chat.FAIL.sendAll("Игрок %player% потерял жизнь: %lives%/%maxLives%".replace("%player%", p.getName()).replace("%lives%", confPl.getLive(p) + "").replace("%maxLives%", confMain.getStartLives() + ""));
		
		if(confPl.getLive(p) < 1) {
			
			p.setGameMode(GameMode.SPECTATOR);
			if(confMain.isBanning())
				p.kickPlayer("Вы потеряли все жизни");
				
		}
		
	}
	
}
