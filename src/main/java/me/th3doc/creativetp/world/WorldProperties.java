package me.th3doc.creativetp.world;

import me.th3doc.creativetp.Main;
import me.th3doc.creativetp.configs.WorldConfigManager;

public class WorldProperties extends WorldConfigManager {
    Main main;

    public WorldProperties(Main main) {
        super(main);
        this.main = main;
    }
    /*
    Does @param World exist
     */
    private void getWorldSection(String w) {
        try {
            if(config.getConfigurationSection(w) == null) {
                config.createSection(w);
                save();
            }
        } catch (Exception e) {e.printStackTrace();}
    }
    /*
    Get @param World section @param Var
     */
    private void getWorldVar(String w, String var) {
        try {
            if(config.getConfigurationSection(w).get(var) == null) {
                config.getConfigurationSection(w).addDefault(var, "");
                save();
            }
        } catch (Exception e) {e.printStackTrace();}
    }
    /*
    Get @param World environment
     */
    private void setEnv(String w) {
        getWorldSection(w);
        getWorldVar(w, "Environment");
        try {
            String env = main.getServer().getWorld(w).getEnvironment().name();
            config.getConfigurationSection(w).set("Environment", env);
            save();
        } catch(Exception e) {e.printStackTrace();}
    }
    /*
    Import @param World properties
     */
    protected void importProperties(String w, String env) {
        getWorldSection(w);
        getWorldVar(w, "Environment");
        try {
            config.getConfigurationSection(w).set("Environment", env);
            save();
        } catch(Exception e) {e.printStackTrace();}
    }
    /*
    Set @param World properties OnWorldCreate?OnWorldLoad
     */
    public void setProperties(String w) {
        setEnv(w);
    }
}
