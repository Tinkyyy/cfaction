package eu.tinky.cfaction.listener;

import eu.tinky.cfaction.CFaction;
import eu.tinky.cfaction.manager.PlayerManager;
import eu.tinky.cfaction.models.data.FPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Optional;

public class PlayerListener implements Listener {

    private final CFaction plugin;
    private final PlayerManager playerManager;

    public PlayerListener() {
        this.plugin = CFaction.getInstance();
        this.playerManager = this.plugin.getGlobalManager().getPlayerManager();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Optional<FPlayer> optionalFPlayer = this.playerManager.getFPlayerByUsername(player.getName());

        if (!optionalFPlayer.isPresent()) {
            this.playerManager.addFPlayer(player);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Optional<FPlayer> optionalFPlayer = this.playerManager.getFPlayerByUsername(player.getName());

        if (optionalFPlayer.isPresent()) {
            this.playerManager.removeFPlayer(player.getName());
        }
    }

    @EventHandler
    public void onPlayerChatEvent(AsyncPlayerChatEvent event) {
        Optional<FPlayer> optionalFPlayer = this.playerManager.getFPlayerByUsername(event.getPlayer().getName());

        if (optionalFPlayer.isPresent()) {
            FPlayer fPlayer = optionalFPlayer.get();
            if (fPlayer.hasFaction()) {
                String chatFormat = String.format("[%s] %s: %s", fPlayer.getFaction().getTag(), fPlayer.getName(), event.getMessage());
                event.setFormat(chatFormat);
            }
        }
    }
}