package floatpoint.untitled3;

import floatpoint.untitled3.commands.*;
import floatpoint.untitled3.handlers.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;
import floatpoint.untitled3.handlers.LoggingHandler;

import java.io.File;
import java.io.IOException;

public final class BanBetter extends JavaPlugin {

    private LoggingHandler loggingHandler;
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("BanBetter 0.0.1 loaded.");
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        // Load the configuration file
        File configFile = new File(getDataFolder(), "config.yml");
        if (configFile.exists()) {
            try {
                getConfig().load(configFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            // Set default configuration options
            getConfig().options().header(">>>> BanBetter 0.0.1 <<<<");
            getConfig().set("demo", true);
            getConfig().set("allbanips", false);


            // Add comments to individual configuration options
            getConfig().options().copyHeader(true); // Copy header to new options
            getConfig().options().copyDefaults(true);
            getConfig().addDefault("demo", true);
            getConfig().addDefault("allbanips", false);

            // Save the configuration file
            saveConfig();
        }

        // SMenu Commands
        getCommand("fly").setExecutor(new Fly());


        // Moderation Commands
        getCommand("ban").setExecutor(new Ban());
        getCommand("tempban").setExecutor(new Tempban());
        getCommand("unban").setExecutor(new Unban());
        getCommand("scan").setExecutor(new IsBanned());
        new PlayerHandler(this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
