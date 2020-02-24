package me.th3doc.creativetp.commands;

import me.th3doc.creativetp.Main;
import me.th3doc.creativetp.enums.Chat;
import me.th3doc.creativetp.enums.Cmd;
import me.th3doc.creativetp.enums.Perms;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TPCreative implements CommandExecutor, TabCompleter {
    Main main;

    public TPCreative(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            main.getLogger().info(Chat._noConsole.txt);
            return false;
        }
        Player p = (Player)sender;
        /*
        Check permission @args
         */
        if(!p.hasPermission(Perms._createWorld.perm) ||
                !p.hasPermission(Perms._setWorld.perm) ||
                !p.hasPermission(Perms._reloadConfig.perm) ||
                !p.hasPermission(Perms._tpcVoid.perm)) {
            if(args.length > 0) {
                p.sendMessage(Chat._invalidCommand.txt);
            }
        }
        /*
        Teleport @link CreativeWorld
         */
        if(p.hasPermission(Perms._tpcCommand.perm) &&
                p.hasPermission(Perms._tpcAll.perm) ||
                p.hasPermission(Perms._tpcNorm.perm) ||
                p.hasPermission(Perms._tpcVoid.perm)) {
            if(args.length == 0) {
                main.getPlayer().tpcPlayer(p);
            }
        }
        /*
        Argument Cases @args[0]
         */
        if (p.hasPermission(Perms._createWorld.perm) ||
                p.hasPermission(Perms._setWorld.perm) ||
                p.hasPermission(Perms._reloadConfig.perm) ||
                p.hasPermission(Perms._tpcVoid.perm)) {
            if(args.length > 0) {
                switch (args[0].toLowerCase()) {
                    case "createworld":
                        if (p.hasPermission(Perms._createWorld.perm)) {
                            main.getWorld().createWorld(p, args);
                        } else {
                            p.sendMessage(Chat._noPerm.txt);
                        }
                        break;
                    case "setworld":
                        if (p.hasPermission(Perms._setWorld.perm)) {
                            main.getWorld().setWorld(p, args);
                        } else {
                            p.sendMessage(Chat._noPerm.txt);
                        }
                        break;
                    case "reload":
                        if (p.hasPermission(Perms._reloadConfig.perm)) {
                            main.reloadConfig();
                            //reload Worlds.yml
                            //reload Locations.yml
                            //reload Permissions.yml
                            //.yml Files Go Here
                        }
                        break;
                    case "void":
                        if (p.hasPermission(Perms._tpcVoid.perm)) {
                            //Send Player to Void Creative
                        }
                        break;
                    default:
                        p.sendMessage(Chat._invalidCommand.txt);
                }
            }
        }
        return false;
    }
    /*
    TPCreative TAB COMPLETER
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        final List<String> arg0 = new ArrayList<>();//COMMAND ARGUMENTS
        final List<String> arg1 = new ArrayList<>();//WORLD LIST
        final String[] arg2 = {"NORMAL", "NETHER", "END"};//ENVIRONMENT ARGUMENTS
        List<String> tabComplete = new ArrayList<>();//TAB COMPLETE LIST

        if(!(sender instanceof Player)) {
            return null;
        }
        Player p = (Player)sender;

        if(p.hasPermission(Perms._createWorld.perm) ||
                p.hasPermission(Perms._setWorld.perm) ||
                p.hasPermission(Perms._reloadConfig.perm) ||
                p.hasPermission(Perms._tpcVoid.perm)) {
            switch (args.length) {
                case 1:
                    if(p.hasPermission(Perms._createWorld.perm)) {
                        arg0.add(Cmd._tpcCreate.cmd);
                    }
                    if(p.hasPermission(Perms._setWorld.perm)) {
                        arg0.add(Cmd._tpcSet.cmd);
                    }
                    if(p.hasPermission(Perms._reloadConfig.perm)) {
                        arg0.add(Cmd._tpcReload.cmd);
                    }
                    if(p.hasPermission(Perms._tpcVoid.perm)) {
                        arg0.add(Cmd._tpcVoid.cmd);
                    }
                    StringUtil.copyPartialMatches(args[0], arg0, tabComplete);
                    Collections.sort(tabComplete);
                    break;
                case 2:
                    if(p.hasPermission(Perms._setWorld.perm) || p.hasPermission(Perms._createWorld.perm)) {
                        for (World w : Bukkit.getServer().getWorlds()) {
                            arg1.add(w.getName());
                        }
                        StringUtil.copyPartialMatches(args[1], arg1, tabComplete);
                        Collections.sort(tabComplete);
                    }
                    break;
                case 3:
                    if(args[0].equals(Cmd._tpcCreate.cmd) && p.hasPermission(Perms._createWorld.perm)) {
                        StringUtil.copyPartialMatches(args[2], Arrays.asList(arg2), tabComplete);
                        Collections.sort(tabComplete);
                    }
                    break;
                default:
                    return null;
            }

            return tabComplete;
        }
        return null;
    }
}