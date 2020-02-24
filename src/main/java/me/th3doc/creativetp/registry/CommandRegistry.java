package me.th3doc.creativetp.registry;

import me.th3doc.creativetp.Main;
import me.th3doc.creativetp.commands.TPCreative;
import me.th3doc.creativetp.configs.Defaults;

public class CommandRegistry extends Defaults {
    private Main main;

    public CommandRegistry(Main main) {
        super(main);
        this.main = main;
    }

    protected void registerCommands() {
        main.getCommand("tpc").setExecutor(new TPCreative(main));
//        main.getCommand("effect").setExecutor(new EffectCommand(main));
    }

}
