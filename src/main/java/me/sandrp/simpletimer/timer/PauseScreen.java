package me.sandrp.simpletimer.timer;

import me.sandrp.simpletimer.Main;
import me.sandrp.simpletimer.timer.command.Timer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.swing.*;

public class PauseScreen {

    public static void send (){
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if(!Timer.isRunning() && Main.getPlugin().getConfig().getBoolean("enabled")) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendActionBar(Main.miniMessage.deserialize("<grey>Timer / <#da4918><bold>paused"));
                    }
                }
            }
        };runnable.runTaskTimer(Main.getPlugin(), 0L, 20L);
    }
}
