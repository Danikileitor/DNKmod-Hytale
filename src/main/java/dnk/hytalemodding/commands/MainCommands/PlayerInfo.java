package dnk.hytalemodding.commands.MainCommands;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractTargetPlayerCommand;
import com.hypixel.hytale.server.core.entity.UUIDComponent;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.modules.entitystats.EntityStatMap;
import com.hypixel.hytale.server.core.modules.entitystats.EntityStatValue;
import com.hypixel.hytale.server.core.modules.entitystats.asset.DefaultEntityStatTypes;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

public class PlayerInfo extends AbstractTargetPlayerCommand {

    public PlayerInfo() {
        super("PlayerInfo", "Get player info");
    }

    @Override
    protected void execute(@Nonnull CommandContext commandContext, @Nullable Ref<EntityStore> targetRef,
            @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world,
            @Nonnull Store<EntityStore> store) {

        Player player = store.getComponent(ref, Player.getComponentType());

        try {
            if (targetRef == null) {
                player.sendMessage(Message.raw("Target player not found."));
                return;
            }
        } catch (Exception e) {
            player.sendMessage(Message.raw("An error occurred while fetching target player."));
            return;
        }

        Player targetPlayer = store.getComponent(targetRef, Player.getComponentType());
        UUIDComponent targetComponent = store.getComponent(targetRef, UUIDComponent.getComponentType());
        TransformComponent targetTransform = store.getComponent(targetRef, TransformComponent.getComponentType());
        World targetWorld = targetPlayer.getWorld();

        @SuppressWarnings("null")
        EntityStatMap targetPlayerStats = store.getComponent(targetRef, EntityStatMap.getComponentType());
        EntityStatValue targetPlayerHealth = targetPlayerStats.get(DefaultEntityStatTypes.getHealth());
        EntityStatValue targetPlayerStamina = targetPlayerStats.get(DefaultEntityStatTypes.getStamina());
        EntityStatValue targetPlayerMana = targetPlayerStats.get(DefaultEntityStatTypes.getMana());

        player.sendMessage(Message.raw("UUID: " + targetComponent.getUuid()));
        player.sendMessage(Message.raw("Name: " + targetPlayer.getDisplayName()));
        player.sendMessage(Message.raw("GameMode: " + targetPlayer.getGameMode()));
        player.sendMessage(Message.raw("Language: " + playerRef.getLanguage()));

        player.sendMessage(Message.raw("Position: " + targetTransform.getPosition()));
        if (targetWorld != null) {
            player.sendMessage(Message.raw("World: " + targetWorld.getName()));
            player.sendMessage(Message.raw("Players: " + targetWorld.getPlayerCount()));
        } else {
            player.sendMessage(Message.raw("World: Unknown"));
        }

        player.sendMessage(Message.raw("Health: " + targetPlayerHealth));
        player.sendMessage(Message.raw("Stamina: " + targetPlayerStamina));
        player.sendMessage(Message.raw("Mana: " + targetPlayerMana));
    }
}