package itzshmulik.survivelist.survivelistchristmas.Events;

import itzshmulik.survivelist.survivelistchristmas.Commands.Christmas;
import itzshmulik.survivelist.survivelistchristmas.SurvivelistChristmas;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class ClickEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getWhoClicked() instanceof Player){

            Player player = (Player) e.getWhoClicked();

            PersistentDataContainer data = player.getPersistentDataContainer();
            int kills = data.get(new NamespacedKey(SurvivelistChristmas.getPlugin(), "killCounter"), PersistentDataType.INTEGER);

            if(e.getView().getBottomInventory() != e.getClickedInventory()){
                e.setCancelled(true);
            }

        }
    }
}
