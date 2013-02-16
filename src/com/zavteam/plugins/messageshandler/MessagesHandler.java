package com.zavteam.plugins.messageshandler;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.zavteam.plugins.ChatMessage;
import com.zavteam.plugins.Main;
import com.zavteam.plugins.configs.IgnoreConfig;
import com.zavteam.plugins.configs.MainConfig;

public class MessagesHandler {
	public static void handleMessage(String[] sarray, ChatMessage cm) {
		boolean permissionsBV = MainConfig.getPermissionEnabled();
		if (Main.plugin.getServer().getOnlinePlayers().length == 0 && MainConfig.getRequirePlayers()) {
			return;
		}
		if (permissionsBV || cm == null) {
			for (Player player : Main.plugin.getServer().getOnlinePlayers()) {
				Main.plugin.log.info("Permission: " + cm.getPermission() + ", Message: " + cm.getMessage() + ", Player: " + player.hasPermission(cm.getPermission()));
				if (player.hasPermission(cm.getPermission()) && !IgnoreConfig.getIgnorePlayers().contains(player.getName())) {
					player.sendMessage(sarray);
				}
			}
		} else {
			for (Player player : Main.plugin.getServer().getOnlinePlayers()) {
				if (!IgnoreConfig.getIgnorePlayers().contains(player.getName())) {
					player.sendMessage(sarray);
				}
			}
		}
		if (MainConfig.getMessagesInConsole()) {
			for (String s : sarray) {
				Main.plugin.log.info(s);	
			}
		}
	}

	public static void addMessage(String m) {
		List<String> s = MainConfig.config.getStringList("messages.default");
		s.add(m);
		MainConfig.set("messages.default", s);
	}
	public static void listPage(int i, CommandSender sender) {
		sender.sendMessage(ChatColor.GOLD + "ZavAutoMessager Messages Page: " + i);
		int initialInt = (5 * i) - 5;
		int finalInt = initialInt + 5;
		for (int iterator = initialInt; iterator < finalInt; iterator++) {
			String message = ChatColor.GOLD + Integer.toString(iterator + 1) + ". ";
			try {
				message = message + Main.plugin.messages.get(iterator);
			} catch (IndexOutOfBoundsException e) {

			}
			sender.sendMessage(message);
		}
	}
	public static void listHelpPage(int i, CommandSender sender) {
		sender.sendMessage(ChatColor.GOLD + "========= ZavAutoMessager Help =========");
		switch (i) {
		case 1: {
			sender.sendMessage(ChatColor.GOLD + "1. /automessager reload - Reloads config");
			sender.sendMessage(ChatColor.GOLD + "2. /automessager on - Start the messages");
			sender.sendMessage(ChatColor.GOLD + "3. /automessager off - Stops the messages");
			sender.sendMessage(ChatColor.GOLD + "4. /automessager add <message> - Adds a message to the list");
			sender.sendMessage(ChatColor.GOLD + "5. /automessager remove <message number> - Removes message from list");
			sender.sendMessage(ChatColor.GOLD + "=============== Page 1/2 ===============");
			break;
		}
		case 2: {
			sender.sendMessage(ChatColor.GOLD + "6. /automessager ignore - Toggles ignoring messages");
			sender.sendMessage(ChatColor.GOLD + "7. /automessager broadcast <message> - Send a message now");
			sender.sendMessage(ChatColor.GOLD + "8. /automessager about - Displays info about the Main.plugin");
			sender.sendMessage(ChatColor.GOLD + "9. /automessage list - NOT WORKING Shows a message list");
			sender.sendMessage(ChatColor.GOLD + "10. /automessager help (page)- Displays this menu");
			sender.sendMessage(ChatColor.GOLD + "=============== Page 2/2 ===============");
			break;
		}
		}
	}
}