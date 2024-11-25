package eu.tinky.cfaction.event;

import eu.tinky.cfaction.models.data.FPlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class FactionCreateEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private boolean isCancelled;
    private final FPlayer fPlayer;
    private final String factionName;

    public FactionCreateEvent(FPlayer fPlayer, String factionName) {
        this.fPlayer = fPlayer;
        this.factionName = factionName;
        this.isCancelled = false;
    }

    public FPlayer getFPlayer() {
        return fPlayer;
    }

    public String getFactionName() {
        return factionName;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
