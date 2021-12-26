package itzshmulik.survivelist.survivelistchristmas.listeners;

import itzshmulik.survivelist.survivelistchristmas.SurvivelistChristmas;
import itzshmulik.survivelist.survivelistchristmas.model.GiftLocation;
import itzshmulik.survivelist.survivelistchristmas.model.GiftSearchProcess;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

/**
 * Watches for gift block interactions (quest 2).
 * <p>
 * The detection process is somewhat limited;
 * a player must find all gifts during one server session (no restarts)
 * as only the count found is persisted--not the specific gifts found.
 *
 * @since 1.0.0
 * @author ms5984
 * @see GiftSearchProcess
 */
public class GiftSearchListener implements Listener {
    public final HashMap<UUID, HashSet<GiftLocation>> foundCache = new HashMap<>();
    public final SurvivelistChristmas plugin;

    public GiftSearchListener(@NotNull SurvivelistChristmas plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onGiftBlockInteract(PlayerInteractEvent e) {
        // check for clicked block
        final Block clickedBlock = e.getClickedBlock();
        if (clickedBlock == null) return;
        // check if gift block
        final GiftLocation test = GiftLocation.from(clickedBlock);
        final String key = plugin.searchGiftLocation(test);
        if (key == null) return; // not defined gift block
        // send data async
        Bukkit.getScheduler().runTaskAsynchronously(plugin, new GiftSearchProcess(this, e.getPlayer(), test)::process);
    }
}
