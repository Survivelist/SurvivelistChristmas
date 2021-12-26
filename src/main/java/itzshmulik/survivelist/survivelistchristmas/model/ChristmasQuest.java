package itzshmulik.survivelist.survivelistchristmas.model;

import com.github.sanctum.templates.Template;
import itzshmulik.survivelist.survivelistchristmas.SurvivelistChristmas;
import itzshmulik.survivelist.survivelistchristmas.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Represents the Christmas 2021 Quests.
 *
 * @since 1.0.0
 * @author ItzShmulik
 * @author ms5984
 */
public enum ChristmasQuest {
    /**
     * 5 Kills.
     */
    QUEST_1("Get on The Naughty List", "Kill 5 players", 5) {
        // delay production (not sure if ItemMetaFactory is always live)
        final Template template = new Template.Builder()
                .setName(Util.color("&8&lOh... some coal"))
                .setLore("&f\n&b&l❄ Christmas 2021 Item ❄")
                .transformLore(Util::color)
                .build();

        @Override
        public void giveReward(@NotNull Player player) {
            super.giveReward(player);
            ItemRunnable.schedule(player, template.produce(Material.COAL));
        }
    },
    /**
     * 5 Gifts (found).
     */
    QUEST_2("Gift Grabber", "Find all gifts at spawn", 5),
    /**
     * 32 Snowballs.
     */
    QUEST_3("Snowball Fight!", "Throw 32 Snowballs", 32),
    /**
     * 10 Trees.
     */
    QUEST_4("What a nice holiday tree!", "Plant 10 Spruce trees", 10),
    /**
     * 1 Vote.
     */
    QUEST_5("A Gift For The Server", "Vote for the server", 1),
    /**
     * 1 Gift (given).
     */
    QUEST_6("Santa 2.0", "Gift a player something for the holiday", 1),
    /**
     * 64 Cookies.
     */
    QUEST_7("I would like a ginger bread", "Make 64 cookies", 64),
    ;

    final String title;
    final String directions;
    final int target;

    ChristmasQuest(String title, String directions, int target) {
        this.title = title;
        this.directions = directions;
        this.target = target;
    }

    /**
     * Get the title of this quest.
     *
     * @return the title of this quest
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get directions of how to complete this quest.
     *
     * @return directions of how to complete this quest
     */
    public String getDirections() {
        return directions;
    }

    /**
     * Get the target count for this quest.
     *
     * @return the target count
     */
    public int getTargetCount() {
        return target;
    }

    /**
     * Give the reward of this quest to a player.
     * <p>
     * See overrides on constants.
     *
     * @param player a player
     */
    public void giveReward(@NotNull Player player) {
        player.sendMessage(Util.color("&b&l[Survivelist Christmas] &aQuest completed: " + getTitle()));
    }

    /**
     * Get the quest's enum name as a NamespacedKey.
     * <p>
     * <code>namespace</code> = {@link SurvivelistChristmas#getName()};
     * <code>key</code> = {@link #name()} lowercased
     *
     * @return a new NamespacedKey
     * @implNote If you use the {@link SurvivelistChristmas#getPlugin()}
     * getter to provide <code>plugin</code>, keep in mind that you
     * <b>cannot use that method in static field declarations.</b>
     */
    public NamespacedKey asNamespacedKey(@NotNull SurvivelistChristmas plugin) {
        return new NamespacedKey(plugin, name());
    }

    private static class ItemRunnable {
        final Player player;
        final ItemStack item;

        ItemRunnable(Player player, ItemStack item) {
            this.player = player;
            this.item = item;
        }

        void give() {
            player.getInventory().addItem(item);
        }

        static void schedule(Player player, ItemStack item) {
            Bukkit.getScheduler().runTask(SurvivelistChristmas.getPlugin(), new ItemRunnable(player, item)::give);
        }
    }
}
