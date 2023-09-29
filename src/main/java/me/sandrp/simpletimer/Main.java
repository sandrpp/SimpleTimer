package me.sandrp.simpletimer;

import me.sandrp.simpletimer.timer.PauseScreen;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Main extends JavaPlugin {

    //Plugin info
    public static Main plugin;
    private static String author;
    private static String version;

    //MiniMessage
    public static final MiniMessage miniMessage = MiniMessage.miniMessage();
    public static final Component prefix = miniMessage.deserialize("<dark_grey>[</dark_grey><gradient:#fd0168:#c844e8><bold>SimpleTimer</bold><dark_grey>]</dark_grey> ");
    public static final Component errorPrefix = miniMessage.deserialize("Error: ");

    @Override
    public void onEnable() {
        version = this.getDescription().getVersion();
        if(this.getDescription().getAuthors().get(0) != null) {
            author = this.getDescription().getAuthors().get(0);
        }else{
            author = "none";
        }
        plugin = this;

        //pause Screen when not enabled
        PauseScreen.send();

        //register Commands
        CommandRegister.registerCommands(this.getServer());
    }

    @Override
    public void onDisable() {
    }

    public static Main getPlugin() {
        return plugin;
    }

    public static String getAuthor() {
        return author;
    }

    public static String getVersion() {
        return version;
    }
}
