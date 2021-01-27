package ru.dseymo.nineLives;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import ru.dseymo.nineLives.executes.LivesExecute;
import ru.dseymo.nineLives.listeners.DeathListener;
import ru.dseymo.nineLives.listeners.JoinEvent;
import ru.dseymo.nineLives.players.PlayersConfig;
import ru.dseymo.nineLives.utils.Tablist;

public class Main extends JavaPlugin {
	
	@Getter
	private static Main instance;
	
	
	@Getter
	private PlayersConfig players;
	@Getter
	private MainConfig main;
	@Getter
	private Tablist tablist;
	
	@Override
	public void onEnable() {
		instance = this;
		main = new MainConfig(new File(getDataFolder() + "/config.yml"));
		players = new PlayersConfig(new File(getDataFolder() + "/players.yml"));
		tablist = new Tablist();
		
		Bukkit.getPluginCommand("lives").setExecutor(new LivesExecute());
		
		registerEvents();
		
		getLogger().info("Enabled!");
	}
	
	private void registerEvents() {
		PluginManager mn = Bukkit.getPluginManager();
		
		mn.registerEvents(new DeathListener(), this);
		mn.registerEvents(new JoinEvent(), this);
		
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Disabled!");
	}
	
}
