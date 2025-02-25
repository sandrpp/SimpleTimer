package me.sandrp.simpletimer;

import me.sandrp.simpletimer.timer.PauseScreenManager;
import me.sandrp.simpletimer.timer.TimerManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Main extends JavaPlugin {

    //Plugin info
    public static Main plugin;

    //MiniMessage
    public static final MiniMessage miniMessage = MiniMessage.miniMessage();
    public static final Component prefix = miniMessage.deserialize("<dark_grey>[</dark_grey><gradient:#ffd6ff:#bbd0ff><bold>SimpleTimer</bold><dark_grey>]</dark_grey> ");
    public static final Component errorPrefix = miniMessage.deserialize("Error: ");
    private static TimerManager timerManager;

    @Override
    public void onEnable() {
        //set plugin
        plugin = this;

        //set timerManager & pauseScreenManager
        timerManager = new TimerManager();
        timerManager.getPauseScreenManager().startPauseScreen();

        //register Commands
        CommandRegister.registerCommands(this.getServer());
    }

    @Override
    public void onDisable() {
        timerManager.disableTimer();
        CommandRegister.unregisterCommands(this.getServer());
    }

    public static Main getPlugin() {
        return plugin;
    }


    @NotNull
    public static TimerManager getTimerManager() {
        return timerManager;
    }
}
