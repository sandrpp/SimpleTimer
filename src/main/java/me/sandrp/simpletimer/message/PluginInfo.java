package me.sandrp.simpletimer.message;

import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PluginInfo {

    private static MiniMessage miniMessage = MiniMessage.miniMessage();

    public static void send(@NotNull CommandSender commandSender, String pluginName, String author, String version, boolean miniMessageApiHint) {
        if(commandSender instanceof Player player){
            Chat.sendMessage(player, "<st><grey>        </st> <gradient:#fd0168:#c844e8>" + pluginName + "</gradient:#fd0168:#c844e8> <st><grey>        </grey></st>");
            Chat.sendMessage(player, "Version: <#dd4282>" + version);
            Chat.sendMessage(player, "Author: <#dd4282>" + author);
            if(miniMessageApiHint) {
                player.sendMessage(miniMessage.deserialize("Info: <#dd4282>the plugin uses MiniMessageAPI for formatting: ").append(miniMessage.deserialize("<blue>https://docs.advntr.dev/</blue>").clickEvent(ClickEvent.openUrl("https://docs.advntr.dev/minimessage/format.html")).hoverEvent(HoverEvent.showText(miniMessage.deserialize("<blue>https://docs.advntr.dev/minimessage/format.html</blue>")))));
            }
            Chat.sendMessage(player, "Commands:");
            Chat.sendMessage(player, "<grey>/timer start/resume");
            Chat.sendMessage(player, "<dark_grey>(start the timer)</dark_grey>");
            Chat.sendMessage(player, "<grey>/timer pause/stop");
            Chat.sendMessage(player, "<dark_grey>(pause the timer)</dark_grey>");
            Chat.sendMessage(player, "<grey>/timer enable");
            Chat.sendMessage(player, "<dark_grey>(enable the plugin)</dark_grey>");
            Chat.sendMessage(player, "<grey>/timer disable");
            Chat.sendMessage(player, "<dark_grey>(disable the plugin)</dark_grey>");
            Chat.sendMessage(player, "<grey>/timer reset");
            Chat.sendMessage(player, "<dark_grey>(reset the plugin)</dark_grey>");
            Chat.sendMessage(player, "<grey>/timer toggle");
            Chat.sendMessage(player, "<dark_grey>(toggle the the timer on/off)</dark_grey>");
            Chat.sendMessage(player, "<st><grey>        </st> <gradient:#fd0168:#c844e8>" + pluginName + "</gradient:#fd0168:#c844e8> <st><grey>        </grey></st>");
        }
        else{
            Console.sendMessage("<st><grey>        </st> <gradient:#fd0168:#c844e8>" + pluginName + "</gradient:#fd0168:#c844e8> <st><grey>        </grey></st>");
            Console.sendMessage("Version: <#dd4282>" + version);
            Console.sendMessage("Author: <#dd4282>" + author);
            Console.sendMessage("Commands:");
            Console.sendMessage("<grey>/timer start/resume");
            Console.sendMessage("<dark_grey>(start the timer)</dark_grey>");
            Console.sendMessage("<grey>/timer pause/stop");
            Console.sendMessage("<dark_grey>(pause the timer)</dark_grey>");
            Console.sendMessage("<grey>/timer enable");
            Console.sendMessage("<dark_grey>(enable the plugin)</dark_grey>");
            Console.sendMessage("<grey>/timer disable");
            Console.sendMessage("<dark_grey>(disable the plugin)</dark_grey>");
            Console.sendMessage("<grey>/timer reset");
            Console.sendMessage("<dark_grey>(reset the plugin)</dark_grey>");
            Console.sendMessage("<grey>/timer toggle");
            Console.sendMessage("<dark_grey>(toggle the the timer on/off)</dark_grey>");
            Console.sendMessage("<st><grey>        </st> <gradient:#fd0168:#c844e8>" + pluginName + "</gradient:#fd0168:#c844e8> <st><grey>        </grey></st>");
        }
    }
}
