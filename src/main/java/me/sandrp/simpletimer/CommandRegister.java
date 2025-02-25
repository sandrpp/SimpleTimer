package me.sandrp.simpletimer;

import me.sandrp.simpletimer.timer.command.TimerCommand;
import me.sandrp.simpletimer.timer.command.TimerToggleCommand;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class CommandRegister {

    private static final @NotNull Map<String, Command> COMMANDS = new HashMap<>();

    static {
        //commands
        COMMANDS.put("timer", new TimerCommand());
        COMMANDS.put("/", new TimerToggleCommand());
    }

    public static void registerCommands(@NotNull Server server){
        COMMANDS.forEach(((s, command) -> server.getCommandMap().register(s, "simpletimer", command)));
    }

    public static void unregisterCommands(@NotNull Server server){
        server.getCommandMap().clearCommands();
    }
}
