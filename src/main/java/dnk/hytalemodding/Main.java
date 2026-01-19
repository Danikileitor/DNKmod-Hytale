package dnk.hytalemodding;

import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import dnk.hytalemodding.commands.MainCommand;
import dnk.hytalemodding.commands.MainServerCommand;
import dnk.hytalemodding.events.ExampleEvent;

import javax.annotation.Nonnull;

public class Main extends JavaPlugin {

    public Main(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        this.getCommandRegistry().registerCommand(new MainServerCommand("dnk", "Main server command"));
        this.getCommandRegistry().registerCommand(new MainCommand());
        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, ExampleEvent::onPlayerReady);
    }
}