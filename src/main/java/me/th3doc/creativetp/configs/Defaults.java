package me.th3doc.creativetp.configs;

import me.th3doc.creativetp.Main;
import me.th3doc.creativetp.enums.Chat;
import me.th3doc.creativetp.enums.FileIO;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;

public class Defaults {
    Main main;

    public Defaults(Main main) {
        this.main = main;
    }
    /*
    Default @configs
    */
    protected void defaultConfigStatus() {
        //**********
        //config.yml
        //**********
        File file = new File(main.getDataFolder(), FileIO._config.name);
        try {
            if(!main.getDataFolder().exists()) {
                main.getDataFolder().mkdirs();
            }
            if (!file.exists()) {
                main.getLogger().info(Chat._configMissing.txt);
                main.saveDefaultConfig();
            }
            main.getLogger().info(Chat._configFound.txt);
            try {
                main.getConfig().load(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //**********
        //Worlds.yml
        //**********
        File worldsConfig = new File(main.getDataFolder(), FileIO._worlds.name);
        try {
            if (!worldsConfig.exists()) {
                main.getLogger().info(Chat._worldFileMissing.txt);
                YamlConfiguration customConfig = YamlConfiguration.loadConfiguration(worldsConfig);
                //LOOK FOR DEFAULTS IN JAR
                Reader customConfigStream = new InputStreamReader(main.getResource(FileIO._worlds.name), "UTF8");
                if (customConfigStream != null) {
                    YamlConfiguration configStream = YamlConfiguration.loadConfiguration(customConfigStream);
                    customConfig.setDefaults(configStream);
                    customConfig.options().copyDefaults(true);
                    customConfig.save(worldsConfig);
                }
            }
            main.getLogger().info(Chat._worldFileFound.txt);
            try {
                YamlConfiguration.loadConfiguration(worldsConfig);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
