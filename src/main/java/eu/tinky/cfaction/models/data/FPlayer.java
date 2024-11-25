package eu.tinky.cfaction.models.data;


import eu.tinky.cfaction.models.Faction;
import org.bukkit.entity.Player;

public class FPlayer {

    private final int id;
    private final Player player;
    private Faction faction;

    public FPlayer(int id, Player player, Faction faction) {
        this.id = id;
        this.player = player;
        this.faction = faction;
    }

    public FPlayer(int id, Player player) {
        this.id = id;
        this.player = player;
        this.faction = null;
    }

    public int getId() {
        return id;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getName() {
        return this.player.getName();
    }

    public Faction getFaction() {
        return this.faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public boolean hasFaction() {
        return this.faction != null;
    }

    @Override
    public String toString() {
        return "FPlayer{" +
                "player=" + this.getName() +
                ", faction=" + (this.faction != null ? this.faction.getTag() : "Aucune") +
                '}';
    }
}
