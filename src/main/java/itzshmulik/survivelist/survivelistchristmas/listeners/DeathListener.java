package itzshmulik.survivelist.survivelistchristmas.listeners;

import itzshmulik.survivelist.survivelistchristmas.SurvivelistChristmas;
import itzshmulik.survivelist.survivelistchristmas.model.ChristmasQuest;
import itzshmulik.survivelist.survivelistchristmas.model.QuestProcessor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * @since 1.0
 * @author ItzShmulik
 * @author ms5984
 */
public class DeathListener implements Listener {

    @EventHandler
    public void onKill(PlayerDeathEvent e){
        final Player killer = e.getEntity().getKiller();
        if (killer == null) return;
        Bukkit.getScheduler().runTask(SurvivelistChristmas.getPlugin(), new QuestProcessor(killer, ChristmasQuest.QUEST_1)::process);
    }

}
