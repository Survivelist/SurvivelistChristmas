package itzshmulik.survivelist.survivelistchristmas;

import itzshmulik.survivelist.survivelistchristmas.Commands.Christmas;
import itzshmulik.survivelist.survivelistchristmas.Events.ClickEvent;
import itzshmulik.survivelist.survivelistchristmas.Events.deathEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SurvivelistChristmas extends JavaPlugin {

    public static SurvivelistChristmas plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;
        getCommand("christmas").setExecutor(new Christmas());

        PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(new ClickEvent(), this);
        pluginManager.registerEvents(new deathEvent(), this);
    }

    public static SurvivelistChristmas getPlugin() {
        return  plugin;
    }
}
