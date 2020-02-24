package me.th3doc.creativetp.world;

import me.th3doc.creativetp.Main;
import me.th3doc.creativetp.enums.FileIO;

public class WorldManager extends SafeWorlds {
    private Main main;

    public WorldManager(Main main) {
        super(main);
        this.main = main;
    }
    /*
    onEnable
     */
    public void onEnable() {
        loadWorlds();
    }
    /*
    Get @config CreativeWorld name
     */
    public String getCWorldName() {
        try {
            if (config.get(FileIO._creativeWorld.name).toString() != null) {
                return config.get(FileIO._creativeWorld.name).toString();
            }
        } catch(Exception e) {e.printStackTrace();}
        return "";
    }
}
