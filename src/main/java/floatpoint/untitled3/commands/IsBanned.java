package floatpoint.untitled3.commands;

import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class IsBanned implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("Usage: /scan <username>");
            return false;
        }

        String username = args[0];

        if (!Bukkit.getBanList(BanList.Type.NAME).isBanned(username)) {
            sender.sendMessage("Scanning bans for " + ChatColor.GRAY + username + ChatColor.WHITE + " [" + ChatColor.GREEN + "Online" + ChatColor.WHITE + "] [" + ChatColor.GRAY + "Offline" + ChatColor.WHITE + "] [" + ChatColor.RED + "Banned" + ChatColor.WHITE + "]");
            sender.sendMessage(ChatColor.GREEN + username + ChatColor.WHITE + " is not currently banned.");
            return false;
        }

        BanEntry ban = Bukkit.getBanList(BanList.Type.NAME).getBanEntry(username);
        String banReason = ban.getReason();
        String admin = ban.getSource();
        sender.sendMessage("Scanning bans for " + ChatColor.GRAY + username + ChatColor.WHITE + " [" + ChatColor.GREEN + "Online" + ChatColor.WHITE + "] [" + ChatColor.GRAY + "Offline" + ChatColor.WHITE + "] [" + ChatColor.RED + "Banned" + ChatColor.WHITE + "]");

        TextComponent message = new TextComponent(ChatColor.RED + username);
        message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{new TextComponent(net.md_5.bungee.api.ChatColor.YELLOW + "« ModSuite Custom Hover Text »" + "\n"), new TextComponent(net.md_5.bungee.api.ChatColor.DARK_RED + "" + net.md_5.bungee.api.ChatColor.BOLD + "Origin " + net.md_5.bungee.api.ChatColor.YELLOW + "» " + net.md_5.bungee.api.ChatColor.WHITE + sender.getName()), new TextComponent(net.md_5.bungee.api.ChatColor.DARK_RED + "" + net.md_5.bungee.api.ChatColor.BOLD +"\nReason " + net.md_5.bungee.api.ChatColor.YELLOW + "» " + net.md_5.bungee.api.ChatColor.WHITE + banReason)}));
        Bukkit.spigot().broadcast(message);

        //ender.sendMessage(ChatColor.RED + username + ChatColor.WHITE + " is banned." + ChatColor.GOLD + "\n Reason: " + ChatColor.WHITE + banReason + ChatColor.GOLD +"\n Banned by: " + ChatColor.RED + admin);

        return true;
    }
}
