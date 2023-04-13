package floatpoint.untitled3.commands;

import org.bukkit.command.*;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if(!(sender instanceof Player)){
            sender.sendMessage("Only players are able to fly, you are a computer.");
            return true;
        }

        Player player = (Player) sender;

        if (player.getAllowFlight()){
            player.setAllowFlight(false);
            player.sendMessage("Flight disabled.");
        } else{
            player.setAllowFlight(true);
            player.sendMessage("Flight enabled.");
        }
        return true;
    }
}
