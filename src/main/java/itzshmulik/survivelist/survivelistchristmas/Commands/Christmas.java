package itzshmulik.survivelist.survivelistchristmas.Commands;

import itzshmulik.survivelist.survivelistchristmas.Events.ClickEvent;
import itzshmulik.survivelist.survivelistchristmas.Events.deathEvent;
import itzshmulik.survivelist.survivelistchristmas.SurvivelistChristmas;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Christmas implements CommandExecutor {

    private  final JavaPlugin plugin = JavaPlugin.getProvidingPlugin(getClass());

    public static int trees = 0;
    public static int gifts = 0;
    public static int snowballs = 0;
    public static int votes = 0;
    public static int gifted = 0;
    public static int cookies = 0;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(sender instanceof Player){

            Player player = (Player) sender;
            Inventory gui = Bukkit.createInventory(player, 9, "Christmas Quests");

            PersistentDataContainer data = player.getPersistentDataContainer();
            int kills = data.get(new NamespacedKey(SurvivelistChristmas.getPlugin(), "killCounter"), PersistentDataType.INTEGER);

            // Quest 1

            ItemStack ironSword = new ItemStack(Material.IRON_SWORD);
            ItemMeta ironSwordMeta = ironSword.getItemMeta();
            ironSwordMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eGetting in the naughty list"));
            ArrayList<String> ironSwordLore = new ArrayList<>();
            ironSwordLore.add(ChatColor.translateAlternateColorCodes('&', "&fKill 5 players"));
            ironSwordLore.add(ChatColor.translateAlternateColorCodes('&', "&fCompleted: " + "&e" + kills + " / 5"));
            ironSwordMeta.setLore(ironSwordLore);

            ironSword.setItemMeta(ironSwordMeta);
            gui.setItem(1, ironSword);

            // Quest 2
            ItemStack fishing = new ItemStack(Material.FISHING_ROD);
            ItemMeta fishingMeta = fishing.getItemMeta();
            fishingMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eGift grabber"));
            ArrayList<String> fishingLore = new ArrayList<>();
            fishingLore.add(ChatColor.translateAlternateColorCodes('&', "&fFind all gifts at spawn"));
            fishingLore.add(ChatColor.translateAlternateColorCodes('&', "&fCompleted: " + "&e" + gifts + " / 5"));
            fishingMeta.setLore(fishingLore);

            fishing.setItemMeta(fishingMeta);
            gui.setItem(2, fishing);

            // Quest 3
            ItemStack snowball = new ItemStack(Material.SNOWBALL);
            ItemMeta snowballMeta = snowball.getItemMeta();
            snowballMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eSnowball fight!"));
            ArrayList<String> snowballLore = new ArrayList<>();
            snowballLore.add(ChatColor.translateAlternateColorCodes('&', "&fThrow 32 snowballs"));
            snowballLore.add(ChatColor.translateAlternateColorCodes('&', "&fCompleted: " + "&e" + snowballs + " / 32"));
            snowballMeta.setLore(snowballLore);

            snowball.setItemMeta(snowballMeta);
            gui.setItem(3, snowball);

            // Quest 4
            ItemStack tree = new ItemStack(Material.SPRUCE_SAPLING);
            ItemMeta treeMeta = tree.getItemMeta();
            treeMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eWhat a nice holiday tree!"));
            ArrayList<String> treeLore = new ArrayList<>();
            treeLore.add(ChatColor.translateAlternateColorCodes('&', "&fPlant 10 spruce trees"));
            treeLore.add(ChatColor.translateAlternateColorCodes('&', "&fCompleted: " + "&e" + trees + " / 10"));
            treeMeta.setLore(treeLore);

            tree.setItemMeta(treeMeta);
            gui.setItem(4, tree);

            // Quest 5
            ItemStack paper = new ItemStack(Material.PAPER);
            ItemMeta paperMeta = paper.getItemMeta();
            paperMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eA gift for the server"));
            ArrayList<String> paperLore = new ArrayList<>();
            paperLore.add(ChatColor.translateAlternateColorCodes('&', "&fVote for the server"));
            paperLore.add(ChatColor.translateAlternateColorCodes('&', "&fCompleted: " + "&e" + votes + " / 1"));
            paperMeta.setLore(paperLore);

            paper.setItemMeta(paperMeta);
            gui.setItem(5, paper);

            // Quest 6
            ItemStack diamond = new ItemStack(Material.DIAMOND);
            ItemMeta diamondMeta = diamond.getItemMeta();
            diamondMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eSanta 2.0"));
            ArrayList<String> diamondLore = new ArrayList<>();
            diamondLore.add(ChatColor.translateAlternateColorCodes('&', "&fGift a player something for the holiday"));
            diamondLore.add(ChatColor.translateAlternateColorCodes('&', "&fCompleted: " + "&e" + gifted + " / 1"));
            diamondMeta.setLore(diamondLore);

            diamond.setItemMeta(diamondMeta);
            gui.setItem(6, diamond);

            // Quest 7
            ItemStack cookie = new ItemStack(Material.COOKIE);
            ItemMeta cookieMeta = cookie.getItemMeta();
            cookieMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eI would like a ginger bread"));
            ArrayList<String> cookieLore = new ArrayList<>();
            cookieLore.add(ChatColor.translateAlternateColorCodes('&', "&fMake 64 cookies"));
            cookieLore.add(ChatColor.translateAlternateColorCodes('&', "&fCompleted: " + "&e" + cookies + " / 64"));
            cookieMeta.setLore(cookieLore);

            cookie.setItemMeta(cookieMeta);
            gui.setItem(7, cookie);

            // Null
            ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
            ItemMeta glassMeta = glass.getItemMeta();
            glassMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f"));
            glass.setItemMeta(glassMeta);
            gui.setItem(0, glass);
            gui.setItem(8, glass);


            player.openInventory(gui);

        }else{
            sender.sendMessage("You have to be a player to use that command!");
        }

        return false;
    }
}
