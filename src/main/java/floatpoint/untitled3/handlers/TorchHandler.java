package floatpoint.untitled3.handlers;

import floatpoint.untitled3.Test;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class TorchHandler implements Listener {

    public TorchHandler(Test plugin){

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler(ignoreCancelled = true)
    public void onTorchPlaced(BlockPlaceEvent event){
        Block block = event.getBlock();

        if (block.getType() != Material.TORCH){
            return;
        }

        if (!event.getPlayer().hasPermission("bukkit.ban") || !event.getPlayer().hasPermission("essentials.ban")){
            return;
        }
        Bukkit.getLogger().info("A torch has been placed.");
    }
}
