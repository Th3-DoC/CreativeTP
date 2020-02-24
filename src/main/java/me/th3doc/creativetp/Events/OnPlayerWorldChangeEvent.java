package me.th3doc.creativetp.Events;

import me.th3doc.creativetp.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class OnPlayerWorldChangeEvent implements Listener {
    private Main main;

    public OnPlayerWorldChangeEvent(Main main) {
        this.main = main;
    }

    @EventHandler
    public void worldChange(PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        boolean isConfigWorld = p.getWorld().getName().equals(main.getWorld().getCWorldName());
        boolean isCreativeWorld = p.getWorld().getName().equals(main.getPlayer().getCLocName(p));

        if(isConfigWorld || isCreativeWorld) {
            main.getPlayer().setCreative(p);
            main.getPlayer().setPerms(p);
        } else {
            main.getPlayer().setSurvival(p);
            main.getPlayer().removePerms(p);
        }
    }
}
