package me.sandrp.simpletimer.timer;
import me.sandrp.simpletimer.message.ActionBarMessage;
import me.sandrp.simpletimer.message.MessageManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import me.sandrp.simpletimer.Main;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TimerManager {
    FileConfiguration config = Main.getPlugin().getConfig();
    private int timer = config.getInt("timer");
    private boolean timerRunning = config.getBoolean("running");
    private BukkitRunnable timerRunnable;
    private boolean up =  config.getBoolean("up");
    private boolean visible = config.getBoolean("visible");

    private final PauseScreenManager pauseScreenManager = new PauseScreenManager();

    String hex1 = config.getString("hex1");
    String hex2 = config.getString("hex2");
    double phase = -1.0;
    boolean transitionUp = true;

    public boolean startTimer(){
        if(timerRunning) {
            return false;
        }
        visible = true;
        config.set("visible", true);
        timerRunning = true;
        config.set("running", true);
        Main.getPlugin().saveConfig();
        pauseScreenManager.stopPauseScreen();
        timerRunnable = new BukkitRunnable() {
            @Override
            public void run() {
                if(timerRunning){

                    if (phase == 1.0){
                        transitionUp = false;
                    } else if (phase == -1.0) {
                        transitionUp = true;
                    }
                    if (transitionUp) {
                        phase += 0.05;
                    } else {
                        phase -= 0.05;
                    }

                    BigDecimal bd = new BigDecimal(Double.toString(phase));
                    bd = bd.setScale(2, RoundingMode.HALF_UP);
                    phase = bd.doubleValue();

                    if (up) {
                        timer++;
                    }
                    else {
                        timer--;
                        if(timer == 0){
                            timerRunning = false;
                            config.set("running", false);
                            Main.getPlugin().saveConfig();
                            cancel();
                        }
                    }
                    ActionBarMessage.broadcastMessage("<grey>Timer • <gradient:" + hex1 + ":" + hex2 + ":" + phase + "><bold>" + MessageManager.shortInteger(timer));
                    config.set("timer", timer);
                    Main.getPlugin().saveConfig();
                }
            }
        };
        timerRunnable.runTaskTimer(Main.getPlugin(), 0L, 20L);
        return true;
    }

    public boolean stopTimer(){
        if(!timerRunning){
            return false;
        }
        timerRunning = false;
        config.set("running", false);
        Main.getPlugin().saveConfig();
        timerRunnable.cancel();
        pauseScreenManager.startPauseScreen();
        return true;
    }

    public void disableTimer(){
        if(timerRunning){
            timerRunning = false;
            config.set("running", false);
            Main.getPlugin().saveConfig();
        }
    }

    public void hideTimer(){
        if(timerRunning){
            timerRunning = false;
            config.set("running", false);
            Main.getPlugin().saveConfig();
            timerRunnable.cancel();
        } else {
            pauseScreenManager.stopPauseScreen();
        }
    }

    public boolean toggleTimerVisibility(){
        if(!visible){
            visible = true;
            config.set("visible", true);
            Main.getPlugin().saveConfig();
            pauseScreenManager.startPauseScreen();
            return true;
        }else{
            visible = false;
            config.set("visible", false);
            Main.getPlugin().saveConfig();
            hideTimer();
            return false;
        }
    }

    public boolean toggleTimer(){
        if(timerRunning){
            stopTimer();
            return true;
        }else{
            startTimer();
            return false;
        }
    }

    public void resetTimer(){
        stopTimer();
        timer = 0;
        config.set("timer", timer);
        Main.getPlugin().saveConfig();
    }

    public void setTimer(int time) {
        timer = time;
        config.set("timer", timer);
        Main.getPlugin().saveConfig();
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public PauseScreenManager getPauseScreenManager() {
        return pauseScreenManager;
    }

    public boolean setColor (String color){
        switch (color){
            case "red":
                setHex("#ad2831", "#800e13");
                break;
            case "green":
                setHex("#a3b18a", "#588157");
                break;
            case "blue":
                setHex("#1a759f", "#1e6091");
                break;
            case "yellow":
                setHex("#ffc300", "#ffaa00");
                break;
            case "purple":
                setHex("#9d4edd", "#5a189a");
                break;
            case "orange":
                setHex("#f4a261", "#e76f51");
                break;
            case "pink":
                setHex("#ffb3c1", "#ff758f");
                break;
            case "cyan":
                setHex("#78c6a3", "#469d89");
                break;
            case "white":
                setHex("#bbd0ff", "#ffd6ff");
                break;
            case "black":
                setHex("#343a40", "#212529");
                break;
            default:
                return false;
        }
        return true;
    }

public void setHex(String hex1, String hex2){
        this.hex1 = hex1;
        this.hex2 = hex2;
        config.set("hex1", hex1);
        config.set("hex2", hex2);
        Main.getPlugin().saveConfig();
    }
}
