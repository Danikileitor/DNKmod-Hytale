package dnk.hytalemodding.interactions;

import com.hypixel.hytale.builtin.teleport.components.TeleportHistory;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.ComponentAccessor;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.math.vector.Transform;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.math.vector.Vector3f;
import com.hypixel.hytale.protocol.InteractionType;
import com.hypixel.hytale.server.core.entity.InteractionContext;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.component.HeadRotation;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.modules.entity.teleport.Teleport;
import com.hypixel.hytale.server.core.modules.interaction.interaction.CooldownHandler;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.SimpleInstantInteraction;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import java.util.concurrent.Executor;
import javax.annotation.Nonnull;

public class DNKModWarpHome extends SimpleInstantInteraction {
    public static final BuilderCodec<DNKModWarpHome> CODEC = BuilderCodec
            .builder(DNKModWarpHome.class, DNKModWarpHome::new, SimpleInstantInteraction.CODEC).build();

    protected void firstRun(@Nonnull InteractionType type, @Nonnull InteractionContext context,
            @Nonnull CooldownHandler cooldown) {
        CommandBuffer<EntityStore> buffer = context.getCommandBuffer();
        if (buffer != null) {
            Ref<EntityStore> ref = context.getEntity();
            World world = ((EntityStore) buffer.getExternalData()).getWorld();
            TransformComponent transform = (TransformComponent) buffer.getComponent(ref,
                    TransformComponent.getComponentType());
            if (transform != null) {
                HeadRotation headRotation = (HeadRotation) buffer.getComponent(ref, HeadRotation.getComponentType());
                if (headRotation != null) {
                    Vector3d oldPos = transform.getPosition().clone();
                    Vector3f oldRotation = headRotation.getRotation().clone();
                    ((TeleportHistory) buffer.ensureAndGetComponent(ref, TeleportHistory.getComponentType()))
                            .append(world, oldPos, oldRotation, "Home");
                    Player.getRespawnPosition(ref, world.getName(), (ComponentAccessor<EntityStore>) buffer)
                            .thenAcceptAsync(homeTransform -> {
                                Teleport teleportComponent = Teleport.createForPlayer((World) null,
                                        (Transform) homeTransform);
                                buffer.addComponent(ref, Teleport.getComponentType(), teleportComponent);
                            }, (Executor) world);
                }
            }
        }
    }
}