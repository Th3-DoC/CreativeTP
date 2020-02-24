package me.th3doc.creativetp.Events;

import me.th3doc.creativetp.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

public class OnPlayerJoinEvent implements Listener {
    private Main main;

    public OnPlayerJoinEvent(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        PermissionAttachment attachment = p.addAttachment(main);
        main.getPlayer().initializePerms(p, attachment);
    }

}
