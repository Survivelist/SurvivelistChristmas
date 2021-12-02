package itzshmulik.survivelist.survivelistchristmas.Commands;

import itzshmulik.survivelist.survivelistchristmas.Models.GUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Christmas implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        Player player = (Player) sender;
        if(sender instanceof Player){
            GUI menu = new GUI();
            menu.openInventory(player);
        }else{
            sender.sendMessage("You have to be a player to use that command!");
        }

        return false;
    }
}
