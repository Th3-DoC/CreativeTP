package me.th3doc.creativetp.Events;

import me.th3doc.creativetp.Main;
import me.th3doc.creativetp.enums.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnEntityDamageEvent implements Listener {
    Main main;

    public OnEntityDamageEvent(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onDamaged(EntityDamageEvent e) {
        if(main.getPlayer().isTP()) {
            Entity ent = e.getEntity();
            try {
                if (ent instanceof Player) {
                    Player p = (Player) ent;
                    if (main.getPlayer().getTpTaskID(p) != 0) {
                        int taskId = main.getPlayer().getTpTaskID(p);
                        Bukkit.getServer().getScheduler().cancelTask(taskId);
                        main.getPlayer().resetTpTaskID(p);
                        p.sendMessage(Chat._hurtCancel.txt);
                    }
                }
            } catch (NullPointerException ignored) {
            }
        }
    }
}
