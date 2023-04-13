package floatpoint.untitled3;

import floatpoint.untitled3.commands.Ban;
import floatpoint.untitled3.commands.Fly;
import floatpoint.untitled3.commands.IsBanned;
import floatpoint.untitled3.commands.Unban;
import floatpoint.untitled3.handlers.PlayerHandler;
import floatpoint.untitled3.handlers.TorchHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Test extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("ModerationSuite loaded. You are running the free version, this version is limited.");

        // SMenu Commands
        getCommand("fly").setExecutor(new Fly());


        // Moderation Commands
        getCommand("ban").setExecutor(new Ban());
        getCommand("unban").setExecutor(new Unban());
        getCommand("scan").setExecutor(new IsBanned());
        new PlayerHandler(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
