package eu.tinky.cfaction.listener;

import eu.tinky.cfaction.CFaction;
import eu.tinky.cfaction.event.FactionCreateEvent;
import eu.tinky.cfaction.models.data.FPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FactionListener implements Listener {

    private final CFaction plugin;

    public FactionListener() {
        this.plugin = CFaction.getInstance();
    }

    @EventHandler
    public void onFactionCreate(FactionCreateEvent event) {
        FPlayer fPlayer = event.getFPlayer();
        String factionName = event.getFactionName();

        fPlayer.getPlayer().sendMessage("Tu as crée la faction: " + factionName);
    }
}
