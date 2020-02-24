package me.th3doc.creativetp.player;

import me.th3doc.creativetp.Main;
import me.th3doc.creativetp.configs.PlayerConfigManager;
import me.th3doc.creativetp.enums.Chat;
import me.th3doc.creativetp.enums.FileIO;
import me.th3doc.creativetp.enums.Perms;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public class LocationHandler extends PlayerConfigManager {
    private Main main;
    private final HashMap<String, Integer> tpEventMap = new HashMap<>();
    private boolean isTP = false;
    private boolean isSurvival = false;
    private boolean isCreative = false;

    public LocationHandler(Main main) {
        super(main);
        this.main = main;
    }
    /*
    Is teleport action True?False
     */
    public boolean isTP() {
        return isTP;
    }
    /*
    Get @param Player TaskID
     */
    public int getTpTaskID(Player p) {
        return tpEventMap.get(p.getUniqueId().toString());
    }
    /*
    Set @param Player TaskID to 0 for !NULL validation checks
     */
    public void resetTpTaskID(Player p) {
        tpEventMap.put(p.getUniqueId().toString(), 0);
    }
    /*
    Check sLoc isValid
     */
    private Location checkSLoc(Player p) {
        if (getSLoc() != null) {
            World w = getSLoc().getWorld();
            w.loadChunk((int) getSLoc().getX(), (int) getSLoc().getZ());
            return getSLoc();
        } else {
            p.sendMessage(Chat._noSLoc.txt);
            try {
                World w = main.getServer().getWorld(main.getConfig().get(FileIO._hubworld.name).toString());
                w.loadChunk((int) w.getSpawnLocation().getX(), (int) w.getSpawnLocation().getZ());
                return w.getSpawnLocation();
            } catch (Exception e) {p.sendMessage(Chat._missingHub.txt);}
        }
        return null;
    }
    /*
    Saves @param Player location before teleport
     */
    protected void saveLoc(Player p) {
        if(getPlayerName() == null) {
            getLocConfig(FileIO._playerDataFolder.name, p, FileIO._playerLocYML.name);
        }
        Object playerName = getPlayerName();
        Object uuid = getUUID();
        //CHECK PLAYER NAME & UUID != NULL & TO DATE
        if((playerName == null && uuid == p.getUniqueId().toString()) ||
                (playerName != p.getName() && uuid == p.getUniqueId().toString())) {
            setPlayerName(p);
        }
        //BOOLEANS FOR CHECK
        boolean isConfigWorld = p.getWorld().getName().equals(main.getWorld().getCWorldName());
        boolean isCreativeWorld = p.getWorld().getName().equals(main.getPlayer().getCLocName(p));
        //CHECK IF FIRST & LOC TO SAVE
        if (isConfigWorld || isCreativeWorld) {
            setCLoc(p);
            setIsFlying(p);
            isCreative = true;
        } else {
            setSLoc(p);
            setIsOpFlying(p);
            isSurvival = true;
        }
        saveLocConfig();
    }
    /*
    Handles what location @param Player should be sent
     */
    protected Location getSavedLoc(Player p) {
        boolean isConfigWorld = p.getWorld().getName().equals(main.getWorld().getCWorldName());
        boolean isCreativeWorld = p.getWorld().getName().equals(main.getPlayer().getCLocName(p));
        boolean inCreative = inCreative();
        boolean isValidCreative = main.getPlayer().getCLocName(p).equals(main.getWorld().getCWorldName());
        //CHECK WORLD BEFORE ASSIGNING TP LOCATION
        if (isConfigWorld || isCreativeWorld) {
            return checkSLoc(p);
        } else if(inCreative) {
            return checkSLoc(p);
        } else {
            if (isValidCreative) {
                getCLoc().getWorld().loadChunk((int) getCLoc().getX(), (int) getCLoc().getZ());
                return getCLoc();
            } else {
                p.sendMessage(Chat._noCLoc.txt);
                try {
                    World w = Bukkit.getWorld(main.getWorld().getCWorldName());
                    w.loadChunk((int) w.getSpawnLocation().getX(), (int) w.getSpawnLocation().getZ());
                    return w.getSpawnLocation();
                } catch (NullPointerException e) {p.sendMessage(Chat._missingWorld.txt);}
            }
        }
        return null;
    }
    /*
    Teleport @param Player to @param Location
    If p.hasPermission NULL @config Delay
     */
    protected void tp(Player p, Location loc) {
        if(isSurvival && p.hasPermission(Perms._tpcDelay.perm) && main.getConfig().get(FileIO._opNoDelay.name).equals(true)) {
            setInCreative(true);
            isSurvival = false;
        } else if(isCreative && p.hasPermission(Perms._tpcDelay.perm) && main.getConfig().get(FileIO._opNoDelay.name).equals(true)) {
            setInCreative(false);
            isCreative = false;
        }
        if(p.hasPermission(Perms._tpcDelay.perm) && main.getConfig().get(FileIO._opNoDelay.name).equals(true)) {
            p.teleport(loc);
        } else {
            long delay = 0L;
            if(isCreative) {
                delay = Long.parseLong(main.getConfig().get(FileIO._creativeDelay.name).toString());
            }
            if(isSurvival) {
                delay = Long.parseLong(main.getConfig().get(FileIO._survivalDelay.name).toString());
            }
            BukkitTask tp = new BukkitRunnable() {
                @Override
                public void run() {
                    if(isSurvival) {
                        setInCreative(true);
                        isSurvival = false;
                    }
                    if(isCreative) {
                        setInCreative(false);
                        isCreative = false;
                    }
                    isTP = false;
                    resetTpTaskID(p);
                    p.teleport(loc);
                }
            }.runTaskLater(main, delay);

            isTP = true;
            tpEventMap.put(p.getUniqueId().toString(), tp.getTaskId());
        }
    }
}
