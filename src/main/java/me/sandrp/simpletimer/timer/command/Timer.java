package me.sandrp.simpletimer.timer.command;

import me.sandrp.simpletimer.Main;
import me.sandrp.simpletimer.message.ActionBar;
import me.sandrp.simpletimer.message.Chat;
import me.sandrp.simpletimer.message.Console;
import me.sandrp.simpletimer.message.PluginInfo;
import net.kyori.adventure.util.RGBLike;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.css.RGBColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Timer extends Command {
    protected Timer(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    public Timer(){
        this("timer", "command for the timer", "timer", Arrays.asList("t"));
    }

    FileConfiguration config = Main.getPlugin().getConfig();
    public static boolean timerRunning = Main.getPlugin().getConfig().getBoolean("running");
    public static int timer = Main.getPlugin().getConfig().getInt("timer");
    public static BukkitRunnable runnable;

    private static String rgb1 = "#fd0168";
    private static String rgb2 = "#c844e8";

    private static boolean up =  Main.getPlugin().getConfig().getBoolean("up");

    private static int counter = 1;
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if(args.length == 0){
            //plugin information
            PluginInfo.send(sender, "Timer", Main.getAuthor(), Main.getVersion(), true);
            return true;
        }
        if(sender instanceof Player player){
            //command executed from non player
            if(!(player.hasPermission("timer.use"))){
                permissionMessage(player);
            }
            if (args.length == 1) {
                switch (args[0].toLowerCase()) {
                    case "start":
                    case "resume":
                        startTimer(player);
                        if(config.getBoolean("enabled")){
                            config.set("enabled", true);
                        }
                        break;
                    case "stop":
                    case "pause":
                        if (timerRunning) {
                            timerRunning = false;
                            runnable.cancel();
                            Chat.sendPrefixMessage(player, "<grey>you paused the timer!");
                        } else {
                            mustRun(player);
                        }
                        break;
                    case "disable":
                        if (timerRunning) {
                            timerRunning = false;
                            runnable.cancel();
                        }
                        config.set("enabled", false);
                        Main.getPlugin().saveConfig();
                        Chat.sendPrefixMessage(player, "<grey>you disabled the timer plugin!");
                        break;
                    case "enable":
                        config.set("enabled", true);
                        Main.getPlugin().saveConfig();
                        Chat.sendPrefixMessage(player, "<grey>you enabled the timer plugin!");
                        break;
                    case "reset":
                        timer = 0;
                        timerRunning = false;
                        if(!runnable.isCancelled()) {
                            runnable.cancel();
                        }
                        Chat.sendPrefixMessage(player, "<grey>the timer was reset!");
                        break;
                    case "toggle":
                        if(timerRunning){
                            timerRunning = false;
                            runnable.cancel();
                            Chat.sendPrefixMessage(player, "<grey>you paused the timer!");
                        } else{
                            startTimer(player);
                        }
                        break;
                    case "up":
                        if(up) {
                            Chat.sendErrorPrefixMessage(player, "the timer is already counting up");
                        }else {
                            up = true;
                            Main.getPlugin().getConfig().set("up", true);
                            Main.getPlugin().saveConfig();
                            Chat.sendPrefixMessage(player, "the timer now counts up");
                        }
                        break;
                    case "down":
                        if(!up) {
                            Chat.sendErrorPrefixMessage(player, "the timer is already counting down");
                        }else {
                            up = false;
                            Main.getPlugin().getConfig().set("up", false);
                            Main.getPlugin().saveConfig();
                            Chat.sendPrefixMessage(player, "the timer now counts down");
                        }
                        break;
                    default:
                        usageMessage(player);
                        break;
                }
            }else if(args.length == 2 && args[0].equals("set")){
                if(config.getBoolean("enabled")) {
                    try {
                        timer = Integer.parseInt(args[1]);
                        Chat.sendPrefixMessage(player, "<grey>the time was set to <#fd0168>" + args[1] + "s</#fd0168>!");
                        config.set("timer", timer);
                        Main.getPlugin().saveConfig();
                    } catch (NumberFormatException e) {
                        Chat.sendPrefixMessage(player, "<red>the second argument has to be a number!");
                    }
                }else{
                    Chat.sendErrorPrefixMessage(player, "the timer has to be enabled");
                }
            }
            else{
                usageMessage(player);
            }
        }
        else{
            mustPlayer();
        }
        return false;
    }

    //method to start the timer
    public static void startTimer(Player player){
        if (!timerRunning){
            if (!up && timer == 0) {
                Chat.sendErrorPrefixMessage(player, "the timer is set to 0. change it to a higher number to count backwards");
            }else{
                timerRunning = true;
                runnable = new BukkitRunnable() {
                    @Override
                    public void run() {
                        //send Timer in actionbar

                        ActionBar.sendBroadcastMessage((shortInteger(timer)));
                        if(up){
                            timer++;
                        } else{
                            if(timer==0){
                                Chat.sendPrefixMessage(player, "timer finished!");
                                timerRunning=false;
                                runnable.cancel();
                            }else {
                                timer--;
                            }
                        }
                        Main.getPlugin().getConfig().set("timer", timer);
                        Main.getPlugin().saveConfig();
                    }
                };
                runnable.runTaskTimer(Main.getPlugin(), 0, 20);
            }

        }
    }

    public static String shortInteger(int duration) {
        //String string = "<grey>Timer - <gradient:#fd0168:#c844e8><bold>";
        String string = "<grey>Timer - <gradient:" + rgb1 +":" + rgb2 + "><bold>";
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
        }else {
            string =string;
        }
        if(minutes!=0) {
            if (minutes <= 9) {
                string = string + "0" + minutes + "m ";
            } else {
                string = string + minutes + "m ";
            }
        }else{
            string= string;
        }
        if(seconds <=9){
            string= string+"0"+seconds+"s";
        }else{
            string= string+seconds+"s";
        }
        return string;
    }

    //usage message
    public static void usageMessage(Player player){
        Chat.sendMessage(player, "<blue>Usage:</blue> <grey>/timer [USAGE]</grey>");
    }
    //permission message
    public void permissionMessage(Player player){
        Chat.sendErrorPrefixMessage(player, "you don't have the permission to do this");
    }
    //timer has to be running message
    public void mustRun(Player player){
        Chat.sendErrorPrefixMessage(player, "the timer hast to be running to do this");
    }
    //have to be player to execute
    public static void mustPlayer(){
        Console.sendErrorPrefixMessage("you have to be a player to run this command");
    }

    public static boolean isRunning() {
        return timerRunning;
    }

    public static void stopRunnable() {
        runnable.cancel();
    }

    public static void setRunning(boolean running) {
        timerRunning = running;
    }

    public static void cancel(){
        runnable.cancel();
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        if(args.length == 1) {
            List<String> list = new ArrayList<String>();
            list.add("start");
            list.add("pause");
            list.add("reset");
            list.add("set");
            list.add("enable");
            list.add("disable");
            list.add("up");
            list.add("down");
            return list;
        }
        return null;
    }
}
