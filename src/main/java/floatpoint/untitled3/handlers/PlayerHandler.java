package floatpoint.untitled3.handlers;

import floatpoint.untitled3.Test;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.eclipse.sisu.Priority;

import java.awt.*;

public class PlayerHandler implements Listener {

    public PlayerHandler(Test plugin) {
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
        event.setQuitMessage(ChatColor.RED + "" + ChatColor.BOLD + player.getName() + ChatColor.RESET + "" + ChatColor.WHITE + " is gay.");

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

        item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.ITALIC + "ModSuite");
        item.setItemMeta(meta);
        inventory.addItem(item);
    }
}
