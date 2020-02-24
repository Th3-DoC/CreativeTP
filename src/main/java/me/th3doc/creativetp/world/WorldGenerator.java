package me.th3doc.creativetp.world;

import me.th3doc.creativetp.Main;
import me.th3doc.creativetp.enums.Chat;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public class WorldGenerator extends WorldProperties {
    private Main main;

    public WorldGenerator(Main main) {
        super(main);
        this.main = main;
    }
    /*
    Create @param World with @param Environment for @param Player
     */
    public void createWorld(Player p, String[] args) {
        World.Environment env;

        if(args.length > 3) {
            p.sendMessage(Chat._invalidCommand.txt);
            return;
        }
        if (args.length < 2) {
            p.sendMessage(Chat._noWorldName.txt);
        } else {
            //CHECK IF WORLD EXISTS, CREATE ONLY IF IT DOESN'T.
            if (new File(main.getServer().getWorldContainer(), args[1]).exists()) {
                p.sendMessage("World Already Exists, Try Another One.");
            } else {
                p.sendMessage("Creating World " + args[1] + ", Please Wait Until Created");
                if(args.length == 3) {
                    switch (args[2].toLowerCase()) {
                        case "normal":
                            env = World.Environment.NORMAL;
                            break;
                        case "nether":
                            env = World.Environment.NETHER;
                            break;
                        case "end":
                            env = World.Environment.THE_END;
                            break;
                        default:
                            env = null;
                            break;
                    }
                    if(env != null) {
                        main.getServer().createWorld(new WorldCreator(args[1].toLowerCase()).environment(env));
                    } else {
                        p.sendMessage(Chat._onList.txt);
                        return;
                    }
                }
                if(args.length == 2) {
                    main.getServer().createWorld(new WorldCreator(args[1].toLowerCase()).environment(World.Environment.NORMAL));
                }
                main.getWorld().addSafeWorld(args[1].toLowerCase());
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        main.getWorld().setProperties(args[1].toLowerCase());
                        p.sendMessage("World " + args[1].toLowerCase() + " Was Created.");
                    }
                }.runTaskLater(main, 150L);
            }
        }
    }
    /*
    Set @config CreativeWorld/VoidWorld @param World
    ADD SUPPORT FOR VOID WORLD & CREATIVE NETHER/END WORLD LINK
     */
    public void setWorld(Player p, String[] args) {
        if (args.length < 2) {
            p.sendMessage(Chat._noWorldName.txt);
        } else if (args.length == 2) {
            //CHECK IF WORLD EXISTS, SET ONLY IF IT DOES.
            if (new File(main.getServer().getWorldContainer(), args[1]).exists()) {
                config.set("CreativeWorld", args[1]);
                save();
                p.sendMessage("World set to " + args[1]);
            } else {
                p.sendMessage("World Doesn't Exist!");
            }
        } else {
            p.sendMessage(Chat._toManyNames.txt);
        }
    }
}
