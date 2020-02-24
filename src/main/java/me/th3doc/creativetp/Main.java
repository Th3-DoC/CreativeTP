package me.th3doc.creativetp;

import me.th3doc.creativetp.commands.ReloadCommand;
import me.th3doc.creativetp.player.PlayerManager;
import me.th3doc.creativetp.registry.RegistryManager;
import me.th3doc.creativetp.enums.Chat;
import me.th3doc.creativetp.world.WorldManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {
    private WorldManager world;
    private PlayerManager player;

    @Override
    public void onEnable() {
        //PLUGIN ENABLING
        RegistryManager registry = new RegistryManager(this);
        registry.onEnable();
        world = new WorldManager(this);
        world.onEnable();
        player = new PlayerManager(this);
        ReloadCommand reload = new ReloadCommand(this);
        reload.reload();
        getLogger().info(Chat._onEnable.txt);
    }
    @Override
    public void onDisable() {
        //Setup @config saving on disable/reload/disconnect/update HAVE?AND?POPULATE?MAPS/SETS?FOR?CONFIG?INFO
    }
    public WorldManager getWorld() {
        return world;
    }
    public PlayerManager getPlayer() {
        return player;
    }
}