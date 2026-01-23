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
        super("playerinfo", "server.commands.dnk.playerinfo.desc");
    }

    @Override
    protected void execute(@Nonnull CommandContext commandContext, @Nullable Ref<EntityStore> targetRef, @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef,
            @Nonnull World world, @Nonnull Store<EntityStore> store) {

        Player player = store.getComponent(ref, Player.getComponentType());

        try {
            if (targetRef == null) {
                player.sendMessage(Message.translation("server.commands.dnk.error.targetPlayerNotFound"));
                return;
            }
        } catch (Exception e) {
            player.sendMessage(Message.translation("server.commands.dnk.error.fetchingTargetPlayer"));
            return;
        }

        Player targetPlayer = store.getComponent(targetRef, Player.getComponentType());
        UUIDComponent targetComponent = store.getComponent(targetRef, UUIDComponent.getComponentType());
        TransformComponent targetTransform = store.getComponent(targetRef, TransformComponent.getComponentType());
        World targetWorld = targetPlayer.getWorld();

        EntityStatMap targetPlayerStats = store.getComponent(targetRef, EntityStatMap.getComponentType());
        EntityStatValue targetPlayerHealth = targetPlayerStats.get(DefaultEntityStatTypes.getHealth());
        EntityStatValue targetPlayerStamina = targetPlayerStats.get(DefaultEntityStatTypes.getStamina());
        EntityStatValue targetPlayerSignatureEnergy = targetPlayerStats.get(DefaultEntityStatTypes.getSignatureEnergy());
        EntityStatValue targetPlayerOxygen = targetPlayerStats.get(DefaultEntityStatTypes.getOxygen());
        EntityStatValue targetPlayerMana = targetPlayerStats.get(DefaultEntityStatTypes.getMana());
        EntityStatValue targetPlayerAmmo = targetPlayerStats.get(DefaultEntityStatTypes.getAmmo());

        player.sendMessage(Message.translation("server.commands.dnk.playerinfo.uuid").param("0", targetComponent.getUuid().toString()));
        player.sendMessage(Message.translation("server.commands.dnk.playerinfo.name").param("0", targetPlayer.getDisplayName()));
        player.sendMessage(Message.translation("server.commands.dnk.playerinfo.gamemode").param("0", targetPlayer.getGameMode().toString()));
        player.sendMessage(Message.translation("server.commands.dnk.playerinfo.language").param("0", playerRef.getLanguage()));

        player.sendMessage(Message.translation("server.commands.dnk.playerinfo.position").param("0", targetTransform.getPosition().getX())
                .param("1", targetTransform.getPosition().getY()).param("2", targetTransform.getPosition().getZ()));
        if (targetWorld != null) {
            player.sendMessage(Message.translation("server.commands.dnk.playerinfo.world").param("0", targetWorld.getName()));
            player.sendMessage(Message.translation("server.commands.dnk.playerinfo.playerCount").param("0", targetWorld.getPlayerCount()));
        } else {
            player.sendMessage(Message.translation("server.commands.dnk.playerinfo.world").param("0", Message.translation("commands.dnk.error.unknown")));
        }

        if (targetPlayerHealth != null) {
            player.sendMessage(Message.translation("server.commands.dnk.playerinfo.health").param("0", String.valueOf(targetPlayerHealth))
                    .param("1", String.valueOf(targetPlayerHealth.getMax())).param("2", targetPlayerHealth.asPercentage()));
        } else {
            player.sendMessage(Message.translation("server.commands.dnk.playerinfo.health").param("0", Message.translation("commands.dnk.error.unknown"))
                    .param("1", Message.translation("commands.dnk.error.unknown")).param("2", Message.translation("commands.dnk.error.unknown")));
        }

        if (targetPlayerStamina != null) {
            player.sendMessage(Message.translation("server.commands.dnk.playerinfo.stamina").param("0", String.valueOf(targetPlayerStamina))
                    .param("1", String.valueOf(targetPlayerStamina.getMax())).param("2", targetPlayerStamina.asPercentage()));
        } else {
            player.sendMessage(Message.translation("server.commands.dnk.playerinfo.stamina").param("0", Message.translation("commands.dnk.error.unknown"))
                    .param("1", Message.translation("commands.dnk.error.unknown")).param("2", Message.translation("commands.dnk.error.unknown")));
        }

        if (targetPlayerSignatureEnergy != null) {
            player.sendMessage(Message.translation("server.commands.dnk.playerinfo.signatureEnergy").param("0", String.valueOf(targetPlayerSignatureEnergy))
                    .param("1", String.valueOf(targetPlayerSignatureEnergy.getMax())).param("2", targetPlayerSignatureEnergy.asPercentage()));
        } else {
            player.sendMessage(Message.translation("server.commands.dnk.playerinfo.signatureEnergy").param("0", Message.translation("commands.dnk.error.unknown"))
                    .param("1", Message.translation("commands.dnk.error.unknown")).param("2", Message.translation("commands.dnk.error.unknown")));
        }

        if (targetPlayerOxygen != null) {
            player.sendMessage(Message.translation("server.commands.dnk.playerinfo.oxygen").param("0", String.valueOf(targetPlayerOxygen))
                    .param("1", String.valueOf(targetPlayerOxygen.getMax())).param("2", targetPlayerOxygen.asPercentage()));
        } else {
            player.sendMessage(Message.translation("server.commands.dnk.playerinfo.oxygen").param("0", Message.translation("commands.dnk.error.unknown"))
                    .param("1", Message.translation("commands.dnk.error.unknown")).param("2", Message.translation("commands.dnk.error.unknown")));
        }

        if (targetPlayerMana != null) {
            player.sendMessage(Message.translation("server.commands.dnk.playerinfo.mana").param("0", String.valueOf(targetPlayerMana))
                    .param("1", String.valueOf(targetPlayerMana.getMax())).param("2", targetPlayerMana.asPercentage()));
        } else {
            player.sendMessage(Message.translation("server.commands.dnk.playerinfo.mana").param("0", Message.translation("commands.dnk.error.unknown"))
                    .param("1", Message.translation("commands.dnk.error.unknown")).param("2", Message.translation("commands.dnk.error.unknown")));
        }

        if (targetPlayerAmmo != null) {
            player.sendMessage(Message.translation("server.commands.dnk.playerinfo.ammo").param("0", String.valueOf(targetPlayerAmmo))
                    .param("1", String.valueOf(targetPlayerAmmo.getMax())).param("2", targetPlayerAmmo.asPercentage()));
        } else {
            player.sendMessage(Message.translation("server.commands.dnk.playerinfo.ammo").param("0", Message.translation("commands.dnk.error.unknown"))
                    .param("1", Message.translation("commands.dnk.error.unknown")).param("2", Message.translation("commands.dnk.error.unknown")));
        }
    }
}