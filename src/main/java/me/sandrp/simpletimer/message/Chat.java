package me.gemmerr.goodtimer.message;

import me.gemmerr.goodtimer.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class Chat {

    //Error Color (HEX): #e52213
    //Error Color (RGB): 229, 34, 19
    static MiniMessage miniMessage = MiniMessage.miniMessage();

    public static void sendMessage(@NotNull Player playerIn, String messageIn){
        Component messageOut = miniMessage.deserialize(messageIn);
        playerIn.sendMessage(messageOut);
    }
    public static void sendPrefixMessage(@NotNull Player playerIn, String messageIn){
        Component messageOut = miniMessage.deserialize(messageIn);
        playerIn.sendMessage(Main.prefix.append(messageOut));
    }
    public static void sendBroadcastMessage(String messageIn){
        Component messageOut = miniMessage.deserialize(messageIn);
        Bukkit.broadcast(messageOut);
    }
    public static void sendBroadcastPrefixMessage(String messageIn){
        Component messageOut = miniMessage.deserialize(messageIn);
        Bukkit.broadcast(Main.prefix.append(messageOut));
    }
    public static void sendErrorPrefixMessage(@NotNull Player playerIn, String messageIn){
        Component messageOut = miniMessage.deserialize(messageIn);
        playerIn.sendMessage(Main.errorPrefix.append(messageOut).color(TextColor.color(229, 34, 19)));
    }
}
