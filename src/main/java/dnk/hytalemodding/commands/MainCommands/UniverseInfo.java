package dnk.hytalemodding.commands.MainCommands;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.annotation.Nullable;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.AbstractCommand;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.World;

public class UniverseInfo extends AbstractCommand {

    public UniverseInfo() {
        super("universeinfo", "server.commands.dnk.universeinfo.desc");
    }

    @Override
    @Nullable
    protected CompletableFuture<Void> execute(CommandContext commandContext) {
        Universe universe = Universe.get();
        Map<String, World> worlds = universe.getWorlds();

        commandContext.sendMessage(Message.translation("server.commands.dnk.universeinfo.worlds.title").param("0", worlds.size()));
        worlds.forEach((key, world) -> {
            commandContext.sendMessage(Message.translation("server.commands.dnk.universeinfo.worlds").param("0", world.getName()).param("1", world.getPlayerCount()));
        });
        commandContext.sendMessage(Message.raw("Hello World"));

        return CompletableFuture.completedFuture(null);
    }
}
