package itzshmulik.survivelist.survivelistchristmas.util;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.Contract;

/**
 * Common utilities.
 *
 * @since 1.0.0
 * @author ms5984
 */
public class Util {
    private Util() {} // utility class
    /**
     * Process all ampersand colors in the string.
     *
     * @param text a string
     * @return the string with ampersands replaced
     */
    @Contract("null -> null")
    public static String color(String text) {
        if (text == null) return null;
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
