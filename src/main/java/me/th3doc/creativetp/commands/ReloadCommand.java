package me.th3doc.creativetp.commands;

import me.th3doc.creativetp.Main;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

public class ReloadCommand {
    private Main main;

    public ReloadCommand(Main main) {
        this.main = main;
    }

    public void reload() {
        reloadPermissibleAttachments();
        //Add Growing Actions Here
    }
    /*
    @reload get @OnlinePlayers, reset permAttachment & perms
     */
    private void reloadPermissibleAttachments() {
        for(Player p:main.getServer().getOnlinePlayers()) {
            PermissionAttachment attachment = p.addAttachment(main);
            main.getPlayer().initializePerms(p, attachment);
        }
    }
}
