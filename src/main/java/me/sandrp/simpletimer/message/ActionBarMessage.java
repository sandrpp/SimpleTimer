package me.sandrp.simpletimer.message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;

public class ActionBarMessage {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public static void broadcastMessage(String messageIn){
        Component messageOut = miniMessage.deserialize(messageIn);
        Bukkit.getOnlinePlayers().forEach(player -> player.sendActionBar(messageOut));
    }
}
