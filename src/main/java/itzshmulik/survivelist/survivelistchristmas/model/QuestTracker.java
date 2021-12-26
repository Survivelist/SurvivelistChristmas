package itzshmulik.survivelist.survivelistchristmas.model;

import itzshmulik.survivelist.survivelistchristmas.SurvivelistChristmas;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * Manage quest state.
 * <p>
 * <i>NamespaceKeys must reference a plugin, so while from a design perspective
 * this functionality should be contained in {@link ChristmasQuest} it had
 * to be extracted to this separate class.</i>
 *
 * @since 1.0.0
 * @author ms5984
 */
final class QuestTracker {
    final ChristmasQuest quest;
    final NamespacedKey key;

    QuestTracker(ChristmasQuest quest) {
        this.quest = quest;
        this.key = quest.asNamespacedKey(SurvivelistChristmas.getPlugin());
    }

    @Nullable Integer read(@NotNull Player player) {
        final PersistentDataContainer playerData = player.getPersistentDataContainer();
        if (!playerData.has(key, PersistentDataType.INTEGER)) {
            return null;
        }
        final Integer value;
        try {
            value = playerData.get(key, PersistentDataType.INTEGER);
        } catch (IllegalArgumentException ignored) {
            return null;
        }
        return value;
    }

    int increment(@NotNull Player player) {
        final int inc = Optional.ofNullable(read(player)).orElse(0) + 1;
        player.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, inc);
        return inc;
    }
}
