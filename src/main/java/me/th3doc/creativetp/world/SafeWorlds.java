package me.th3doc.creativetp.world;

import me.th3doc.creativetp.Main;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SafeWorlds extends WorldGenerator {
    private Main main;

    public SafeWorlds(Main main) {
        super(main);
        this.main = main;
    }
    /*
    Add @param World to @config SafeWorlds Create?Import
     */
    protected void addSafeWorld(String w) {
        List<String> sw = config.getStringList("SafeWorlds");
        sw.add(w);
        config.set("SafeWorlds", sw);
        save();
    }
    /*
    Remove @config UnknownWorlds
     */
    private void removeUnknown(String w) {
        List<String> uw = config.getStringList("UnknownWorlds");
        uw.remove(w);
        config.set("UnknownWorlds", uw);
        save();
    }
    /*
    Set @config UnknownWorlds for import to @config SafeWorlds
     */
    private void getUnknownWorlds() {
        File[] files =(main.getServer().getWorldContainer()).listFiles();
        List<String> worlds = new ArrayList<>();
        //CHECK WORLD CONTAINER FOR ALL WORLDS
        if(files != null) {
            for(File file : files) {
                boolean isWorld = new File(file, "level.dat").exists();

                if(isWorld) {
                    worlds.add(file.getName());
                }
            }
        }
        worlds.removeIf(main.getConfig().getStringList("BaseWorlds")::contains);
        worlds.removeIf(config.getStringList("SafeWorlds")::contains);
        config.set("UnknownWorlds", worlds);
        save();
    }
    /*
    Load @config SafeWorlds if not loaded
     */
    private void loadSafeWorlds() {
        try {
            int count = 0;
            for(String w : config.getStringList("SafeWorlds")) {
                if(!main.getServer().getWorlds().contains(w)) {
                    main.getServer().createWorld(new WorldCreator(w)
                            .environment(World.Environment.valueOf((String) config.getConfigurationSection(w).get("Environment"))));
                    count++;
                }
            }
            main.getLogger().info(count + " World(s) Loaded By CreativeTP");
        } catch(NullPointerException e) {e.printStackTrace();}
    }
    /*
    Import pre-loaded @param Worlds
     */
    private void importLoadedUnknownWorlds() {
        try {
            int count = 0;
            for(String w:config.getStringList("UnknownWorlds")) {
                if (main.getServer().getWorlds().contains(w)) {
                    setProperties(w);
                    addSafeWorld(w);
                    removeUnknown(w);
                    count++;
                }
            }
            main.getLogger().info("Data for " + count + " World(s) Saved From Other Plugins");
        } catch(Exception e) {e.printStackTrace();}
    }
    /*
    Get @config UnknownWorlds,
    load @config SafeWorlds,
    import loaded @config UnknownWorlds
     */
    protected void loadWorlds() {
        getUnknownWorlds();
        loadSafeWorlds();
        importLoadedUnknownWorlds();
    }
    /*
    Import loaded @param World
     */
    public void importUnknownWorld(String w) {
        if(config.getStringList("UnknownWorlds").contains(w)) {
            if(main.getServer().getWorlds().contains(w))
            setProperties(w);
            addSafeWorld(w);
            removeUnknown(w);
        }
    }
    /*
    Import @param World to @config SafeWorlds
     */
    public void importWorld(String w, String env) {

        if(!config.getStringList("SafeWorlds").contains(w)) {
            File[] files = main.getServer().getWorldContainer().listFiles();
            if(files != null) {
                for (File file:files) {
                    boolean validFolder = new File(file, "level.dat").exists();
                    if(validFolder) {
                        importProperties(w, env);
                        addSafeWorld(w);
                        removeUnknown(w);
                        main.getServer().createWorld(new WorldCreator(w).environment(World.Environment.valueOf(env)));
                    }
                }
            }
        }
    }
}
