package dnk.hytalemodding.commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;

import dnk.hytalemodding.commands.MainCommands.Info;

public class MainCommand extends AbstractCommandCollection {

    public MainCommand() {
        super("dnk", "Main player command");
        this.addSubCommand(new Info());
    }
}
