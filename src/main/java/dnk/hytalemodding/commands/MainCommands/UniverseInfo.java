package dnk.hytalemodding.commands.MainCommands;

import java.util.concurrent.CompletableFuture;

import javax.annotation.Nullable;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.AbstractCommand;
import com.hypixel.hytale.server.core.command.system.CommandContext;

public class UniverseInfo extends AbstractCommand {

    public UniverseInfo() {
        super("universeinfo", "server.commands.dnk.universeinfo.desc");
    }

    @Override
    @Nullable
    protected CompletableFuture<Void> execute(CommandContext commandContext) {
        Message.raw("Hello World");
        return CompletableFuture.completedFuture(null);
    }
}
