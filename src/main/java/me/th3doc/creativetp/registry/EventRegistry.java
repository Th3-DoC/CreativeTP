package me.th3doc.creativetp.registry;

import me.th3doc.creativetp.Events.*;
import me.th3doc.creativetp.Main;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public class EventRegistry extends CommandRegistry implements Listener {
    private Main main;

    public EventRegistry(Main main) {
        super(main);
        this.main = main;
    }

    protected void registerEvents() {
        PluginManager pm = main.getServer().getPluginManager();
        pm.registerEvents(new OnPlayerJoinEvent(main), main);
        pm.registerEvents(new OnWorldLoadEvent(main), main);
        pm.registerEvents(new OnPlayerWorldChangeEvent(main), main);
        pm.registerEvents(new OnPlayerMoveEvent(main), main);
        pm.registerEvents(new OnEntityDamageEvent(main), main);
    }
}
