package dnk.hytalemodding.commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;

import dnk.hytalemodding.commands.MainCommands.PlayerInfo;
import dnk.hytalemodding.commands.MainCommands.UniverseInfo;

public class MainCommand extends AbstractCommandCollection {

    public MainCommand() {
        super("dnk", "server.commands.dnk.main.desc");
        this.addSubCommand(new PlayerInfo());
        this.addSubCommand(new UniverseInfo());
    }
}