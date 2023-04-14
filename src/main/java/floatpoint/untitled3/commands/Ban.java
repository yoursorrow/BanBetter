package floatpoint.untitled3.commands;

import floatpoint.untitled3.BanBetter;
import floatpoint.untitled3.handlers.LoggingHandler;
import jdk.jpackage.internal.Log;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ban implements CommandExecutor {

    private LoggingHandler loggingHandler;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage("Usage: /ban <username> [reason]");
            return false;
        }
        String username = args[0];
        Player player = Bukkit.getPlayer(username);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = now.format(formatter);
        if (player != null) {
            // Player is currently online
            String title = ChatColor.YELLOW + "ModSuite Notification";
            String subtitle = ChatColor.RED + "Rule breaker! You've been permanently banned.";
            int fadeIn = 10; // ticks
            int stay = 70; // ticks
            int fadeOut = 20; // ticks
            player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
            player.kickPlayer("You have been banned from the server.");
            String reason = args.length > 1 ? String.join(" ", args).substring(username.length() + 1) : "";
            Bukkit.getBanList(BanList.Type.NAME).addBan(username, reason, null, sender.getName());
            TextComponent message = new TextComponent(ChatColor.GREEN + username + ChatColor.WHITE + " has been banned from the server by " + ChatColor.RED + sender.getName());
            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]
                    {
                            new TextComponent(net.md_5.bungee.api.ChatColor.YELLOW + "« ModSuite Custom Hover Text »" + "\n"), new TextComponent(net.md_5.bungee.api.ChatColor.DARK_RED + "" + net.md_5.bungee.api.ChatColor.BOLD + "Origin " + net.md_5.bungee.api.ChatColor.YELLOW + "» " + net.md_5.bungee.api.ChatColor.WHITE + sender.getName()), new TextComponent(net.md_5.bungee.api.ChatColor.DARK_RED + "" + net.md_5.bungee.api.ChatColor.BOLD +"\nReason " + net.md_5.bungee.api.ChatColor.YELLOW + "» " + net.md_5.bungee.api.ChatColor.WHITE + reason + ChatColor.DARK_RED + "" + ChatColor.BOLD +"\nTime: " + ChatColor.WHITE + date)
                    }));
            Bukkit.spigot().broadcast(message);
            LoggingHandler loggingHandler = new LoggingHandler(BanBetter.getPlugin(BanBetter.class));
            loggingHandler.logABan(username, reason, sender.getName(), true, player.getAddress().toString(), false);
        } else {
            // Player is offline
            String reason = args.length > 1 ? String.join(" ", args).substring(username.length() + 1) : "";
            Bukkit.getBanList(BanList.Type.NAME).addBan(username, reason, null, sender.getName());
            TextComponent message = new TextComponent(ChatColor.GREEN + username + ChatColor.WHITE + " has been banned from the server by " + ChatColor.RED + sender.getName());
            message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{new TextComponent(net.md_5.bungee.api.ChatColor.YELLOW + "« ModSuite Custom Hover Text »" + "\n"), new TextComponent(net.md_5.bungee.api.ChatColor.DARK_RED + "" + net.md_5.bungee.api.ChatColor.BOLD + "Origin " + net.md_5.bungee.api.ChatColor.YELLOW + "» " + net.md_5.bungee.api.ChatColor.WHITE + sender.getName()), new TextComponent(net.md_5.bungee.api.ChatColor.DARK_RED + "" + net.md_5.bungee.api.ChatColor.BOLD +"\nReason " + net.md_5.bungee.api.ChatColor.YELLOW + "» " + net.md_5.bungee.api.ChatColor.WHITE + reason + ChatColor.DARK_RED + "" + ChatColor.BOLD +"\nTime: " + ChatColor.WHITE + date)}));
            Bukkit.spigot().broadcast(message);
            LoggingHandler loggingHandler = new LoggingHandler(BanBetter.getPlugin(BanBetter.class));
            loggingHandler.logABan(username, reason, sender.getName(), true, "OFFLINE", false);
        }

        return true;
    }

}
