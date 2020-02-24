package me.th3doc.creativetp.commands;

import me.th3doc.creativetp.Main;
import me.th3doc.creativetp.player.RankHandler;
import me.th3doc.creativetp.enums.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class EffectCommand extends RankHandler implements CommandExecutor, TabCompleter {
    Main main;

    public EffectCommand(Main main) {
        super(main);
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            main.getLogger().info(Chat._noConsole.txt);
            return false;
        }
        Player p = (Player)commandSender;
        /*
        USAGE <COMMAND> <GIVE/CLEAR> <COMMANDSENDER> <POTIONEFFECT> <TIME> <STRENGTH>
         */

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
