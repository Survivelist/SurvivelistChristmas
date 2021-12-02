package itzshmulik.survivelist.survivelistchristmas;

import itzshmulik.survivelist.survivelistchristmas.Commands.CreateNpc;
import itzshmulik.survivelist.survivelistchristmas.Commands.Christmas;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class SurvivelistChristmas extends JavaPlugin {

    private static List<ServerPlayer> npcs = new ArrayList<>();
    public static SurvivelistChristmas plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;

        getCommand("npc").setExecutor(new CreateNpc());
        getCommand("christmas").setExecutor(new Christmas());
    }

    public static  List<ServerPlayer> getNpcs() {
        return npcs;
    }

    public static SurvivelistChristmas getPlugin() {
        return  plugin;
    }
}
