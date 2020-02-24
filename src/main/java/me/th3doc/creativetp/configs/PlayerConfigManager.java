package me.th3doc.creativetp.configs;

import me.th3doc.creativetp.Main;
import me.th3doc.creativetp.enums.FileIO;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class PlayerConfigManager {
    private Main main;

    public PlayerConfigManager(Main main) {
        this.main = main;
        //SetConfig Variables/sections
    }
    /*
    Load @param Player @config Locations
     */
    protected void getLocConfig(String folder, Player p, String yml) {
        locConfig = new ConfigHandler(main, folder, p.getUniqueId().toString(), yml);
        config = locConfig.getConfigFile();
        this.player = p;
        loadLocConfig();
    }
    /*
    Location @config Field
     */
    private ConfigHandler locConfig;
    private Player player;
    private FileConfiguration config;
    private String playerName;
    private String uuid;
    private Location sLoc;
    private Location cLoc;
    private boolean isOpFlying;
    private boolean isFlying;
    private boolean isCreative;
    /*
    @param Player @config Field
     */

    private void loadLocConfig() {
        if(config.get(FileIO._playerName.name) == null) {
            config.set(FileIO._playerName.name, player.getName());
            this.playerName = player.getName();
        } else {
            this.playerName = (String) config.get(FileIO._playerName.name);
        }
        if(config.get(FileIO._playerUUID.name) == null) {
            config.set(FileIO._playerUUID.name, player.getUniqueId().toString());
            this.uuid = player.getUniqueId().toString();
        } else {
            this.uuid = (String) config.get(FileIO._playerUUID.name);
        }
        if(config.get(FileIO._isOpFlying.name) == null) {
            config.set(FileIO._isOpFlying.name, false);
            this.isOpFlying = false;
        } else {
            this.isOpFlying = (boolean) config.get(FileIO._isOpFlying.name);
        }
        if(config.get(FileIO._isFlying.name) == null) {
            config.set(FileIO._isFlying.name, false);
            this.isFlying = false;
        } else {
            this.isFlying = (boolean) config.get(FileIO._isFlying.name);
        }
        if(config.get(FileIO._inCreative.name) == null) {
            config.set(FileIO._inCreative.name, false);
            this.isCreative = false;
        } else {
            this.isCreative = (boolean) config.get(FileIO._inCreative.name);
        }
        if(config.get(FileIO._survivalLoc.name) == null) {
            this.sLoc = null;
            config.set(FileIO._survivalLoc.name, sLoc);
        } else {
            this.sLoc = (Location) config.get(FileIO._survivalLoc.name);
        }
        if(config.get(FileIO._creativeLoc.name) == null) {
            this.cLoc = null;
            config.set(FileIO._creativeLoc.name, cLoc);
        } else {
            this.cLoc = (Location) config.get(FileIO._creativeLoc.name);
        }
        locConfig.save();
    }

    protected void saveLocConfig() {
        config.set(FileIO._playerName.name, playerName);
        config.set(FileIO._playerUUID.name, uuid);
        config.set(FileIO._survivalLoc.name, sLoc);
        config.set(FileIO._creativeLoc.name, cLoc);
        config.set(FileIO._isOpFlying.name, isOpFlying);
        config.set(FileIO._isFlying.name, isFlying);
        config.set(FileIO._inCreative.name, isCreative);
        locConfig.save();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(Player p) {
        this.playerName = p.getName();
    }

    public String getUUID() {
        return uuid;
    }

    public Location getCLoc() {
        return this.cLoc;
    }

    public void setCLoc( Player p) {
        this.cLoc = p.getLocation();
    }

    public boolean isFlying() {
        return isFlying;
    }

    public void setIsFlying(Player p) {
        this.isFlying = p.isFlying();
    }

    public Location getSLoc() {
        return sLoc;
    }

    public void setSLoc(Player p) {
        this.sLoc = p.getLocation();
    }

    public boolean isOpFlying() {
        return isOpFlying;
    }

    public void setIsOpFlying(Player p) {
        this.isOpFlying = p.isFlying();
    }

    public boolean inCreative() {
        return isCreative;
    }

    public void setInCreative(boolean var) {
        this.isCreative = var;
        config.set(FileIO._inCreative.name, var);
        locConfig.save();
    }
}
