package itzshmulik.survivelist.survivelistchristmas.Events;

import itzshmulik.survivelist.survivelistchristmas.SurvivelistChristmas;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class deathEvent implements Listener {

    @EventHandler
    public void onKill(PlayerDeathEvent e){

        if(e.getEntity().getKiller() instanceof Player){
            Player p = (Player) e.getEntity().getKiller();

            PersistentDataContainer data = p.getPersistentDataContainer();
            int kills = data.get(new NamespacedKey(SurvivelistChristmas.getPlugin(), "killCounter"), PersistentDataType.INTEGER);

            if(kills > 5){
                kills++;
            }
            if(kills == 5){
                ItemStack coal = new ItemStack(Material.COAL);
                ItemMeta coalMeta = coal.getItemMeta();
                coalMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&8&lOh.. a coal"));
                ArrayList<String> coalLore = new ArrayList<>();
                coalLore.add(ChatColor.translateAlternateColorCodes('&', "&f"));
                coalLore.add(ChatColor.translateAlternateColorCodes('&', "&b&l❄ Christmas 2021 Item ❄"));
                coalMeta.setLore(coalLore);

                coal.setItemMeta(coalMeta);
                p.getInventory().addItem(coal);

                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l[Survivelist Christmas] &aQuest completed: Getting in the naughty list"));
            }


            data.set(new NamespacedKey(SurvivelistChristmas.getPlugin(), "killCounter"), PersistentDataType.INTEGER, kills);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        Player p = e.getPlayer();

        if(!p.getPersistentDataContainer().has(new NamespacedKey(SurvivelistChristmas.getPlugin(), "killCounter"), PersistentDataType.INTEGER)){
            p.getPersistentDataContainer().set(new NamespacedKey(SurvivelistChristmas.getPlugin(), "killCounter"), PersistentDataType.INTEGER, 0);
        }
    }
}
