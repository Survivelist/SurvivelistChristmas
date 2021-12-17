package itzshmulik.survivelist.survivelistchristmas.commands;

import itzshmulik.survivelist.survivelistchristmas.SurvivelistChristmas;
import itzshmulik.survivelist.survivelistchristmas.model.QuestGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @since 1.0
 * @author ItzShmulik
 */
public class ChristmasCommand implements CommandExecutor {

    final SurvivelistChristmas plugin;
    final QuestGUI gui;

    public ChristmasCommand(@NotNull SurvivelistChristmas plugin) {
        this.plugin = plugin;
        this.gui = new QuestGUI(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You have to be a player to use that command!");
        } else {
            final Player player = (Player) sender;
            player.openInventory(gui.generateInventory(player));
        }
        return true;
    }
}
