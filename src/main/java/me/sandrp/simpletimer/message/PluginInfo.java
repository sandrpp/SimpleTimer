package me.sandrp.simpletimer.message;

import me.sandrp.simpletimer.Main;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PluginInfo {

    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    private static final ConsoleCommandSender console = Main.getPlugin().getServer().getConsoleSender();

    public static void send(@NotNull CommandSender commandSender, boolean full) {
        String authorsString = String.join(", ", Main.getPlugin().getDescription().getAuthors());
        String header = "<st><grey>        </st> <gradient:#ffd6ff:#bbd0ff>" + Main.getPlugin().getDescription().getName() + "</gradient:#ffd6ff:#bbd0ff> <st><grey>        </grey></st>";
        String footer = "<st><grey>                                </grey></st>";
        String versionInfo = "Version: <#bbd0ff>" + Main.getPlugin().getDescription().getVersion();
        String authorInfo = "Author: <#bbd0ff>" + authorsString;
        String[] fullCommands = {
            "<grey>/timer start/resume</grey>",
            "<dark_grey>(start the timer)</dark_grey>",
            "<grey>/timer stop/pause</grey>",
            "<dark_grey>(stop the timer)</dark_grey>",
            "<grey>/timer toggle</grey>",
            "<dark_grey>(toggle the timer on/off)</dark_grey>",
            "<grey>//</grey>",
            "<dark_grey>(toggle the timer on/off)</dark_grey>",
            "<grey>/timer reset</grey>",
            "<dark_grey>(resets the timer to 0 and stops it)</dark_grey>",
            "<grey>/timer visibility</grey>",
            "<dark_grey>(sets the timer visibility, show/hide)</dark_grey>",
            "<grey>/timer up/down</grey>",
            "<dark_grey>(sets the timer to count up-/downwards)</dark_grey>",
            "<grey>/timer color [color]</grey>",
            "<dark_grey>(sets the color to one of the shown once)</dark_grey>",
            "<grey>/timer set [number]</grey>",
            "<dark_grey>(sets the timer to a given time)</dark_grey>",
            "<grey>/timer info</grey> <dark_grey>(this info)</dark_grey>"
        };
        String[] shortCommands = {
                "<grey>/timer start/resume</grey>",
                "<dark_grey>(start the timer)</dark_grey>",
                "<grey>... (use \"/timer info full\" to get a list of all commands)</grey>"
        };

        if (commandSender instanceof Player player) {
            MessageManager.defaultMessage(player, header);
            MessageManager.defaultMessage(player, versionInfo);
            MessageManager.defaultMessage(player, authorInfo);
            player.sendMessage(miniMessage.deserialize("Info: <#bbd0ff>this plugin uses MiniMessageAPI for formatting: ")
                .append(miniMessage.deserialize("<#7dacd8>https://docs.advntr.dev/</#7dacd8>")
                .hoverEvent(HoverEvent.showText(miniMessage.deserialize("<gray>https://docs.advntr.dev/minimessage/format.html</gray>")))
                .clickEvent(ClickEvent.openUrl("https://docs.advntr.dev/minimessage/format.html"))));

            MessageManager.defaultMessage(player, "Commands:");
            if (full) {
                for (String command : fullCommands) {
                    MessageManager.defaultMessage(player, command);
                }
            } else {
                for (String command : shortCommands) {
                    MessageManager.defaultMessage(player, command);
                }
            }
            MessageManager.defaultMessage(player, footer);
        } else {
            MessageManager.defaultMessage(console, header);
            MessageManager.defaultMessage(console, versionInfo);
            MessageManager.defaultMessage(console, authorInfo);
            MessageManager.defaultMessage(console, "Commands:");
            if (full) {
                for (String command : fullCommands) {
                    MessageManager.defaultMessage(console, command);
                }
            } else {
                for (String command : shortCommands) {
                    MessageManager.defaultMessage(console, command);
                }
            }
            MessageManager.defaultMessage(console, footer);
        }
    }
}
