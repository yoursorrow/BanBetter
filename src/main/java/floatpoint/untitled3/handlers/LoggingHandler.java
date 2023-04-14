package floatpoint.untitled3.handlers;

import floatpoint.untitled3.BanBetter;
import floatpoint.untitled3.commands.Ban;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class LoggingHandler implements Listener {

    private BanBetter plugin;
    public LoggingHandler(BanBetter plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    public void logToLogs(String message){
        File dataFolder = plugin.getDataFolder();

        if(!dataFolder.exists()){
            dataFolder.mkdir();
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = now.format(formatter);

        File outputFile = new File(dataFolder, "logs" + ".json");
        JSONObject logEntry = new JSONObject();
        logEntry.put("timestamp", timestamp);
        logEntry.put("message", message);
        JSONArray logsArray = new JSONArray();

        try {
            if (outputFile.exists() && outputFile.length() > 0) {
                JSONParser parser = new JSONParser();
                FileReader reader = new FileReader(outputFile);
                Object obj = parser.parse(reader);
                logsArray = (JSONArray) obj;
                reader.close();
            }
            logsArray.add(logEntry);

            FileWriter writer = new FileWriter(outputFile);
            writer.write(logsArray.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    public void logTempBan(String username, String reason, String origin, String ip, Boolean ipban, int duration){
        File dataFolder = plugin.getDataFolder();

        if(!dataFolder.exists()){
            dataFolder.mkdir();
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = now.format(formatter);

        File outputFile = new File(dataFolder, "tbans" + ".json");
        JSONObject logEntry = new JSONObject();
        logEntry.put("id", UUID.randomUUID().toString());
        logEntry.put("timestamp", timestamp);
        logEntry.put("user", username);
        logEntry.put("reason", reason);
        logEntry.put("by", origin);
        logEntry.put("ip", ip);
        logEntry.put("ipban", ipban);
        logEntry.put("duration", duration);
        JSONArray logsArray = new JSONArray();

        try {
            if (outputFile.exists() && outputFile.length() > 0) {
                JSONParser parser = new JSONParser();
                FileReader reader = new FileReader(outputFile);
                Object obj = parser.parse(reader);
                logsArray = (JSONArray) obj;
                reader.close();
            }
            logsArray.add(logEntry);

            FileWriter writer = new FileWriter(outputFile);
            writer.write(logsArray.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }
    public void logABan(String username, String reason, String origin, Boolean permanent, String ip, Boolean ipban){
        File dataFolder = plugin.getDataFolder();

        if(!dataFolder.exists()){
            dataFolder.mkdir();
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = now.format(formatter);

        File outputFile = new File(dataFolder, "bans" + ".json");
        JSONObject logEntry = new JSONObject();
        logEntry.put("id", UUID.randomUUID().toString());
        logEntry.put("timestamp", timestamp);
        logEntry.put("user", username);
        logEntry.put("reason", reason);
        logEntry.put("by", origin);
        logEntry.put("permanent", permanent);
        logEntry.put("ip", ip);
        logEntry.put("ipban", ipban);
        JSONArray logsArray = new JSONArray();

        try {
            if (outputFile.exists() && outputFile.length() > 0) {
                JSONParser parser = new JSONParser();
                FileReader reader = new FileReader(outputFile);
                Object obj = parser.parse(reader);
                logsArray = (JSONArray) obj;
                reader.close();
            }
            logsArray.add(logEntry);

            FileWriter writer = new FileWriter(outputFile);
            writer.write(logsArray.toJSONString());
            writer.flush();
            writer.close();
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }
}