package me.th3doc.creativetp.player;

import me.th3doc.creativetp.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerManager extends RankHandler {

    public PlayerManager(Main main) {
        super(main);
    }
    /*
    Get @config cLoc name
     */
    public String getCLocName(Player p) {
        if(getCLoc() != null) {
            return getCLoc().getWorld().getName();
        }
        return "";
    }
    /*
    TPC @param Player
     */
    public void tpcPlayer(Player p) {
        saveLoc(p);
        Location loc = getSavedLoc(p);
        tp(p, loc);
    }
}
