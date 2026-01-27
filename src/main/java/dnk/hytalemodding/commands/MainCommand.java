package dnk.hytalemodding.commands;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;
import com.hypixel.hytale.server.core.permissions.HytalePermissions;

import dnk.hytalemodding.commands.MainCommands.PlayerInfo;
import dnk.hytalemodding.commands.MainCommands.UniverseInfo;

public class MainCommand extends AbstractCommandCollection {

    public MainCommand() {
        super("dnk", "server.commands.dnk.main.desc");
        requirePermission(HytalePermissions.fromCommand(this.getName()));
        this.addSubCommand(new PlayerInfo());
        this.addSubCommand(new UniverseInfo());
    }
}