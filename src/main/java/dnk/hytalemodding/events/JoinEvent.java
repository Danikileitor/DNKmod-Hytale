package dnk.hytalemodding.events;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;

public class JoinEvent {

    public static void onPlayerReady(PlayerReadyEvent event) {
        Player player = event.getPlayer();
        if (player.isFirstSpawn()) {
            player.sendMessage(Message.raw("Welcome to the server " + player.getDisplayName() + "!"));
        } else {
            player.sendMessage(Message.raw("Welcome " + player.getDisplayName()));
        }
    }
}