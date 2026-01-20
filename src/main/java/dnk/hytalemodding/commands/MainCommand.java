package dnk.hytalemodding.commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;

import dnk.hytalemodding.commands.MainCommands.PlayerInfo;

public class MainCommand extends AbstractCommandCollection {

    public MainCommand() {
        super("dnk", "Main command");
        this.addSubCommand(new PlayerInfo());
    }
}