package eu.tinky.cfaction.manager;

import eu.tinky.cfaction.CFaction;
import eu.tinky.cfaction.models.Faction;
import eu.tinky.cfaction.models.data.FPlayer;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Optional;

public class PlayerManager {

    private final LinkedHashMap<String, FPlayer> fPlayers;

    public PlayerManager() {
        this.fPlayers = new LinkedHashMap<>();
    }

    public LinkedHashMap<String, FPlayer> getFPlayer() {
        return fPlayers;
    }

    public void addFPlayer(Player player) {
        try {
            Faction faction = CFaction.getInstance().getFactionDAO().getFactionByPlayerUUID(player.getUniqueId().toString());
            int playerId = CFaction.getInstance().getPlayerDAO().getOrCreatePlayer(player.getUniqueId().toString(), player.getName());

            FPlayer fPlayer = new FPlayer(playerId, player, faction);

            this.fPlayers.putIfAbsent(player.getName(), fPlayer);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeFPlayer(String username) {
        this.fPlayers.remove(username);
    }

    public Optional<FPlayer> getFPlayerByUsername(String username) {
        if (this.fPlayers.containsKey(username)) {
            return Optional.of(this.fPlayers.get(username));
        }

        return Optional.empty();
    }
}