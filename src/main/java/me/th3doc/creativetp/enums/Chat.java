package me.th3doc.creativetp.enums;

import org.bukkit.ChatColor;

public enum Chat {

    _configMissing(ChatColor.RED + "Configuration file not found! Creating file."),
    _configFound(ChatColor.RED + "Configuration file found! Loading file."),
    _worldFileMissing(ChatColor.RED + "Worlds file not found! Creating file."),
    _worldFileFound(ChatColor.RED + "Worlds file found! Loading file."),
    _onEnable(ChatColor.RED + "CreativeTP Enabled !"),
    _movedCancel(ChatColor.RED + "Cancelling TP, You Moved !"),
    _hurtCancel(ChatColor.RED + "Cancelling TP, You Got Hurt !"),
    _noConsole(ChatColor.RED + "This Command Cannot Be Run From Console!"),
    _invalidCommand(ChatColor.RED + "Invalid Command !"),
    _noWorldName(ChatColor.RED + "Oops, You Forgot A World Name !"),
    _toManyNames(ChatColor.RED + "Oops, To Many Names !"),
    _noPerm(ChatColor.RED + "You Don't Have Permission To-Do This !"),
    _onList(ChatColor.RED + "That's Not On The Environment List !"),
    _missingHub(ChatColor.RED + "Sorry, Can't Find The Server HubWorld World, Please Contact An Admin"),
    _missingWorld(ChatColor.RED + "World is Non-Existent/Unloaded or Not Defined, Sorry...Thanks For Holding, Please Contact An Admin."),
    _noCLoc(ChatColor.RED + "Creative World Changed/Unset, Will Try To Find Creative WorldSpawn...Hold Please."),
    _noSLoc(ChatColor.RED + "Survival Location Not Set, Attempting To Find HubWorld Spawn For TP");

    public String txt;

    Chat(String txt) {
        this.txt = txt;
    }
}
