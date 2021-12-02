package itzshmulik.survivelist.survivelistchristmas.Commands;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.datafixers.util.Pair;
import itzshmulik.survivelist.survivelistchristmas.SurvivelistChristmas;
import net.minecraft.network.protocol.game.ClientboundAddPlayerPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoPacket;
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.EquipmentSlot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class CreateNpc implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if(sender instanceof Player player) {
               for(Player onlinePlayer : Bukkit.getOnlinePlayers()){
                   if(player.hasPermission("christmas.npc" ) || player.hasPermission("christmas.admin")){
                       // Variables
                       CraftPlayer craftPlayer = (CraftPlayer) onlinePlayer;
                       ServerPlayer sp = craftPlayer.getHandle();

                       MinecraftServer server = sp.getServer();
                       ServerLevel level = sp.getLevel();
                       GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "Santa Claus");

                       String signature = "Z9kiC8Li2dWe6ZgGtkK18IxjPbo7H/VHNwT5jYftzAL3qRgTCiFlpSzjkZ+mX8Bxu3ATpRcidpEep+Ipr/9lI55un1TLMPOKUbJVljKPWrtYbdvs7JcmzPIbuT0+X7UiNgnLzCMToc1CIGLPM1/JH3XfXR+0aViEMSEv3YPFMixkmGcwc3BS0/iuYXhNaK47ZNRTw216Dkc8mVRU6bZBHQpjB97Le23MWmjUkJPSM2BOg4AegbT2uSTUlvL1myafi00fjrW+fX7bsv6iwJY/M3pxO3m7e2mP7vAhzGojOeJG+MYKrjcf09sX1Pj1IdPkxKHOESRBP7jpA/1b8XLzFAetFX87ZOhvw7GiwawhuJTURPHMDB4sZgBlHDtUI2JlN4K9ySOazgtQueCzlauzbNAJeEZoJeApZU4+U/YFEiPmvgrMA3QnMIL2zexuj7sxW7eni6SGVgDbpL3gepgq5+zMyLm08xu+QN64hO5LDqJvAgDT1NATNcsFbz9Z32zBqmCA24GmWNfsappROTQw0r7J6Q5Tfz4ybCQfM9iyrBcpUYb1sSBPK0gB2NYWyUApHXFegWsAHt3Ais2xtKPxMC9sadbYQ7PWuqbqdi5gbvvsMq+FT8+OKlWl3Lclu2qpnM+3wno4QrpiI4OCutmvnHw+8Ph2/xMZiaAoEc+Ja2Y=";
                       String texture = "ewogICJ0aW1lc3RhbXAiIDogMTYwNzAwMTAwNjAxOSwKICAicHJvZmlsZUlkIiA6ICJkMGI4MjE1OThmMTE0NzI1ODBmNmNiZTliOGUxYmU3MCIsCiAgInByb2ZpbGVOYW1lIiA6ICJqYmFydHl5IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdkYTZkZGNmZTRhMjUxZDU1ZmY2MDAxYjQ5ZGU3NTE0ZThlNTBjZWE2Y2IyZWRmMWVkODA0ZmYxOGZlZWUzODQiCiAgICB9CiAgfQp9";

                       // Setting the npc's texture
                       gameProfile.getProperties().put("textures", new Property("textures", texture, signature));

                       // Spawning the npc
                       ServerPlayer npc = new ServerPlayer(server, level, gameProfile);
                       npc.setPos(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());

                       ServerGamePacketListenerImpl ps = sp.connection;
                       ps.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, npc));
                       ps.send(new ClientboundAddPlayerPacket(npc));

                       // Set Items in hand
                       ItemStack bannerPattern = new ItemStack(Material.CREEPER_BANNER_PATTERN);
                       ps.send(new ClientboundSetEquipmentPacket(npc.getBukkitEntity().getEntityId(), List.of(new Pair<>(EquipmentSlot.MAINHAND, CraftItemStack.asNMSCopy(bannerPattern)))));

                       player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b[Survivelist Christmas] Successfully spawned santa ho ho ho!"));

                       SurvivelistChristmas.getNpcs().add(npc);
                   }else{
                       player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou don't have the permission to use that command!"));
                   }
               }

        }
        else{
            sender.sendMessage("You have to be a player to use that command!");
        }

        return true;
    }
}
