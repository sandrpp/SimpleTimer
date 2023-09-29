package me.sandrp.simpletimer.message;

import me.sandrp.simpletimer.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Console {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    private static final ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

    public static void sendMessage(String messageIn){
        Component messageOut = miniMessage.deserialize(messageIn);
        console.sendMessage(messageOut);
    }
    public static void sendErrorPrefixMessage(String messageIn){
        Component messageOut = miniMessage.deserialize(messageIn);
        console.sendMessage(Main.errorPrefix.append(messageOut).color(TextColor.color(229, 34, 19)));
    }
}
