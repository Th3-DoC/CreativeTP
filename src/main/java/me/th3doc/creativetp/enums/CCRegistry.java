package me.th3doc.creativetp.enums;

public enum CCRegistry {

    /*
    Minecraft/Bukkit Perms
     */
    FILL("minecraft.command.fill"),
    SELECTOR("minecraft.command.selector"),
    WEATHER("minecraft.command.weather"),
    TOGGLE_WEATHER("minecraft.command.toggledownfall"),
    TIME("minecraft.command.time"),
    SUMMON("minecraft.command.summon"),
    SETBLOCK("minecraft.command.setblock"),
    SEED("minecraft.command.seed"),
    /*
    EssentialsX Perms ADD?ANY?PLUGIN?CHECK?COMMANDMAP?||SIMPLE?YML
     */
    ESSENTIALS_TIME("essentials.time"),
    ESSENTIALS_TIME_SET("essentials.time.set"),
    ESSENTIALS_WEATHER("essentials.weather");

    public String perm;

    CCRegistry(String perm) {
        this.perm = perm;
    }
}
