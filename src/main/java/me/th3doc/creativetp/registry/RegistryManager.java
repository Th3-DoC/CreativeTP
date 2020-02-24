package me.th3doc.creativetp.registry;

import me.th3doc.creativetp.Main;

public class RegistryManager extends EventRegistry {
    Main main;

    public RegistryManager(Main main) {
        super(main);
        this.main = main;
    }
    /*
    onEnable
     */
    public void onEnable() {
        defaultConfigStatus();
        registerEvents();
        registerCommands();
    }
}
