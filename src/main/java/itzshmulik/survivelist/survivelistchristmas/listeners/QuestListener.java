package itzshmulik.survivelist.survivelistchristmas.listeners;

import itzshmulik.survivelist.survivelistchristmas.SurvivelistChristmas;
import itzshmulik.survivelist.survivelistchristmas.model.ChristmasQuest;
import itzshmulik.survivelist.survivelistchristmas.model.QuestProcessor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.projectiles.ProjectileSource;

/**
 * @since 1.0
 * @author ItzShmulik
 * @author ms5984
 */
public class QuestListener implements Listener {

    @EventHandler
    public void onKill(PlayerDeathEvent e){
        final Player killer = e.getEntity().getKiller();
        if (killer == null) return;
        processNextTick(new QuestProcessor(killer, ChristmasQuest.QUEST_1));
    }

    @EventHandler(ignoreCancelled = true)
    public void onSnowballThrow(ProjectileLaunchEvent e) {
        final Projectile projectile = e.getEntity();
        if (projectile instanceof Snowball) {
            final ProjectileSource shooter = projectile.getShooter();
            if (shooter instanceof Player) {
                processNextTick(new QuestProcessor((Player) shooter, ChristmasQuest.QUEST_3));
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onSaplingPlantSuccess(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        final ItemStack itemInHand = e.getItem();
        if (itemInHand == null || itemInHand.getType() == Material.SPRUCE_SAPLING) return;
        // They definitely right-clicked with a spruce sapling, let's check the block now
        //noinspection ConstantConditions
        if (e.getClickedBlock().getRelative(e.getBlockFace()).getType() != Material.SPRUCE_SAPLING) {
            return;
        }
        processNextTick(new QuestProcessor(e.getPlayer(), ChristmasQuest.QUEST_4));
    }

    static void processNextTick(QuestProcessor processor) {
        Bukkit.getScheduler().runTask(SurvivelistChristmas.getPlugin(), processor::process);
    }
}
