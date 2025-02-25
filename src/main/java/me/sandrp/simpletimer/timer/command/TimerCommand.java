package me.sandrp.simpletimer.timer.command;


import me.sandrp.simpletimer.Main;
import me.sandrp.simpletimer.message.MessageManager;
import me.sandrp.simpletimer.timer.TimerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
        if(!(sender instanceof Player player)){
            MessageManager.errorPrefixMessage(sender, "<red>you must be a player to use this command!");
            return false;
        }
        if (!player.hasPermission("simpletimer.use") || !player.isOp()){
            MessageManager.errorPrefixMessage(player, "you do not have permission to use this command!");
            return false;
        }
        if(args.length < 1){
            MessageManager.errorPrefixMessage(player, "<red>you must specify a subcommand!");
            return false;
        }
        switch (args[0].toLowerCase()){
            case "start":
            case "resume":
                if(!timerManager.startTimer()){
                    MessageManager.mainPrefixMessage(player, "the timer is already running!");
                    break;
                }
                MessageManager.mainPrefixMessage(player, "you startet the timer!");
                break;
            case "stop":
            case "pause":
                if(!timerManager.stopTimer()){
                    MessageManager.mainPrefixMessage(player, "the timer is already paused!");
                    break;
                }
                MessageManager.mainPrefixMessage(player, "you paused the timer!");
                break;
            case "toggle":
                if(timerManager.toggleTimer()){
                    MessageManager.mainPrefixMessage(player, "you paused the timer!");
                    break;
                }
                MessageManager.mainPrefixMessage(player, "you started the timer!");
                break;
            case "reset":
                timerManager.resetTimer();
                MessageManager.mainPrefixMessage(player, "you reset the timer!");
                break;
            case "set":
                try {
                    timerManager.setTimer(Integer.parseInt(args[1]));
                    MessageManager.mainPrefixMessage(player, "you set the timer to " + args[1] + "!");
                } catch (NumberFormatException e){
                    MessageManager.errorPrefixMessage(player, "<red>you must specify a number!");
                }
                break;
            case "visibility":
                timerManager.toggleTimerVisibility();
                MessageManager.mainPrefixMessage(player, "you toggled the timer visibility!");
                break;
            case "up":
                timerManager.setUp(true);
                MessageManager.mainPrefixMessage(player, "you set the timer to count up!");
                break;
            case "down":
                timerManager.setUp(false);
                MessageManager.mainPrefixMessage(player, "you set the timer to count down!");
                break;
            case "color":
                if(args.length < 2){
                    MessageManager.errorPrefixMessage(player, "<red>you must specify a color!");
                    break;
                }
                if (!timerManager.setColor(args[1].toLowerCase())){
                    MessageManager.errorPrefixMessage(player, "<red>you must specify a valid color!");
                    break;
                }
                MessageManager.mainPrefixMessage(player, "you set the timer color to " + args[1] + "!");
                break;
        }
        return true;
    }

    @Override
        public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String @NotNull [] args) throws IllegalArgumentException {
            List<String> subcommands = List.of("start", "resume", "stop", "pause", "toggle", "reset", "set", "visibility", "up", "down", "color");
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
            return completions;
        }
}
