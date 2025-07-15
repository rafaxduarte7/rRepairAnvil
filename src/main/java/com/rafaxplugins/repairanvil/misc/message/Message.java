package com.rafaxplugins.repairanvil.misc.message;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Message {

    public static void send(Player player, String message) {
        if (player != null && message != null) {
            player.sendMessage(color(message));
        }
    }

    public static void send(Player player, String... message) {
        if (player != null && message != null) {
            for (String line : message) {
                player.sendMessage(color(line));
            }
        }
    }

    public static void send(Player player, List<String> message) {
        if (player != null && message != null) {
            for (String line : message) {
                player.sendMessage(color(line));
            }
        }
    }

    public static void send(CommandSender sender, String message) {
        if (sender != null && message != null) {
            sender.sendMessage(color(message));
        }
    }

    public static void send(CommandSender sender, String... message) {
        if (sender != null && message != null) {
            for (String line : message) {
                sender.sendMessage(color(line));
            }
        }
    }

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
