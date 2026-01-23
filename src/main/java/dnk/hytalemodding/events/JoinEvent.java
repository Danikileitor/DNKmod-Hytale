package dnk.hytalemodding.events;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;

public class JoinEvent {

    public static void onPlayerReady(PlayerReadyEvent event) {
        Player player = event.getPlayer();
        if (player.isFirstSpawn()) {
            player.sendMessage(Message.translation("server.commands.dnk.welcome.firstSpawn").param("0", player.getDisplayName()));
        } else {
            player.sendMessage(Message.translation("server.commands.dnk.welcome.returning").param("0", player.getDisplayName()));
        }
    }
}