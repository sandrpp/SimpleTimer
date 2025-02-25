package me.sandrp.simpletimer.message;

import me.sandrp.simpletimer.Main;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.Console;
import java.util.List;
import java.util.logging.ConsoleHandler;

public class PluginInfo {

    private static MiniMessage miniMessage = MiniMessage.miniMessage();
    private static ConsoleCommandSender console = Main.getPlugin().getServer().getConsoleSender();

    public static void send(@NotNull CommandSender commandSender, String pluginName, List<String> authors, String version, boolean miniMessageApiHint) {
        String authorsString = String.join(", ", authors);
        String header = "<st><grey>        </st> <gradient:#fd0168:#c844e8>" + pluginName + "</gradient:#fd0168:#c844e8> <st><grey>        </grey></st>";
        String versionInfo = "Version: <#dd4282>" + version;
        String authorInfo = "Author: <#dd4282>" + authorsString;
        String[] commands = {
            "<grey>/actionbar [USAGE] [TYPE] [MESSAGE]</grey>",
            "<dark_grey>(display actionbar messages)</dark_grey>",
            "<grey>/chat [USAGE] [TYPE] [MESSAGE]</grey>",
            "<dark_grey>(display chat messages)</dark_grey>",
            "<grey>/utils</grey> <dark_grey>(this info)</dark_grey>"
        };

        if (commandSender instanceof Player player) {
            MessageManager.defaultMessage(player, header);
            MessageManager.defaultMessage(player, versionInfo);
            MessageManager.defaultMessage(player, authorInfo);
            if (miniMessageApiHint) {
                player.sendMessage(miniMessage.deserialize("Info: <#dd4282>the plugin uses MiniMessageAPI for formatting: ")
                    .append(miniMessage.deserialize("<blue>https://docs.advntr.dev/</blue>")
                    .clickEvent(ClickEvent.openUrl("https://docs.advntr.dev/minimessage/format.html"))));
            }
            MessageManager.defaultMessage(player, "Commands:");
            for (String command : commands) {
                MessageManager.defaultMessage(player, command);
            }
            MessageManager.defaultMessage(player, header);
        } else {
            MessageManager.defaultMessage(console, header);
            MessageManager.defaultMessage(console, versionInfo);
            MessageManager.defaultMessage(console, authorInfo);
            MessageManager.defaultMessage(console, "Commands:");
            for (String command : commands) {
                MessageManager.defaultMessage(console, command);
            }
            MessageManager.defaultMessage(console, header);
        }
    }
}
