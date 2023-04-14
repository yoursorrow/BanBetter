package floatpoint.untitled3.handlers;

import floatpoint.untitled3.BanBetter;
import floatpoint.untitled3.commands.Unban;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.google.gson.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class PlayerHandler implements Listener {
    private BanBetter plugin;

    public PlayerHandler(BanBetter plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler(ignoreCancelled = true)
    public void onBlockPlaced(BlockPlaceEvent event){
        Block block = event.getBlock();

        int xval = block.getX();
        int yval = block.getY();
        int zval = block.getZ();
        Bukkit.getLogger().info("A block has been placed @ X: " + xval + " Y: " + yval + " Z: " + zval);
    }


    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();
        event.setQuitMessage(ChatColor.RED + "" + ChatColor.BOLD + player.getName() + ChatColor.RESET + "" + ChatColor.WHITE + " has joined.");

    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        event.setJoinMessage(ChatColor.RED + "" + ChatColor.BOLD + player.getName() + ChatColor.RESET + "" + ChatColor.WHITE + " is straight.");
        ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
        PlayerInventory inventory = player.getInventory();
        if(inventory.contains(Material.NETHER_STAR)){
            return;
        }
        Plugin plugin = Bukkit.getPluginManager().getPlugin("BanBetter");

        boolean demo = plugin.getConfig().getBoolean("demo");
        if(demo == true){
            String title = net.md_5.bungee.api.ChatColor.RED + "DEMO MODE";
            int fadeIn = 10; // ticks
            int stay = 70; // ticks
            int fadeOut = 20; // ticks
            String subtitle = net.md_5.bungee.api.ChatColor.YELLOW + "Configuration not available.";

            player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
        }
        File dataFolder = plugin.getDataFolder();
        String username = player.getName();
        try {
            int duration = readBanDuration(dataFolder + File.separator +"tbans.json", username);
            if (duration > 0) {
                String formattedDuration = formatDuration(duration);
                String kickMessage = ChatColor.DARK_RED + "" + ChatColor.BOLD + "| SERVER |" + "\n\n" + ChatColor.RESET + net.md_5.bungee.api.ChatColor.RED + "You are temporarily banned!" + "\n" + ChatColor.GOLD + "You have been temporarily banned from" + ChatColor.GREEN + "(servernameinconfig.yml)" + ChatColor.GOLD + "for " + ChatColor.RED + ChatColor.BOLD + "nullrsn" + "\n\n" + formattedDuration;
                player.kickPlayer(kickMessage);
            } else if (duration == 0) {
                Bukkit.getBanList(BanList.Type.NAME).pardon(username);
            } else {
                // Player is not banned
                // ...
            }
        } catch (IOException e) {
            // Handle file read or JSON parsing errors
            e.printStackTrace();
        }
    }
    public static int readBanDuration(String filename, String username) throws IOException, JsonSyntaxException {
        String jsonStr = new String(Files.readAllBytes(Paths.get(filename)));
        JsonArray bans = JsonParser.parseString(jsonStr).getAsJsonArray();

        for (int i = 0; i < bans.size(); i++) {
            JsonObject ban = bans.get(i).getAsJsonObject();
            if (ban.get("user").getAsString().equals(username)) {
                return ban.get("duration").getAsInt();
            }
        }

        return -1; // Player not found in ban log
    }

    public String formatDuration(int durationInSeconds) {
        int days = durationInSeconds / 86400;
        int hours = (durationInSeconds % 86400) / 3600;
        int minutes = ((durationInSeconds % 86400) % 3600) / 60;
        int seconds = ((durationInSeconds % 86400) % 3600) % 60;

        StringBuilder sb = new StringBuilder();
        if (days > 0) {
            sb.append(days).append("d ");
        }
        if (hours > 0) {
            sb.append(hours).append("h ");
        }
        if (minutes > 0) {
            sb.append(minutes).append("m ");
        }
        if (seconds > 0) {
            sb.append(seconds).append("s");
        }

        return sb.toString().trim();
    }
}
