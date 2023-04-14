package floatpoint.untitled3.commands;

import floatpoint.untitled3.BanBetter;
import floatpoint.untitled3.handlers.LoggingHandler;
import jdk.internal.org.jline.reader.Parser;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tempban implements CommandExecutor {
    private LoggingHandler loggingHandler;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage("Usage: /tempban <username> <duration> <reason>");
            return true;
        }

        String username = args[0];
        String durationString = args[1];
        int duration = parseDuration(durationString);
        String reason = args.length > 2 ? String.join(" ", args).substring(username.length() + durationString.length() + 2) : "";
        Player player = Bukkit.getPlayer(username);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = now.format(formatter);

        LoggingHandler loggingHandler = new LoggingHandler(BanBetter.getPlugin(BanBetter.class));
        loggingHandler.logTempBan(username, reason, sender.getName(), player.getAddress().toString(), false, duration);
        return true;
    }

    private int parseDuration(String durationString) {
        char unit = durationString.charAt(durationString.length() - 1);
        int value = Integer.parseInt(durationString.substring(0, durationString.length() - 1));

        switch (unit) {
            case 's':
                return value;
            case 'm':
                return value * 60;
            case 'h':
                return value * 60 * 60;
            case 'd':
                return value * 60 * 60 * 24;
            default:
                throw new IllegalArgumentException("Invalid duration unit: " + unit);
        }
    }
}
