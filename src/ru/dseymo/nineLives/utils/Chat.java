package ru.dseymo.nineLives.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import lombok.Getter;

public enum Chat {
		
	NO_PREFIX(""),
	INFO("&9&lLives&8 >> &7"),
	FAIL("&9&lLives&8 >> &4"),
	SUCCESS("&9&lLives&8 >> &2"),
	NO_PERM("&9&lLives&8 >> &4Нету прав! ");
	
	@Getter
	private String pref;
	
	private Chat(String prefix) {this.pref = prefix;}
	
	public String colorize(String text) {return ChatColor.translateAlternateColorCodes('&', text);}
	
	public void send(CommandSender sender, String... strs) {
		if(this == NO_PERM) {
			sender.sendMessage(colorize(pref + (strs.length == 0 ? "" : strs[0])));
			if(strs.length > 1)
				FAIL.send(sender, Arrays.copyOfRange(strs, 1, strs.length));
			return;
		}
		
		for(String str: strs)
			sender.sendMessage(colorize(pref + str));
	}
	
	public void sendAll(String... strs) {
		send(new ArrayList<>(Bukkit.getOnlinePlayers()), strs);
	}
	
	public void send(List<CommandSender> senders, String... strs) {
		for(CommandSender sender: senders)
			send(sender, strs);
	}
	
}