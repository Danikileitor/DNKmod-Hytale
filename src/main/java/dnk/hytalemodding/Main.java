package dnk.hytalemodding;

import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import dnk.hytalemodding.commands.MainCommand;
import dnk.hytalemodding.events.JoinEvent;

import java.util.logging.Level;

import javax.annotation.Nonnull;

public class Main extends JavaPlugin {

    private static Main instance;

    public Main(@Nonnull JavaPluginInit init) {
        super(init);
    }

    public static Main get() {
        return instance;
    }

    @Override
    protected void setup() {
        instance = this;
        this.getCommandRegistry().registerCommand(new MainCommand());
        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, JoinEvent::onPlayerReady);
        getLogger().at(Level.INFO).log("Plugin setup complete!");
    }

    @Override
    protected void start() {
        getLogger().at(Level.INFO).log("Plugin started!");
    }

    @Override
    protected void shutdown() {
        getLogger().at(Level.INFO).log("Plugin shutting down!");
    }
}