package dnk.hytalemodding.commands;

import javax.annotation.Nonnull;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.OptionalArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.UUIDComponent;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

public class MainPlayerCommand extends AbstractPlayerCommand {

    public MainPlayerCommand() {
        super("dnk", "Main player command");
    }

    // Argumentos
    OptionalArg<String> infoArg = this.withOptionalArg("info", "Player info", ArgTypes.STRING);
    OptionalArg<String> infoPlayeArg = this.withOptionalArg("infoPlayer", "Target player info", ArgTypes.STRING);

    @Override
    protected void execute(@Nonnull CommandContext commandContext, @Nonnull Store<EntityStore> store,
            @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
        Player player = store.getComponent(ref, Player.getComponentType());
        UUIDComponent component = store.getComponent(ref, UUIDComponent.getComponentType());
        TransformComponent transform = store.getComponent(ref, TransformComponent.getComponentType());

        boolean isInfoArgPresent = infoArg.get(commandContext).equals(infoArg.getName());
        boolean isInfoPlayerArgPresent = !infoPlayeArg.get(commandContext).isBlank();

        if (isInfoArgPresent) {
            if (isInfoPlayerArgPresent) {
                String targetPlayerName = infoPlayeArg.get(commandContext);
                PlayerRef targetPlayerRef = world.getPlayerRefs().stream()
                        .filter(pr -> pr.getUsername().equalsIgnoreCase(targetPlayerName))
                        .findFirst()
                        .orElse(null);
                if (targetPlayerRef == null) {
                    player.sendMessage(Message.raw("Player " + targetPlayerName + " not found."));
                    return;
                }
                Ref<EntityStore> targetRef = targetPlayerRef.getReference();
                UUIDComponent targetComponent = store.getComponent(targetRef, UUIDComponent.getComponentType());
                TransformComponent targetTransform = store.getComponent(targetRef,
                        TransformComponent.getComponentType());

                player.sendMessage(Message.raw("Target Player UUID: " + targetComponent.getUuid()));
                player.sendMessage(Message.raw("Target Player Position: " + targetTransform.getPosition()));
            } else {
                player.sendMessage(Message.raw("UUID: " + component.getUuid()));
                player.sendMessage(Message.raw("Position: " + transform.getPosition()));
            }
        }
    }
}
