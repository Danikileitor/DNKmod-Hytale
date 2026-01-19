package dnk.hytalemodding.commands.MainCommands;

import javax.annotation.Nonnull;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.UUIDComponent;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

public class Info extends AbstractPlayerCommand {

    public Info() {
        super("info", "Get player info");
    }

    @Override
    protected void execute(@Nonnull CommandContext commandContext, @Nonnull Store<EntityStore> store,
            @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
        Player player = store.getComponent(ref, Player.getComponentType());
        UUIDComponent component = store.getComponent(ref, UUIDComponent.getComponentType());
        TransformComponent transform = store.getComponent(ref, TransformComponent.getComponentType());

        player.sendMessage(Message.raw("UUID: " + component.getUuid()));
        player.sendMessage(Message.raw("Position: " + transform.getPosition()));
    }
}