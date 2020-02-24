package me.th3doc.creativetp.Events;

import me.th3doc.creativetp.Main;
import me.th3doc.creativetp.enums.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnPlayerMoveEvent implements Listener {
    Main main;

    public OnPlayerMoveEvent(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(main.getPlayer().isTP()) {
            try {
                if (main.getPlayer().getTpTaskID(p) != 0) {
                    int taskId = main.getPlayer().getTpTaskID(p);
                    Bukkit.getServer().getScheduler().cancelTask(taskId);
                    main.getPlayer().resetTpTaskID(p);
                    p.sendMessage(Chat._movedCancel.txt);
                }
            } catch (NullPointerException ignored) {
            }
        }
    }
}
