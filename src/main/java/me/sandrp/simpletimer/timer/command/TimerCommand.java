package me.sandrp.simpletimer.timer.command;


import me.sandrp.simpletimer.Main;
import me.sandrp.simpletimer.message.MessageManager;
import me.sandrp.simpletimer.message.PluginInfo;
import me.sandrp.simpletimer.timer.TimerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TimerCommand extends Command {
    protected TimerCommand(@NotNull String name) {
        super(name);
    }

    public TimerCommand(){
        this("timer");
    }

    TimerManager timerManager = Main.getTimerManager();

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
        if (!sender.hasPermission("simpletimer.use") || !sender.isOp()){
            MessageManager.errorPrefixMessage(sender, "you do not have permission to use this command!");
            return false;
        }
        if(args.length < 1){
            MessageManager.errorPrefixMessage(sender, "<red>you must specify a subcommand!");
            return false;
        }
        switch (args[0].toLowerCase()){
            case "start":
            case "resume":
                if(!timerManager.startTimer()){
                    MessageManager.mainPrefixMessage(sender, "the timer is already running!");
                    break;
                }
                MessageManager.mainPrefixMessage(sender, "you startet the timer!");
                break;
            case "stop":
            case "pause":
                if(!timerManager.stopTimer()){
                    MessageManager.mainPrefixMessage(sender, "the timer is already paused!");
                    break;
                }
                MessageManager.mainPrefixMessage(sender, "you paused the timer!");
                break;
            case "toggle":
                if(timerManager.toggleTimer()){
                    MessageManager.mainPrefixMessage(sender, "you paused the timer!");
                    break;
                }
                MessageManager.mainPrefixMessage(sender, "you started the timer!");
                break;
            case "reset":
                timerManager.resetTimer();
                MessageManager.mainPrefixMessage(sender, "you reset the timer!");
                break;
            case "set":
                try {
                    timerManager.setTimer(Integer.parseInt(args[1]));
                    MessageManager.mainPrefixMessage(sender, "you set the timer to " + args[1] + "s!");
                } catch (NumberFormatException e){
                    MessageManager.errorPrefixMessage(sender, "<red>you must specify a number!");
                }
                break;
            case "visibility":
                if(timerManager.toggleTimerVisibility()){
                    MessageManager.mainPrefixMessage(sender, "you toggled the timer visibility on!");
                    break;
                }
                MessageManager.mainPrefixMessage(sender, "you toggled the timer visibility off!");
                break;
            case "up":
                timerManager.setUp(true);
                MessageManager.mainPrefixMessage(sender, "you set the timer to count up!");
                break;
            case "down":
                timerManager.setUp(false);
                MessageManager.mainPrefixMessage(sender, "you set the timer to count down!");
                break;
            case "color":
                if(args.length < 2){
                    MessageManager.errorPrefixMessage(sender, "<red>you must specify a color!");
                    break;
                }
                if (!timerManager.setColor(args[1].toLowerCase())){
                    MessageManager.errorPrefixMessage(sender, "<red>you must specify a valid color!");
                    break;
                }
                MessageManager.mainPrefixMessage(sender, "you set the timer color to " + args[1] + "!");
                break;
            case "info":
                if (args.length == 2 && args[1].equalsIgnoreCase("full")){
                    PluginInfo.send(sender, true);
                    break;
                }
                PluginInfo.send(sender, false);
                break;
            default:
                MessageManager.errorPrefixMessage(sender, "<red>you must specify a valid subcommand!");
                break;
        }
        return true;
    }

    @Override
        public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String @NotNull [] args) throws IllegalArgumentException {
            List<String> subcommands = List.of("start", "resume", "stop", "pause", "toggle", "reset", "set", "visibility", "up", "down", "color", "info");
            List<String> colors = List.of("red", "green", "blue", "yellow", "purple", "white", "black", "orange", "blue", "cyan", "pink");
            List<String> completions = new ArrayList<>();

            if (args.length == 1) {
                String currentArg = args[0].toLowerCase();
                for (String subcommand : subcommands) {
                    if (subcommand.startsWith(currentArg)) {
                        completions.add(subcommand);
                    }
                }
            }
            if (args.length == 2 && args[0].equalsIgnoreCase("color")) {
                String currentArg = args[1].toLowerCase();
                for (String color : colors) {
                    if (color.startsWith(currentArg)) {
                        completions.add(color);
                    }
                }
            }
        if (args.length == 2 && args[0].equalsIgnoreCase("info")) {
            String currentArg = args[1].toLowerCase();
            if ("full".startsWith(currentArg)) {
                completions.add("full");
            }
        }
            return completions;
        }
}
