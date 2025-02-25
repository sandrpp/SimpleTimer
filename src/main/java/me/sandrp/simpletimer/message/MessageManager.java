package me.sandrp.simpletimer.message;

import me.sandrp.simpletimer.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MessageManager {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public static void defaultMessage(@NotNull Player playerIn, String messageIn){
        Component messageOut = miniMessage.deserialize(messageIn);
        playerIn.sendMessage(messageOut);
    }
    public static void defaultMessage(@NotNull ConsoleCommandSender console, String messageIn){
        Component messageOut = miniMessage.deserialize(messageIn);
        console.sendMessage(messageOut);
    }
    public static void mainPrefixMessage(@NotNull Player playerIn, String messageIn){
        Component messageOut = miniMessage.deserialize(messageIn);
        playerIn.sendMessage(Main.getPrefix().append(messageOut));
    }
    public static void mainPrefixMessage(@NotNull CommandSender commandSender, String messageIn){
        Component messageOut = miniMessage.deserialize(messageIn);
        commandSender.sendMessage(Main.getPrefix().append(messageOut));
    }
    public static void errorPrefixMessage(@NotNull Player playerIn, String messageIn){
        Component messageOut = miniMessage.deserialize(messageIn);
        playerIn.sendMessage(Main.getErrorPrefix().append(messageOut).color(TextColor.color(229, 53, 46)));
    }
    public static void errorPrefixMessage(CommandSender commandSender, String messageIn){
        Component messageOut = miniMessage.deserialize(messageIn);
        commandSender.sendMessage(Main.getErrorPrefix().append(messageOut).color(TextColor.color(229, 34, 19)));
    }

    public static String shortInteger(int duration){
        String string = "";
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        if(duration / 60 / 60 >=1){
            hours = duration / 60 /60;
            duration = duration - ((duration / 60 / 60) * 60 * 60);
        }
        if(duration / 60 >= 1){
            minutes = duration / 60;
            duration = duration - ((duration /60)*60);
        }
        if(duration >=1){
            seconds = duration;
        }
        if(hours!=0) {
            if (hours <= 9) {
                string = string + "0" + hours + "h ";
            } else {
                string = string + hours + "h ";
            }
        }
        if(minutes!=0) {
            if (minutes <= 9) {
                string = string + "0" + minutes + "m ";
            } else {
                string = string + minutes + "m ";
            }
        }
        if(seconds <=9){
            string= string+"0"+seconds+"s";
        }else{
            string= string+seconds+"s";
        }
        return string;
    }
}
