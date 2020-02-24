package me.th3doc.creativetp.Events;

import me.th3doc.creativetp.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;

public class OnWorldLoadEvent implements Listener {
    Main main;

    public OnWorldLoadEvent(Main main) {
        this.main = main;
    }

    @EventHandler
    private void onWorldLoad(WorldLoadEvent e) {
        String w = e.getWorld().getName();
        main.getWorld().importUnknownWorld(w);

    }

}
