package itzshmulik.survivelist.survivelistchristmas;

import itzshmulik.survivelist.survivelistchristmas.commands.ChristmasCommand;
import itzshmulik.survivelist.survivelistchristmas.listeners.QuestListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @since 1.0
 * @author ItzShmulik
 */
public final class SurvivelistChristmas extends JavaPlugin {

    private static SurvivelistChristmas plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;
        //noinspection ConstantConditions
        getCommand("christmas").setExecutor(new ChristmasCommand(this));

        getServer().getPluginManager().registerEvents(new QuestListener(), this);
    }

    public static SurvivelistChristmas getPlugin() {
        return plugin;
    }
}
