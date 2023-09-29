package me.gemmerr.goodtimer.message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class Console {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    private static final ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

    public static void sendMessage(String messageIn){
        Component messageOut = miniMessage.deserialize(messageIn);
        console.sendMessage(messageOut);
    }
}
