package itzshmulik.survivelist.survivelistchristmas.Models;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUI {

    private static Inventory INV;

    public void register(){
        Inventory gui = Bukkit.createInventory(null, 9, "Christmas Quests");

        // Quest 1
        ItemStack ironSword = new ItemStack(Material.IRON_SWORD);
        ItemMeta ironSwordMeta = ironSword.getItemMeta();
        ironSwordMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eGet in the naughty list"));
        ironSword.setItemMeta(ironSwordMeta);
        gui.setItem(0, ironSword);

        // Quest 2
        ItemStack fishing = new ItemStack(Material.FISHING_ROD);
        ItemMeta fishingMeta = fishing.getItemMeta();
        fishingMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eGift grabber"));
        fishing.setItemMeta(fishingMeta);
        gui.setItem(1, fishing);

        // Quest 3
        ItemStack snowball = new ItemStack(Material.SNOWBALL);
        ItemMeta snowballMeta = snowball.getItemMeta();
        snowballMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eSnowball fight!"));
        snowball.setItemMeta(snowballMeta);
        gui.setItem(2, snowball);

        // Quest 4
        ItemStack tree = new ItemStack(Material.SPRUCE_SAPLING);
        ItemMeta treeMeta = tree.getItemMeta();
        treeMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eWhat a nice holiday tree!"));
        tree.setItemMeta(treeMeta);
        gui.setItem(3, tree);

        // Quest 5
        ItemStack paper = new ItemStack(Material.PAPER);
        ItemMeta paperMeta = paper.getItemMeta();
        paperMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eA gift for the server"));
        paper.setItemMeta(paperMeta);
        gui.setItem(4, paper);

        // Quest 6
        ItemStack diamond = new ItemStack(Material.DIAMOND);
        ItemMeta diamondMeta = diamond.getItemMeta();
        diamondMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eSanta 2.0"));
        diamond.setItemMeta(diamondMeta);
        gui.setItem(5, diamond);

        // Quest 7
        ItemStack cookie = new ItemStack(Material.COOKIE);
        ItemMeta cookieMeta = cookie.getItemMeta();
        cookieMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eI would like a ginger bread"));
        cookie.setItemMeta(cookieMeta);
        gui.setItem(6, cookie);

        // Quest 8


    }

    public Inventory getInventory(){
        return INV;
    }

    private void setInventory(Inventory gui){
        INV = gui;
    }

    public void openInventory(Player player){
        player.openInventory(INV);
    }
}
