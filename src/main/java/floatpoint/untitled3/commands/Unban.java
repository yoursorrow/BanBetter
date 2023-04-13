package floatpoint.untitled3.commands;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class Unban implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("Usage: /unban <username>");
            return false;
        }

        String username = args[0];

        if (!Bukkit.getBanList(BanList.Type.NAME).isBanned(username)) {
            sender.sendMessage(ChatColor.GREEN + username + " is not currently banned.");
            return false;
        }

        Bukkit.getBanList(BanList.Type.NAME).pardon(username);
        sender.sendMessage(ChatColor.GREEN + username + " has been unbanned.");
        return true;
    }
}