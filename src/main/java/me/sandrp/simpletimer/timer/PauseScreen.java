package me.sandrp.simpletimer.timer;

import me.sandrp.simpletimer.Main;
import me.sandrp.simpletimer.timer.command.Timer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.swing.*;

public class PauseScreen {

    static int counter = 1;
    public static void send (){
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if(!Timer.isRunning() && Main.getPlugin().getConfig().getBoolean("enabled")) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        try {
                            if(counter == 1){
                                player.sendActionBar(Main.miniMessage.deserialize("<grey>Timer - <#da4918><bold>paused"));
                                counter++;
                            }else if(counter == 2){
                                player.sendActionBar(Main.miniMessage.deserialize("<grey>Timer / <#da4918><bold>paused"));
                                counter++;
                            }
                            else if(counter == 3){
                                player.sendActionBar(Main.miniMessage.deserialize("<grey>Timer | <#da4918><bold>paused"));
                                counter++;
                            }
                            else if(counter == 4){
                                player.sendActionBar(Main.miniMessage.deserialize("<grey>Timer \\ <#da4918><bold>paused"));
                                counter = 1;
                            }
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        };runnable.runTaskTimer(Main.getPlugin(), 0L, 20L);
    }
}
