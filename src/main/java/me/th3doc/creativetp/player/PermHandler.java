package me.th3doc.creativetp.player;

import me.th3doc.creativetp.Main;
import me.th3doc.creativetp.enums.CCRegistry;
import me.th3doc.creativetp.enums.Cmd;
import me.th3doc.creativetp.enums.Perms;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PermHandler extends LocationHandler {
    private Main main;
    private final HashMap<String, PermissionAttachment> perms = new HashMap<>();

    public PermHandler(Main main) {
        super(main);
        this.main = main;
    }
    /*
    Get @key Player @map PermID
     */
    private PermissionAttachment getPermID(Player p) {
            return perms.get(p.getUniqueId().toString());
    }
    /*
    Set @key Player @map TaskID
     */
    private void setPermID(Player p, PermissionAttachment attachment) {
            perms.put(p.getUniqueId().toString(), attachment);
    }
    /*
    Set @param Player @config Permissions
     */
    public void setPerms(Player p) { // SETUP NEEDED : Check/Instantiate && load if Perm File already there
        if(!p.hasPermission(Perms._permBypass.perm)) {
            PermissionAttachment attachment = getPermID(p);
            List<String> permissions = Stream.of(CCRegistry.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            for (String permission : permissions) {
                attachment.setPermission(permission, true);
            }
            p.recalculatePermissions();
            p.updateCommands();
        }
    }
    /*
    Remove @param Player @config Permissions
     */
    public void removePerms(Player p) { // SETUP NEEDED : Remove all but chosen list based on Perm File
        if(!p.hasPermission(Perms._permBypass.perm)) {
            PermissionAttachment perms = getPermID(p);
            List<String> permissions = Stream.of(CCRegistry.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            for (String permission : permissions) {
                perms.unsetPermission(permission);
            }
            p.recalculatePermissions();
            p.updateCommands();
        }
    }
    /*
    Initialize @param Player @param attachment
    */
    public void initializePerms(Player p, PermissionAttachment attachment) {
        attachment.setPermission(Perms._tpcCommand.perm, true);
        attachment.setPermission(Perms._tpcNorm.perm, true);
        p.updateCommands();
        setPermID(p, attachment);
        resetTpTaskID(p);
        if(p.getWorld().getName().equals(main.getWorld().getCWorldName())) {
            setPerms(p);
        } else if(p.getWorld().getName().equals(main.getPlayer().getCLocName(p))) {
            setPerms(p);
        }

    }
    /*
    @param Player @command creative_mode
     */
    public void setCreative(Player p) {
        if(!p.hasPermission(Perms._gmBypass.perm)) {
            p.setGameMode(GameMode.CREATIVE);
        }
        if(isFlying()) {
            if(p.getAllowFlight()) {
                p.setFlying(isFlying());
            }
        }
    }

    public void setSurvival(Player p) {
        if(!p.hasPermission(Perms._gmBypass.perm)) {
            p.setGameMode(GameMode.SURVIVAL);
        }
        if(!p.hasPermission(Perms._permBypass.perm)) {
            Collection<PotionEffect> potions = p.getActivePotionEffects();
            if(!potions.isEmpty()) {
                main.getServer().dispatchCommand(main.getServer().getConsoleSender(), Cmd._effectClear.cmd + p.getName());
            }
        }
        if(isOpFlying()) {
            if(p.getAllowFlight()) {
                p.setFlying(isOpFlying());
            }
        }
    }

}
