package me.th3doc.creativetp.configs;

import me.th3doc.creativetp.Main;
import me.th3doc.creativetp.enums.FileIO;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigHandler {
    private File file;
    private FileConfiguration config;

    public ConfigHandler(Main main, String folder, String uuid, String yml) {

        this.file = new File(main.getDataFolder(),
                FileIO._folderSeperator.name + folder +
                        FileIO._folderSeperator.name + uuid +
                        FileIO._folderSeperator.name + yml);
        if(!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch(IOException e){
                e.printStackTrace();
            }
        }

        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfigFile() {
        return config;
    }
}
