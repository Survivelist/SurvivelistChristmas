package itzshmulik.survivelist.survivelistchristmas.model;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Optional;

/**
 * Process quests and give rewards.
 * <p>
 * Designed for easy next-tick scheduling with
 * {@link BukkitScheduler#runTask(Plugin, Runnable)}.
 *
 * @since 2.0.0
 * @author ms5984
 */
public class QuestProcessor {
    final QuestTracker questTracker;
    final ChristmasQuest quest;
    final Player player;

    public QuestProcessor(Player player, ChristmasQuest quest) {
        questTracker = new QuestTracker(quest);
        this.quest = quest;
        this.player = player;
    }

    /**
     * Read saved state; increment by one; evaluate if they need the reward.
     */
    public void process() {
        final int value = Optional.ofNullable(questTracker.read(player)).orElse(0);
        if (value < quest.getTargetCount()) {
            final int inc = questTracker.increment(player);
            if (inc == quest.getTargetCount()) {
                quest.giveReward(player);
            }
        }
    }
}
