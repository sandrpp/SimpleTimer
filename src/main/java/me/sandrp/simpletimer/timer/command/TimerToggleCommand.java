package me.sandrp.simpletimer.timer.command;

import me.sandrp.simpletimer.Main;
import me.sandrp.simpletimer.message.MessageManager;
import me.sandrp.simpletimer.timer.TimerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TimerToggleCommand extends Command {
    public TimerToggleCommand(){
        this("/", "toggle the timer", "//", new ArrayList<>());
    }

    protected TimerToggleCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    TimerManager timerManager = Main.getTimerManager();

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String @NotNull [] args) {
        if (!sender.hasPermission("simpletimer.use") || !sender.isOp()){
            MessageManager.errorPrefixMessage(sender, "you do not have permission to use this command!");
            return false;
        }

        if (timerManager.toggleTimer()){
            MessageManager.mainPrefixMessage(sender, "you paused the timer!");
            return true;
        }
        MessageManager.mainPrefixMessage(sender, "you started the timer!");
        return true;
    }
}
