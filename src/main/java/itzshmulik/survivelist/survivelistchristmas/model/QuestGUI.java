package itzshmulik.survivelist.survivelistchristmas.model;

import com.github.sanctum.templates.Template;
import itzshmulik.survivelist.survivelistchristmas.SurvivelistChristmas;
import itzshmulik.survivelist.survivelistchristmas.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Displays player quests (with status).
 *
 * @since 1.0.0
 * @author ItzShmulik
 * @author ms5984
 */
public class QuestGUI {
    static final String QUEST_TITLE_PREFIX = "&e";
    static final String DIRECTION_PREFIX = "&f";
    /**
     * 0 = current count; 1 = target
     */
    static final String COMPLETED_TEMPLATE = "&fCompleted: &e{0} / {1}";

    final SurvivelistChristmas plugin;
    final String title = "Christmas Quests";
    final int size = 9;
    final HashMap<Integer, QuestGUIElement> elements = new HashMap<>();
    final ItemStack fillerItem = new Template.Builder().setName(Util.color("&f")).build().produce(Material.GRAY_STAINED_GLASS_PANE);

    public QuestGUI(@NotNull SurvivelistChristmas plugin) {
        this.plugin = plugin;
        // We define the format of the quest items ahead of time
        elements.put(1, new QuestGUIElement(ChristmasQuest.QUEST_1, Material.IRON_SWORD));
        elements.put(2, new QuestGUIElement(ChristmasQuest.QUEST_2, Material.FISHING_ROD));
        elements.put(3, new QuestGUIElement(ChristmasQuest.QUEST_3, Material.SNOWBALL));
        elements.put(4, new QuestGUIElement(ChristmasQuest.QUEST_4, Material.SPRUCE_SAPLING));
        elements.put(5, new QuestGUIElement(ChristmasQuest.QUEST_5, Material.PAPER));
        elements.put(6, new QuestGUIElement(ChristmasQuest.QUEST_6, Material.DIAMOND));
        elements.put(7, new QuestGUIElement(ChristmasQuest.QUEST_7, Material.COOKIE));
    }

    public Inventory generateInventory(Player player) {
        final Inventory gui = Bukkit.createInventory(null, size, title);

        // Quest elements
        for (Map.Entry<Integer, QuestGUIElement> entry : elements.entrySet()) {
            gui.setItem(entry.getKey(), entry.getValue().generateItemFor(player));
        }

        // Filler
        gui.setItem(0, fillerItem);
        gui.setItem(8, fillerItem);

        // Listen on events
        plugin.getServer().getPluginManager().registerEvents(new GUIListener(gui), plugin);
        return gui;
    }

    static class QuestGUIElement {
        final ChristmasQuest quest;
        final Template.Builder builder;
        final Material material;
        final QuestTracker tracker;

        QuestGUIElement(ChristmasQuest quest, Material material) {
            this.quest = quest;
            this.builder = new Template.Builder()
                    .setName(Util.color(QUEST_TITLE_PREFIX + quest.title))
                    .setLore(DIRECTION_PREFIX + quest.directions + "\n" + COMPLETED_TEMPLATE.replace("{1}", String.valueOf(quest.target)));
            this.material = material;
            tracker = new QuestTracker(quest);
        }

        public ItemStack generateItemFor(Player player) {
            final Integer read = tracker.read(player);
            if (read != null) {
                return builder.copy()
                        .transformLore(s -> s.replace("{0}", String.valueOf(read)))
                        .build().produce(material);
            }
            return builder.copy().transformLore(QuestGUI::initialZero).build().produce(material);
        }
    }

    static class GUIListener extends BukkitRunnable implements Listener {
        final Inventory inv;

        GUIListener(Inventory inv) {
            this.inv = inv;
            // extra cleanup step, may or may not be needed
            runTaskTimer(SurvivelistChristmas.getPlugin(), 20L, 10L);
        }

        @EventHandler
        public void onClickInventory(InventoryClickEvent e) {
            if (e.getClickedInventory() == inv) {
                e.setCancelled(true);
            }
        }

        @EventHandler
        public void onCloseInventory(InventoryCloseEvent e) {
            if (e.getInventory() == inv) {
                HandlerList.unregisterAll(this);
            }
        }

        @Override
        public void run() {
            if (inv == null || inv.getViewers().isEmpty()) {
                cancel();
                HandlerList.unregisterAll(this);
            }
        }
    }

    static String initialZero(String replace) {
        return replace.replace("{0}", "0");
    }
}
