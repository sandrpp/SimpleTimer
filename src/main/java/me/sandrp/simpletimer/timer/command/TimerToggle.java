package me.sandrp.simpletimer.timer.command;

import me.sandrp.simpletimer.message.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TimerToggle extends Command {
    public TimerToggle(){
        this("/", "toggle the timer", "//", new ArrayList<String>());
    }
    protected TimerToggle(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (sender instanceof Player player){
            if(args.length == 0){
                if(Timer.timerRunning){
                    Timer.setRunning(false);
                    Timer.cancel();
                    Chat.sendPrefixMessage(player,"<grey>you paused the timer!");
                }
                else{
                    Timer.startTimer(player);
                }
            }
        }else{
            Timer.mustPlayerMessage();
        }
        return false;
    }
}
