package itzshmulik.survivelist.survivelistchristmas.listeners;

import itzshmulik.survivelist.survivelistchristmas.SurvivelistChristmas;
import itzshmulik.survivelist.survivelistchristmas.model.ChristmasQuest;
import itzshmulik.survivelist.survivelistchristmas.model.QuestProcessor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * Watches for craftings (quest 7).
 *
 * @since 1.0.0
 * @author ms5984
 */
public class CookieCraftingListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onMakeCookies(InventoryClickEvent e) {
        if (e.getSlotType() != InventoryType.SlotType.RESULT) return;
        if (e.getInventory().getType() != InventoryType.CRAFTING) return;
        // check item in slot
        final ItemStack currentItem = e.getCurrentItem();
        if (currentItem == null || currentItem.getType() != Material.COOKIE) return;
        // >>They're making cookies
        // take count of cookies in their inventory right now
        final int currentAmount = countCookies(e.getWhoClicked().getInventory());
        // send data to next tick
        Bukkit.getScheduler().runTask(SurvivelistChristmas.getPlugin(), new ComposeCheckNextTick((Player) e.getWhoClicked(), currentAmount)::process);
    }

    static class ComposeCheckNextTick {
        final Player player;
        final int currentCount;
        final QuestProcessor processor;

        ComposeCheckNextTick(Player player, int currentCount) {
            this.player = player;
            this.currentCount = currentCount;
            processor = new QuestProcessor(player, ChristmasQuest.QUEST_7);
        }

        void process() {
            final int diff = countCookies(player.getInventory()) - currentCount;
            for (int i = 0; i < diff; ++i) {
                // increment for every added cookie
                processor.process();
            }
        }
    }

    static int countCookies(PlayerInventory inventory) {
        // count their cookies
        int temp = 0;
        for (ItemStack slot : inventory.getStorageContents()) {
            //noinspection ConstantConditions (bad linting)
            if (slot == null || slot.getType() != Material.COOKIE) continue;
            temp += slot.getAmount();
        }
        return temp;
    }
}
