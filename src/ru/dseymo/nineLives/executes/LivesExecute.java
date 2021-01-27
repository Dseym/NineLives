package ru.dseymo.nineLives.executes;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.dseymo.nineLives.Main;
import ru.dseymo.nineLives.utils.Chat;

public class LivesExecute implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.hasPermission("nineLives.admin")) {
			Chat.NO_PERM.send(sender, "");
			return true;
		}
		
		if(args.length == 0)
			help(sender);
		else if(args[0].equalsIgnoreCase("setlive"))
			setLive(sender, args);
		else
			help(sender);
		
		return true;
	}

	private void help(CommandSender sender) {
		
		Chat.INFO.send(sender, "Commands:",
							   "/lives setlives <nick> <lives> - &eустановить жизни игроку",
							   "/lives help - &eэта команда");
		
	}

	private void setLive(CommandSender sender, String[] args) {
		if(args.length < 3) {
			Chat.FAIL.send(sender, "Не хватает аргументов");
			return;
		}
		Player p = Bukkit.getPlayer(args[1]);
		if(p == null) {
			Chat.FAIL.send(sender, "Игрок не найден");
			return;
		}
		
		try {
			Main.getInstance().getPlayers().setLive(p, Integer.parseInt(args[2]));
			Chat.SUCCESS.send(sender, "Успешно изменено");
		} catch (Exception e) {Chat.FAIL.send(sender, "Введите число");}
		
	}

}
