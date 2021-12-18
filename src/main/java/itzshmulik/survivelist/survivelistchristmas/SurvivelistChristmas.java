package itzshmulik.survivelist.survivelistchristmas;

import itzshmulik.survivelist.survivelistchristmas.commands.ChristmasCommand;
import itzshmulik.survivelist.survivelistchristmas.listeners.CookieCraftingListener;
import itzshmulik.survivelist.survivelistchristmas.listeners.GiftSearchListener;
import itzshmulik.survivelist.survivelistchristmas.listeners.QuestListener;
import itzshmulik.survivelist.survivelistchristmas.model.GiftLocation;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @since 1.0
 * @author ItzShmulik
 */
public final class SurvivelistChristmas extends JavaPlugin {

    static final Pattern GIFT_PREFIX = Pattern.compile("gift-");
    private static SurvivelistChristmas plugin;
    final HashMap<String, GiftLocation> giftLocations = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;
        saveDefaultConfig();
        readGifts();
        //noinspection ConstantConditions
        getCommand("christmas").setExecutor(new ChristmasCommand(this));

        getServer().getPluginManager().registerEvents(new QuestListener(), this);
        getServer().getPluginManager().registerEvents(new CookieCraftingListener(), this);
        getServer().getPluginManager().registerEvents(new GiftSearchListener(this), this);
    }

    public @Nullable String searchGiftLocation(@NotNull GiftLocation gl) {
        synchronized (giftLocations) {
            for (Map.Entry<String, GiftLocation> entry : giftLocations.entrySet()) {
                if (gl.equals(entry.getValue())) return entry.getKey();
            }
            return null;
        }
    }

    void readGifts() {
        final FileConfiguration config = getConfig();
        synchronized (giftLocations) {
            giftLocations.clear();
            for (String key : config.getKeys(false)) {
                if (key.startsWith("gift-")) {
                    Optional.of(key)
                            .map(config::getConfigurationSection)
                            .map(this::locFromSection)
                            .ifPresent(gl -> giftLocations.put(GIFT_PREFIX.matcher(key).replaceFirst(""), gl));
                }
            }
        }
    }

    GiftLocation locFromSection(ConfigurationSection section) {
        final Object x = section.get("x");
        final Object y = section.get("y");
        final Object z = section.get("z");
        if (x instanceof Number && y instanceof Number && z instanceof Number) {
            try {
                return new GiftLocation((int) x, (int) y, (int) z);
            } catch (IllegalArgumentException ignored) {
            }
        }
        return null;
    }

    public static SurvivelistChristmas getPlugin() {
        return plugin;
    }
}
