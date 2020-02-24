package me.th3doc.creativetp.enums;

public enum FileIO {

    /*
    Folder/File Names
     */
    _folderSeperator("/"),
    _playerDataFolder("playerdata"),
    _config("config.yml"),
    _worlds("Worlds.yml"),
    _playerLocYML("Locations.yml"),
    /*
    config.yml
     */
    _hubworld("HubWorld"),
    _creativeDelay("CreativeDelay"),
    _survivalDelay("SurvivalDelay"),
    _opNoDelay("opNoDelay"),
    /*
    Worlds.yml
     */
    _creativeWorld("CreativeWorld"),
    /*
    Locations.yml
     */
    _playerName("PlayerName"),
    _playerUUID("UUID"),
    _creativeLoc("CreativeLocation"),
    _survivalLoc("SurvivalLocation"),
    _isFlying("isFlying"),
    _isOpFlying("isOpFlying"),
    _inCreative("inCreative");

    public String name;

    FileIO(String name) {
        this.name = name;
    }
}
