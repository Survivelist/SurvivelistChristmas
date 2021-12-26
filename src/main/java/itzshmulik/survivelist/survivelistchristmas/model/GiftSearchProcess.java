package itzshmulik.survivelist.survivelistchristmas.model;

import itzshmulik.survivelist.survivelistchristmas.listeners.GiftSearchListener;
import itzshmulik.survivelist.survivelistchristmas.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Handle gift discovery counting.
 *
 * @since 1.0.0
 * @author ms5984
 */
public class GiftSearchProcess {
    private final GiftSearchListener giftSearchListener;
    final Player player;
    final GiftLocation gl;
    final QuestProcessor processor;

    public GiftSearchProcess(GiftSearchListener giftSearchListener, Player player, GiftLocation gl) {
        this.giftSearchListener = giftSearchListener;
        this.player = player;
        this.gl = gl;
        processor = new QuestProcessor(player, ChristmasQuest.QUEST_2);
    }

    public void process() {
        if (Bukkit.isPrimaryThread()) throw new IllegalStateException();
        final CompletableFuture<Boolean> isAlreadyComplete = new CompletableFuture<>();
        Bukkit.getScheduler().runTask(giftSearchListener.plugin, () -> {
            final Integer read = processor.questTracker.read(player);
            isAlreadyComplete.complete(read != null && read == ChristmasQuest.QUEST_2.target);
        });
        if (isAlreadyComplete.join()) {
            return;
        }
        final UUID playerUid = player.getUniqueId();
        synchronized (giftSearchListener.foundCache) {
            final HashSet<GiftLocation> playerSet = giftSearchListener.foundCache.computeIfAbsent(playerUid, k -> new HashSet<>());
            if (playerSet.add(gl)) {
                player.sendMessage(Util.color("&eYou found a Christmas gift!"));
            } else {
                player.sendMessage(Util.color("&eYou already found this gift!"));
            }
            if (playerSet.size() == ChristmasQuest.QUEST_2.target) {
                Bukkit.getScheduler().runTask(giftSearchListener.plugin, this::sync);
            }
        }
    }

    void sync() {
        for (int i = 0; i < ChristmasQuest.QUEST_2.target; ++i) {
            processor.process();
        }
    }
}
