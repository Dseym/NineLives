package ru.dseymo.nineLives.players;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import ru.dseymo.nineLives.utils.Config;

public class PlayersConfig extends Config {

	public PlayersConfig(File file) {
		super(file, false);
	}
	
	public void setLive(OfflinePlayer p, int lives) {
		
		set(p.getUniqueId().toString(), lives);
		save();
		
	}
	
	public void addLive(OfflinePlayer p) {
		
		set(p.getUniqueId().toString(), getLive(p)+1);
		save();
		
	}
	
	public void removeLive(OfflinePlayer p) {
		int lives = getLive(p);
		if(lives < 1) return;
		
		set(p.getUniqueId().toString(), lives-1);
		save();
		
	}
	
	public int getLive(OfflinePlayer p) {return getInt(p.getUniqueId().toString());}
	public ArrayList<OfflinePlayer> getPlayers() {
		ArrayList<OfflinePlayer> players = new ArrayList<>();
		for(String str: getKeys(false))
			players.add(Bukkit.getOfflinePlayer(UUID.fromString(str)));
		
		return players;
	}
	public boolean contains(OfflinePlayer p) {return getPlayers().contains(p);}

}
