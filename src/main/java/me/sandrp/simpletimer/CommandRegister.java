package me.sandrp.simpletimer;

import me.sandrp.simpletimer.timer.command.TimerCommand;
import me.sandrp.simpletimer.timer.command.TimerToggleCommand;
import org.bukkit.Bukkit;
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
    public static void registerCommand(@NotNull String name, @NotNull Command command, @NotNull Server server){
        COMMANDS.put(name, command);
        CommandRegister.registerCommands(server);
    }

    public static void registerCommand(@NotNull String name, @NotNull Command command){
        COMMANDS.put(name, command);
        CommandRegister.registerCommands();
    }

    public static void registerCommands(@NotNull Server server){
        COMMANDS.forEach(((s, command) -> server.getCommandMap().register(s, "Commands", command)));
    }

    public static void registerCommands(){
        CommandRegister.registerCommands(Bukkit.getServer());
    }

    public static void unregisterCommands(@NotNull Server server){
        server.getCommandMap().clearCommands();
    }

    public static void unregisterCommands(){
        CommandRegister.unregisterCommands(Bukkit.getServer());
    }


}
