package dnk.hytalemodding.commands.MainCommands;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.annotation.Nonnull;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractAsyncCommand;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.World;

public class UniverseInfo extends AbstractAsyncCommand {

    public UniverseInfo() {
        super("universeinfo", "server.commands.dnk.universeinfo.desc");
        addSubCommand(new UniversePlayerList());
    }

    @Override
    protected CompletableFuture<Void> executeAsync(@Nonnull CommandContext commandContext) {
        Universe universe = Universe.get();
        commandContext.sendMessage(Message.translation("server.commands.dnk.universeinfo.name").param("0", universe.getName()));
        commandContext.sendMessage(Message.translation("server.commands.dnk.universeinfo.playercount").param("0", universe.getPlayerCount()));
        commandContext.sendMessage(Message.translation("server.commands.dnk.universeinfo.defaultworld").param("0", universe.getDefaultWorld().getName()));

        Map<String, World> worlds = universe.getWorlds();
        commandContext.sendMessage(Message.translation("server.commands.dnk.universeinfo.worlds.title").param("0", worlds.size()));
        worlds.forEach((key, world) -> {
            commandContext.sendMessage(Message.translation("server.commands.dnk.universeinfo.worlds.world").param("0", world.getName()).param("1", world.getPlayerCount()));
        });

        return CompletableFuture.completedFuture(null);
    }

    public static class UniversePlayerList extends AbstractAsyncCommand {

        public UniversePlayerList() {
            super("playerlist", "server.commands.dnk.universeinfo.playerlist.desc");
        }

        @Override
        protected CompletableFuture<Void> executeAsync(CommandContext commandContext) {
            Universe universe = Universe.get();
            commandContext.sendMessage(Message.translation("server.commands.dnk.universeinfo.playerlist.title").param("0", universe.getPlayerCount()));
            universe.getPlayers().forEach(player -> {
                commandContext.sendMessage(Message.translation("server.commands.dnk.universeinfo.playerlist.player").param("0", player.getUsername()));
            });

            return CompletableFuture.completedFuture(null);
        }
    }
}