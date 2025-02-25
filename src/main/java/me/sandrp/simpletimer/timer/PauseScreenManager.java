package me.sandrp.simpletimer.timer;

import me.sandrp.simpletimer.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PauseScreenManager {

    private int counter = 1;
    private BukkitRunnable pauseScreenRunnable;
    private boolean pauseScreenRunning = false;

    public void startPauseScreen (){
        if (pauseScreenRunning){
            return;
        }
        pauseScreenRunning = true;
        pauseScreenRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if(counter == 1){
                        player.sendActionBar(Main.miniMessage.deserialize("<grey>Timer - <#da4918><bold>paused"));
                        counter++;
                    }
                    else if(counter == 2){
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
                }
            }
        };pauseScreenRunnable.runTaskTimer(Main.getPlugin(), 0L, 12L);
    }

    public void stopPauseScreen(){
        pauseScreenRunning = false;
        pauseScreenRunnable.cancel();
    }
}
