package me.th3doc.creativetp.configs;

import me.th3doc.creativetp.Main;
import me.th3doc.creativetp.enums.FileIO;
import org.bukkit.configuration.file.FileConfiguration;

public class WorldConfigManager extends ConfigHandler {
    private Main main;

    public WorldConfigManager(Main main) {
        super(main, "", "", FileIO._worlds.name);
        this.main = main;
    }
    /*
    Worlds Fields
     */
    protected FileConfiguration config = getConfigFile();
}
