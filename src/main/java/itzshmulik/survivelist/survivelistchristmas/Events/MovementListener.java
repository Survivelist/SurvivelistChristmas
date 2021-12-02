package itzshmulik.survivelist.survivelistchristmas.Events;

import itzshmulik.survivelist.survivelistchristmas.SurvivelistChristmas;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.network.protocol.game.ClientboundRotateHeadPacket;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import javax.swing.text.html.parser.Entity;

public class MovementListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {

        SurvivelistChristmas.getNpcs().stream()
                .forEach(npc -> {

                    Location location = npc.getBukkitEntity().getLocation();
                    location.setDirection(e.getPlayer().getLocation().subtract(location).toVector());
                    float yaw = location.getYaw();
                    float pitch = location.getPitch();

                    // Rotate head packet
                    ServerGamePacketListenerImpl ps = ((CraftPlayer) e.getPlayer()).getHandle().connection;

                    ps.send(new ClientboundRotateHeadPacket(npc, (byte) yaw));
                    ps.send(new ClientboundMoveEntityPacket.Rot(npc.getBukkitEntity().getEntityId(), (byte) ((yaw%360)*250/360), (byte) ((pitch%360)*256/360), false));

                });

    }
}
